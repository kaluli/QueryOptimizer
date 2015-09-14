package com.tesis.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.tesis.model.Configuracion;
//import com.tesis.model.Database;
import com.tesis.model.User;
import com.tesis.service.UserService;


@Controller
@SessionAttributes("userSession") //Spring obtiene una instancia de la session
public class InicioController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/inicio", method=RequestMethod.GET)	
	public String inicio(Model model,  HttpSession session, @RequestParam(value="id", required=false) Integer id, @RequestParam(value="action", required=false) String action) {		
		User usu = new User();	
		
		String nombre_user = session.getAttribute("userSession").toString();
		usu = userService.findByUserName(nombre_user);
		if (usu != null){
			int idlogueado = usu.getId();
			model.addAttribute("logueado", idlogueado);
		}			
		
		//Configuracion configuracion = new Configuracion();		
		//model.addAttribute("configuracion", configuracion);
		
		if(id == null){ //Mi perfil			
			model.addAttribute("yomismo", true);		
			model.addAttribute("config", usu.getConfiguraciones());					
		}
		
		model.addAttribute("user", usu);									
		return "inicio";
	}

	@RequestMapping(value="/inicio", method=RequestMethod.POST)
	//public String inicio(@Valid @ModelAttribute("configuracion") Database configuracion, BindingResult result, HttpSession session, Model model ) {
	public String inicio(BindingResult result, HttpSession session, Model model ) {
		String nombre_user = session.getAttribute("userSession").toString();
		User usu = new User();					
		usu = userService.findByUserName(nombre_user);
		model.addAttribute("user", usu);
		return "redirect:inicio.html";
	}
	
	//RequestParam va a ser requerido
	@RequestMapping(value="/buscar", method=RequestMethod.GET)
	public @ResponseBody Model buscar(Model model, @RequestParam(value="buscar", required=false) String buscar, HttpSession session) {
		boolean logueado;
		if(session.getAttribute("userSession") == null)
			logueado = false;
		else
			logueado= true;
		
		if(session.getAttribute("userSession") != null){
			session.setAttribute("userSession",session.getAttribute("userSession"));
			String nombre_user = session.getAttribute("userSession").toString();
			User usu = userService.findByUserName(nombre_user);
			model.addAttribute("user", usu);
		//	model.addAttribute("siguiendo", usu.getSiguiendo());
/*			 for(int i = 0; i < usu.getSeguidores().size(); i++) {
		            System.out.println(usu.getSeguidores().get(i).getIdSeguidor());

		        }*/
			if ((buscar != null)){
				//Todos los users que matchean con la búsqueda
				//List<User> users = userService.findbyName(buscar);				
				//model.addAttribute("users", users);
				//Seguidores del user que inició sesión
				/*user usu = userService.findByUserName();
				 for(int i = 0; i < usu.getSeguidores().size(); i++) {
			            System.out.println(usu.getSeguidores().get(i).getIduser());
			        }
				model.addAttribute("seguidores", usu.getSeguidores());*/		
				model.addAttribute("logueado", logueado);	
				model.addAttribute("yomismo", session.getAttribute("userSession"));
			}
		}
	    return model;
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
