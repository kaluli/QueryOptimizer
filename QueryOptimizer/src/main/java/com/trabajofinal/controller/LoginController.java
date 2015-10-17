package com.trabajofinal.controller;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import com.trabajofinal.model.User;
import com.trabajofinal.model.UserLogin;
import com.trabajofinal.service.UserService;

@Controller
@SessionAttributes("userSession") //Spring obtiene una instancia de la session
public class LoginController {
	
	@Autowired
	private UserService userService;
		
	@RequestMapping(value="/registro", method=RequestMethod.GET)	
	public String registro(Model model, HttpSession session) {
		if(session.getAttribute("userSession") != null)
			return "redirect:login.html";
		User user = new User();		
		model.addAttribute("user", user);		
		return "registro";
	}
	
	@RequestMapping(value="/registro", method=RequestMethod.POST)
	public String registro(@Valid @ModelAttribute("usuario") User user, BindingResult result, Model model, HttpSession session) {		
		if(result.hasErrors()) {
			return "registro";
		} else if(userService.findByUserName(user.getUser()) != null) {
			model.addAttribute("message", "El nombre de usuario existe. Prueba con otro");
			return "registro";
		} else {
			Hasher hasher = Hashing.md5().newHasher();
			hasher.putString(user.getPassword());			
			user.setPassword(hasher.hash().toString());				      
			userService.save(user);
			session.setAttribute("userSession", user.getUser());
			model.addAttribute("message", "Se guardaron los datos del usuario");
			//model.addAttribute("usuario", session.getAttribute("usuario"));
			return "redirect:login.html";
		}
	}
	
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model, HttpSession session) {	
		if (session.getAttribute("userSession") != null){						
			session.setAttribute("userSession", session.getAttribute("userSession"));
			return "redirect:inicio.html";
		}	
		else{	
			UserLogin userLogin = new UserLogin();		
			model.addAttribute("userLogin", userLogin);
			return "login";
		}		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult result, HttpSession session, Model model ) {
		if (result.hasErrors()) {
			model.addAttribute("error");			
			return "redirect:login.html";
		} else {
			if (userService.findByLogin(userLogin.getUser(), userLogin.getPassword()) != null) {					
				session.setAttribute("userSession", userLogin.getUser());			
				model.addAttribute("userLogin", userLogin);
				return "redirect:inicio.html";
			} else {		
				model.addAttribute("error");							
				return "redirect:login.html";
			}
		}		
	}
	
	
	
}
