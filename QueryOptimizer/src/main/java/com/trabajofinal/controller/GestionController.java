package com.trabajofinal.controller;


import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.trabajofinal.model.User;
import com.trabajofinal.model.UserLogin;
import com.trabajofinal.service.UserService;

@Controller
@SessionAttributes("userSession")
public class GestionController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/cargar_database", method=RequestMethod.GET)
	public String cargarDatabase(Model model, HttpSession session){
	  	SingleConnectionDataSource ds = new SingleConnectionDataSource();
	    ds.setDriverClassName("com.mysql.jdbc.Driver");
	   
	    ds.setUrl("jdbc:mysql://127.0.0.1:3306/tesis");
	    ds.setUsername("root");
	    ds.setPassword("kaluli32");
	    JdbcTemplate jt = new JdbcTemplate(ds);	    	
	    
	    StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        /*try {
            reader = new BufferedReader(new FileReader("1.sql"));
            String line = null;
            while ((line = reader.readLine()) != null)
                builder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuietly(reader);

        }
        */
	    jt.execute(builder.toString());
	    return null;

	}
	
	private void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ignored) {}
        }
    }
	
	@RequestMapping(value="/micuenta", method=RequestMethod.GET)
	public String mostrar(Model model, HttpSession session) {		
		if(session.getAttribute("userSession") == null)
			return "redirect:login.html";
		
			String nombre_user = session.getAttribute("userSession").toString();
			User usu = userService.findByUserName(nombre_user);
			model.addAttribute("user", usu);
			model.addAttribute("logueado", true);	
			return "micuenta";
		}
					
	@RequestMapping(value="/micuenta", method=RequestMethod.POST)
	public String micuenta(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result, HttpSession session, Model model ) {
		if (result.hasErrors()) {
			model.addAttribute("error");			
			return "redirect:login.html";
		} else {
			if (userService.findByLogin(userLogin.getUser(), userLogin.getPassword()) != null) {
				model.addAttribute("logueado", true);					
				session.setAttribute("userSession", userLogin.getUser());			
				model.addAttribute("userLogin", userLogin);
				return "redirect:micuenta.html";
			} else {		
				model.addAttribute("error");							
				return "redirect:login.html";
			}
		}		
	}	
	
	@RequestMapping(value="/notificaciones", method=RequestMethod.GET)
	public String notifica(Model model, HttpSession session) {		
		if(session.getAttribute("userSession") == null)
			return "redirect:login.html";
			
			model.addAttribute("logueado", true);			
			User user= new User();		
			model.addAttribute("user", user);		
			return "notificaciones";
		}
					
	@RequestMapping(value="/notificaciones", method=RequestMethod.POST)
	public String notificar(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result, HttpSession session, Model model ) {
		if (result.hasErrors()) {
			model.addAttribute("error");			
			return "redirect:login.html";
		} else {
			if (userService.findByLogin(userLogin.getUser(), userLogin.getPassword()) != null) {
				model.addAttribute("logueado", true);					
				session.setAttribute("userSession", userLogin.getUser());			
				model.addAttribute("userLogin", userLogin);
				return "redirect:notificaciones.html";
			} else {		
				model.addAttribute("error");							
				return "redirect:login.html";
			}
		}		
	}	
	
	@RequestMapping(value="/recuperar_password", method=RequestMethod.GET)
	public String recuperar_password(Model model, HttpSession session) {
		if(session.getAttribute("userSession") == null)
			return "redirect:login.html";
		
			String nombre_user = session.getAttribute("userSession").toString();
			User usu = userService.findByUserName(nombre_user);
			model.addAttribute("user", usu);
		return "recuperar_password";
		
	}
	
	@RequestMapping(value="/recuperar_password", method=RequestMethod.POST)
	public String recuperar_password(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result, HttpSession session, Model model ) {
		if (result.hasErrors()) {
			model.addAttribute("error");			
			return "recuperar_password";

		} else {
			if (userService.findByLogin(userLogin.getUser(), userLogin.getPassword()) != null) {					
				session.setAttribute("userSession", userLogin.getUser());			
				model.addAttribute("userLogin", userLogin);
				return "redirect:profile.html";
			} else {		
				model.addAttribute("error");							
				return "recuperar_password";
			}
		}		
	}
	
	@RequestMapping(value="/cambiarpassword", method=RequestMethod.GET)	
	public String cambiarpassword(Model model, HttpSession session) {
		User user= new User();		
		model.addAttribute("user", user);		
		return "cambiarpassword";
	}
	
	@RequestMapping(value="/cambiarpassword", method=RequestMethod.POST)
	public String cambiarpassword(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {		
		if(result.hasErrors()) {
			return "cambiarpassword";
		} else if(userService.findByUserName(user.getUsername()) != null) {
			model.addAttribute("message", "El nombre de user existe. Prueba con otro");
			return "cambiarpassword";
		} else {
		/*	String pass = user.getPassword();
			MD5Encoder encoder = new MD5Encoder;
			String hashedPass = encoder.encode(pass.getBytes());
			user.setPassword(hashedPass);*/	       
			userService.save(user);
			session.setAttribute("userSession", user.getUsername());
			model.addAttribute("message", "Se guardaron los datos del user");
			//model.addAttribute("user", session.getAttribute("user"));
			return "redirect:login.html";
		}
	}
}
