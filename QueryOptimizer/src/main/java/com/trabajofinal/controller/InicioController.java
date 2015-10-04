package com.trabajofinal.controller;

import java.util.Date;

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

import com.trabajofinal.mahout.MachineLearning;
import com.trabajofinal.model.Configuracion;
import com.trabajofinal.model.Consulta;
import com.trabajofinal.model.Ranking;
import com.trabajofinal.model.User;
import com.trabajofinal.service.ConfiguracionService;
import com.trabajofinal.service.ConsultaService;
import com.trabajofinal.service.RankingService;
import com.trabajofinal.service.UserService;
import com.trabajofinal.utils.Database;


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

	@RequestMapping(value="/inicio", method=RequestMethod.GET)	
	public String inicio(Model model,  HttpSession session, @RequestParam(value="id", required=false) Integer id, @RequestParam(value="action", required=false) String action) {		
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
		User usu = userService.findByUserName(session.getAttribute("userSession").toString());						
		Consulta consulta = new Consulta(query,usu.getId(),configId,date);				
		Configuracion config = configuracionService.findById(consulta.getIdconfig());
		model.addAttribute("resultados", consulta.gestionarConsulta(config.getName(), database));
		consultaService.save(consulta);
		MachineLearning machineLearning = new MachineLearning(consulta, config);
		Double timeAverage = consultaService.getTimeAverage(consulta.getQuery());
		Ranking ranking = machineLearning.gestionarRanking(database, usu, timeAverage, date);		
		rankingService.save(ranking);
		model.addAttribute("consulta", consulta);
		model.addAttribute("user", usu);		
		return "inicio";
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
		User usu = new User();		
		String nombre_user = session.getAttribute("userSession").toString();
		usu = userService.findByUserName(nombre_user);
		model.addAttribute("user", usu);
		return "redirect:configuracion.html";
	}
}
