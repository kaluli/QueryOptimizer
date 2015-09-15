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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tesis.model.User;
import com.tesis.model.UserLogin;
import com.tesis.service.UserService;

@Controller
@SessionAttributes("userSession") //Spring obtiene una instancia de la session
public class LogController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/logs", method=RequestMethod.GET)
	public String mostrar(Model model, HttpSession session) {		
		if(session.getAttribute("userSession") == null)
			return "redirect:login.html";
		
			String nombre_user = session.getAttribute("userSession").toString();
			User usu = userService.findByUserName(nombre_user);
			
			model.addAttribute("user", usu);
			model.addAttribute("logueado", true);	
			return "logs";
		}
					
	@RequestMapping(value="/logs", method=RequestMethod.POST)
	public String micuenta(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result, HttpSession session, Model model ) {
		if (result.hasErrors()) {
			model.addAttribute("error");			
			return "redirect:login.html";
		} else {
			if (userService.findByLogin(userLogin.getUser(), userLogin.getPassword()) != null) {
				model.addAttribute("logueado", true);					
				session.setAttribute("userSession", userLogin.getUser());			
				model.addAttribute("userLogin", userLogin);
				return "redirect:logs.html";
			} else {		
				model.addAttribute("error");							
				return "redirect:login.html";
			}
		}		
	}	
}
