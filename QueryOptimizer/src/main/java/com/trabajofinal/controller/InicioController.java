package com.trabajofinal.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
		int itemId = machineLearning.getItemId(consulta.getQuery());
		//Ranking ranking = rankingService.findByItemId(itemId);		
		
		List <Ranking> rankings = rankingService.findAll();	//Todos los rankings	
		
		Ranking nuevoRanking = new Ranking(usu.getId(),itemId,(float)1,timeAverage,date); // null porque no esta rankeado
		rankingService.save(nuevoRanking);
		
		this.compararQueries(rankings, consulta, nuevoRanking);
		
		
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
	
	// Comparar la query alternativa con la original
	public String compararQueries(List<Ranking> rankings, Consulta consulta, Ranking nuevoRanking){
		
		Double maxValue = 0.0;
		Double minValue = 10000.0
				;
		for(int i = 0; i < rankings.size(); i++) {
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
		}
		
		rankingService.save(nuevoRanking);
		
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
