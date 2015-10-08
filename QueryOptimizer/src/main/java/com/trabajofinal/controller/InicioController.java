package com.trabajofinal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
		model.addAttribute("resultados", consulta.gestionarConsulta(config.getName(), database));		
		consultaService.save(consulta);
		
		MachineLearning machineLearning = new MachineLearning(consulta, config);
		Double timeAverage = consultaService.getTimeAverage(consulta.getQuery());
		List<String> queryParseada = machineLearning.parsearQuery(consulta.getQuery());
		int itemId = machineLearning.getItemId(queryParseada, consulta.getQuery());
		//Ranking ranking = rankingService.findByItemId(itemId);		
		
		List <Ranking> rankings = rankingService.findAll();	//Todos los rankings	
		
		Ranking nuevoRanking = new Ranking(usu.getId(),itemId,(float)1,timeAverage,date); // null porque no esta rankeado
		rankingService.save(nuevoRanking);
		
		List<String> queriesAlternativas = this.generarQueriesAlternativas(config, queryParseada,consulta,itemId);
		
		
		//Si ya existe en la tabla de ranking, le pongo el mismo ranking
		/*if (ranking.getItemId() == nuevoRanking.getItemId()){
			nuevoRanking.setRanking(ranking.getRanking());			
		}*/
		
	//	machineLearning.gestionarRanking(database, consulta, nuevoRanking, itemService.findById(nuevoRanking.getItemId()));
		
		
		//ranking.setRanking(machineLearning.crearRankingId(rankingService.findAll(),itemService.findById(nuevoRanking.getItemId())));
		
		///rankingService.save(nuevoRanking);
		
		model.addAttribute("consulta", consulta);
		model.addAttribute("user", usu);		
		return "inicio";
    	
    	
	}	
	
	// Con MachineLearning genero queries alternativas
	private List<String> generarQueriesAlternativas(Configuracion config, List<String> queryParseada, Consulta consulta, int itemId){
		Database database = new Database();
		// Trae todas las distintas al itemId
	    List<String> keyFieldsArray = new ArrayList<String>();
		String currentSqlStatement; String table = null; String conditions = null; String keyFields = null;
		String queryAlternativa = null;
		StringBuilder nuevaQueryAlternativa = new StringBuilder();

		Boolean where = false; Boolean inner = false;  Boolean all = false;		
		List <Item> queriesAlternativas = itemService.findQueriesAlternativas(itemId);
		
		currentSqlStatement = queryParseada.get(0).substring(5);
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
					where = true;
					i++;
					conditions = queryParseada.get(i).substring(5); // Hacer lo mismo que para keyfields
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
	    	
	    	for(int i = 0; i < queriesAlternativas.size(); i++) {
	            queryAlternativa = queriesAlternativas.get(i).getQuery();
	            queryAlternativa = queryAlternativa.replace("%table%", table).replace("%keyfields%", keyFields);
	            this.compararQueries(config, consulta.getQuery(), queryAlternativa);
	            System.out.println(queryAlternativa);
	        }
	    	
	    	
		/*	nuevaQueryAlternativa.append(currentSqlStatement);
			if (all == true){
				nuevaQueryAlternativa.append(" * ");
			}
			else{
				keyFields = keyFieldsArray.toString().replace("[", " ");
				keyFields = keyFields.replace("]", " ");
				nuevaQueryAlternativa.append(keyFields);
			}
			nuevaQueryAlternativa.append(" FROM "); 
			nuevaQueryAlternativa.append(table);
			if (where == true){
				nuevaQueryAlternativa.append(" WHERE ");
				nuevaQueryAlternativa.append(conditions);
			}
			
			String queryAlternativa = nuevaQueryAlternativa.toString();
			System.out.println(queryAlternativa);
			*/
			
			break;
		}
			default:
				break;
		}	
		return queryParseada;
	}

	// Comparar la query alternativa con la original
	private String compararQueries(Configuracion config, String consulta1, String consulta2){
		/* Antes de esto, debo ver si las consultas son equivalentes y de ahi pedir el ranking 
		 * Se ve eso en ConsultasEquivalentes
		 * */
		Double maxValue = 0.0;
		Double minValue = 10000.0;
		
		/*for(int i = 0; i < rankings.size(); i++) {
			Double time = rankings.get(i).getTime();
			// Da el máximo valor
			if (time > maxValue) {
			    maxValue = time;
			}
			if (time < minValue) {
			    minValue = time;
			}			
		}
		if (nuevoRanking.getTime() > maxValue){
			nuevoRanking.setRanking(1.0f);			
		}
		if (nuevoRanking.getTime() < minValue){
			nuevoRanking.setRanking(5.0f);			
		}*/
		
		//rankingService.save(nuevoRanking);
		
		System.out.print("Maximo: " + maxValue + " Minimo: " + minValue);
		
		
		/*// Ordenar tiempos
		 * HashMap<Double,Integer> map = new HashMap<Double,Integer>();

		for(int i = 0; i < rankings.size(); i++) {
			System.out.println(rankings.get(i).getTime());
			map.put(rankings.get(i).getTime(),i);			
		}
		
		Map<Double,Integer> treeMap = new TreeMap<Double,Integer>(map);
		for (Entry<Double,Integer> entry : treeMap.entrySet()) {
			System.out.println(" Id: " + entry.getValue() + " Tiempo: " + entry.getKey());
		}*/


//		Double maxValue = null;

	/*	if (nuevoRanking.getTime() > maxValue){
			maxValue = nuevoRanking.getTime(); 			
		}*/
		
		 		
		return "holis";
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
