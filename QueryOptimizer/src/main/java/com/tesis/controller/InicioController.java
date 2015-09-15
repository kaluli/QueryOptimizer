package com.tesis.controller;

import java.util.Date;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tesis.model.Configuracion;
import com.tesis.model.Consulta;
import com.tesis.model.User;
import com.tesis.service.ConfiguracionService;
import com.tesis.service.ConsultaService;
import com.tesis.service.UserService;


@Controller
@SessionAttributes("userSession") //Spring obtiene una instancia de la session
public class InicioController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ConsultaService consultaService;
	
	@Autowired
	private ConfiguracionService configuracionService;
	
	@RequestMapping(value="/inicio", method=RequestMethod.GET)	
	public String inicio(Model model,  HttpSession session, @RequestParam(value="id", required=false) Integer id, @RequestParam(value="action", required=false) String action) {		
		User usu = new User();	
		
		String nombre_user = session.getAttribute("userSession").toString();
		usu = userService.findByUserName(nombre_user);
		if (usu != null){
			int idlogueado = usu.getId();
			model.addAttribute("logueado", idlogueado);
		}			
		
		if(id == null){ //Mi perfil			
			model.addAttribute("yomismo", true);		
			model.addAttribute("config", usu.getConfiguraciones());					
		}
		
		model.addAttribute("user", usu);									
		return "inicio";
	}

	@RequestMapping(value="/inicio", method=RequestMethod.POST)
	public String inicio(@RequestParam("query") String query, @RequestParam("configId") Integer configId, @Valid @ModelAttribute("usuario") User usuario, BindingResult result, Model model, HttpSession session) {
    	Date date = new Date();
		User usu = userService.findByUserName(session.getAttribute("userSession").toString());						
		
		// Guarda en log
		Consulta consulta = new Consulta(query,usu.getId(),configId,date);		
		consultaService.save(consulta);
		String db = configuracionService.findById(consulta.getIdconfig()).getName();
		List<Map<String, Object>> resultados = consulta.gestionarConsulta(consulta, db);
		
		Iterator<Map<String, Object>> it = resultados.iterator(); 
        while (it.hasNext()) { 
            System.out.println("" + it.next()); 
        }
        
        model.addAttribute("resultados", resultados);
		model.addAttribute("consultas", usu.getConsultas().size());
		model.addAttribute("user", usu);
		model.addAttribute("config", usu.getConfiguraciones());		
		
		return "inicio";
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
