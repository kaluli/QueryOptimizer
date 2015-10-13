package com.trabajofinal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.trabajofinal.model.Configuracion;
import com.trabajofinal.model.Consulta;
import com.trabajofinal.model.Item;
import com.trabajofinal.model.Ranking;
import com.trabajofinal.model.User;
import com.trabajofinal.service.ConfiguracionService;
import com.trabajofinal.service.ConsultaService;
import com.trabajofinal.service.ItemService;
import com.trabajofinal.service.RankingService;
import com.trabajofinal.service.UserService;
import com.trabajofinal.utils.Database;
import com.trabajofinal.utils.MachineLearning;


@Controller
@SessionAttributes("userSession") //Spring obtiene una instancia de la session
public class InicioController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private ConfiguracionService configuracionService;

	@Autowired
	private RankingService rankingService;

	@Autowired
	private ItemService itemService;	

	@RequestMapping(value="/inicio", method=RequestMethod.GET)	
	public String inicio(Model model,  HttpSession session, @RequestParam(value="id", required=false) Integer id, @RequestParam(value="action", required=false) String action) {
		if (session.getAttribute("userSession") == null){
			return "redirect:login.html";
		}
		
		User usu = userService.findByUserName(session.getAttribute("userSession").toString());
		if (usu != null){
			int idlogueado = usu.getId();
			model.addAttribute("logueado", idlogueado);
		}			
		
		if(id == null){ //Mi perfil			
			model.addAttribute("yomismo", true);									
		}
		
		model.addAttribute("user", usu);									
		return "inicio";
	}

	@RequestMapping(value="/inicio", method=RequestMethod.POST)
	public String inicio(@RequestParam("query") String query, @RequestParam("configId") Integer configId, @Valid @ModelAttribute("usuario") User usuario, BindingResult result, Model model, HttpSession session) {
    	Date date = new Date();
    	Database database = new Database();
    	if (session.getAttribute("userSession") == null){
			return "redirect:login.html";			
        }
    	
		User usu = userService.findByUserName(session.getAttribute("userSession").toString());						
		Consulta consulta = new Consulta(query,usu.getId(),configId,date);				
		Configuracion config = configuracionService.findById(consulta.getIdconfig());
		List<Map<String, Object>> resultados = consulta.gestionarConsulta(config, database);
		model.addAttribute("resultados", resultados);		
		consultaService.save(consulta);
		
		MachineLearning machineLearning = new MachineLearning(consulta, config);
		Double timeAverage = consultaService.getTimeAverage(consulta.getQuery());
		List<String> queryParseada = machineLearning.parsearQuery(consulta.getQuery());
		int itemId = machineLearning.getItemId(queryParseada, consulta.getQuery());
		model.addAttribute("recomendacion", "No hay recomendaciones");
		if (itemId != 0){
			List<Item> queriesAlternativas = itemService.findQueriesAlternativas(itemId);
			entrenarModelo(resultados, queriesAlternativas ,queryParseada, config, consulta,itemId);
			Ranking nuevoRanking = new Ranking(usu.getId(),itemId,(float)1,timeAverage,date); // 1 el ranking más bajo por defecto
			rankingService.save(nuevoRanking);
			List<RecommendedItem> recomendaciones = machineLearning.gestionarRanking(database, consulta, nuevoRanking, itemService.findById(nuevoRanking.getItemId()));
			if (recomendaciones.size() > 0){
				for (RecommendedItem recommendation : recomendaciones) {	
					if (recommendation.getValue() > 0){
						//this.compararQueries(config, resultados, consulta, consulta2, itemId, queriesAlternativas.get(i).getId());
						String queryAlternativaRecomendada = this.crearQueryAlternativa(config, consulta, itemService.findById((int)recommendation.getItemID()).getQuery(), this.crearFields(config, queryParseada, consulta));
						Consulta queryAlternativa = new Consulta(queryAlternativaRecomendada,consulta.getIduser(),consulta.getIdconfig(),date);
						if (this.compararQueries(config, consulta,queryAlternativa, itemId,(int)recommendation.getItemID()) == true){
							double timeAverageAlternativa = rankingService.getTimeAverage((int)recommendation.getItemID());
							double timeAverageOriginal = rankingService.getTimeAverage(itemId);
							if (timeAverageAlternativa < timeAverageOriginal){
								model.addAttribute("recomendacion", queryAlternativaRecomendada);	
							} 														
						}
						
						
					}
				}	
			}			
			
		}	
		model.addAttribute("consulta", consulta);
		model.addAttribute("user", usu);		
		return "inicio";    	    	
	}		
	
	private void entrenarModelo(List<Map<String, Object>> resultados, List<Item> queriesAlternativas,List<String> queryParseada, Configuracion config, Consulta consulta, int itemId){		
		String queryAlternativa = null;		
		String table = this.crearFields(config,queryParseada,consulta).get("table").toString();
		String keyFields = this.crearFields(config,queryParseada,consulta).get("keyFields").toString();
		String conditions = this.crearFields(config,queryParseada,consulta).get("conditions").toString();
		Double promedio;
		Float max = 5f; Float min = 1f; Float puntajeAlternativa; Float puntajeOriginal;
		rankingService.deleteAll();
		for(int i = 0; i < queriesAlternativas.size(); i++) {
            queryAlternativa = queriesAlternativas.get(i).getQuery();
            queryAlternativa = queryAlternativa.replace("%table%", table).replace("%keyfields%", keyFields).replace("%conditions%", conditions);            
            Consulta consulta2 = new Consulta(queryAlternativa, consulta.getIduser(),consulta.getIdconfig(),consulta.getCreated());
            int itemConsultaAlternativa = queriesAlternativas.get(i).getId();
            
            for(int j = 2; j < 100; j++){
	            if (this.compararQueries(config, consulta, consulta2, itemId, queriesAlternativas.get(i).getId()) == true){
	            	if (consulta2.getTime() < consulta.getTime()){
	            		// La alternativa es mejor
	            		puntajeAlternativa = max;
	            		puntajeOriginal = min;	            		
	            	}
	            	else{
	            		puntajeAlternativa = min;
	            		puntajeOriginal = max;						
	            	}
					Ranking rankingItem = new Ranking (j,itemConsultaAlternativa,puntajeAlternativa,consulta2.getTime(),consulta.getCreated());
					rankingService.save(rankingItem);
					//promedio = consultaService.getTimeAverage(consulta.getQuery());
					Ranking ranking = new Ranking (j,itemId,puntajeOriginal,consulta.getTime(),consulta.getCreated());
					rankingService.save(ranking);
	            }
		    }           
        }	    	
	}
	
	private String crearQueryAlternativa(Configuracion config, Consulta consulta, String queryParseadaRecomendada, Map<String,Object> fields){
		String queryAlternativaRecomendada = queryParseadaRecomendada;
		String table = fields.get("table").toString();
		String keyFields = fields.get("keyFields").toString();
		String conditions = fields.get("conditions").toString();
		
		queryAlternativaRecomendada = queryAlternativaRecomendada.replace("%table%", table).replace("%keyfields%", keyFields).replace("%conditions%", conditions);
		return queryAlternativaRecomendada;
	}	
	
	private Map<String,Object> crearFields(Configuracion config, List<String> queryParseada, Consulta consulta){
		Database database = new Database();
		Map<String,Object> fields = new HashMap<String,Object>();
		List<String> keyFieldsArray = new ArrayList<String>();
		String table = ""; String conditions = ""; String keyFields = "";
	
		switch (queryParseada.get(0)){
			case "term=select":{			
				for(int i = 0; i < queryParseada.size(); i++) { 
					// *** TABLE
					if (queryParseada.get(i).contentEquals("term=from")){
						i++;						
						table = queryParseada.get(i).substring(5); //Quita el term=
						int index = consulta.getQuery().toLowerCase().indexOf(table); //Para caseSensitive
						table = consulta.getQuery().substring(index, index + table.length());						
					}
					// *** CONDITIONS
					if (queryParseada.get(i).contentEquals("term=where")){						
						i++;
						int index = consulta.getQuery().indexOf("where");
						conditions = consulta.getQuery().substring(index + "where".length(), consulta.getQuery().length());
						conditions = conditions.replace(";", " ");			

						System.out.println(conditions);
					}	         
					if (queryParseada.get(i).contentEquals("term=inner join")){						
						i++;
						int index = consulta.getQuery().indexOf("where");
						conditions = consulta.getQuery().substring(index + "where".length(), consulta.getQuery().length());
						conditions = conditions.replace(";", " ");			

						System.out.println(conditions);
					}	         
				}
			break;
			}
		}
		// *** KEYFIELDS
		List<Map<String, Object>> results = database.traerKeyFields(database, config, table);
		for (Map<String, Object> result : results) {
	        HashMap<String, Object> map = (HashMap<String, Object>) result;
	        for (Object key : map.keySet()) {
	        	System.out.println(map.get(key).toString());
	        	keyFieldsArray.add(map.get(key).toString());	                
	        }	        
	    }			
		keyFields = keyFieldsArray.toString().replace("[", " ").replace("]", " ");			
		System.out.println(keyFields);
		
		//*** EXTRAS -> GROUP BY, ORDER BY, HAV
		
		fields.put("keyFields", keyFields);
		fields.put("table", table);
		fields.put("conditions", conditions);
		
		return fields;
	}		

	// Comparar la query alternativa con la original
	private Boolean compararQueries(Configuracion config,Consulta consultaOriginal, Consulta consultaAlternativa, int itemConsultaOriginal, int itemConsultaAlternativa){
		Database database1 = new Database();
		Database database2 = new Database();
		List<Map<String, Object>> resultadosConsulta1 = database1.ejecutarQuery(config, consultaOriginal, database1);
		List<Map<String, Object>> resultadosConsulta2 = database2.ejecutarQuery(config, consultaAlternativa, database2);
		if (resultadosConsulta1.equals(resultadosConsulta2)){
			return true;					
		}
		else
			return false;			
	}
	
	@RequestMapping(value="/configuracion", method=RequestMethod.GET)	
	public String configuracion(Model model,  HttpSession session, @RequestParam(value="id", required=false) Integer id, @RequestParam(value="action", required=false) String action) {		
		User usu = new User();	
		
		String nombre_user = session.getAttribute("userSession").toString();
		usu = userService.findByUserName(nombre_user);
		int idlogueado = usu.getId();
		model.addAttribute("logueado", idlogueado);
		//model.addAttribute("configuracion", configuracion);
		
		if(id == null){ //Mi perfil
			model.addAttribute("yomismo", true);		
			model.addAttribute("config", usu.getConfiguraciones());					
		}
		
		model.addAttribute("user", usu);									
		return "configuracion";
	}

	@RequestMapping(value="/configuracion", method=RequestMethod.POST)
	public String configuracion(@Valid @ModelAttribute("configuracion") Configuracion configuracion, BindingResult result, HttpSession session, Model model ) {
		String nombre_user = session.getAttribute("userSession").toString();
		User usu = userService.findByUserName(nombre_user);
		model.addAttribute("user", usu);
		return "redirect:configuracion.html";
	}
}
