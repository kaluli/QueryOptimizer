package com.trabajofinal.controller;


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

import com.trabajofinal.model.User;
import com.trabajofinal.model.UserLogin;
import com.trabajofinal.model.UserProfile;
import com.trabajofinal.service.UserProfileService;
import com.trabajofinal.service.UserService;

@Controller
@SessionAttributes("userSession") //Spring obtiene una instancia de la session
public class LoginController {
	
	@Autowired
	private UserService userService;
	private UserProfileService userProfileService;
		
	@RequestMapping(value="/registro", method=RequestMethod.GET)	
	public String registro(Model model, HttpSession session) {
		if(session.getAttribute("userSession") != null)
			return "redirect:login.html";
		User user = new User();
		//UserProfile UserProfile = new UserProfile();
		//user.setUserProfile(UserProfile);		

		model.addAttribute("user", user);		
		
		return "registro";
	}
	
	@RequestMapping(value="/registro", method=RequestMethod.POST)
	public String registro(@Valid @ModelAttribute("user") User user, @ModelAttribute("userProfile") UserProfile user_profile, BindingResult result, Model model, HttpSession session) {		
		if(result.hasErrors()) {
			return "registro";
		} else if(userService.findByUserName(user.getUsername()) != null) {
			model.addAttribute("message", "El nombre de user existe. Prueba con otro");
			return "registro";
		} else {
		/*	String pass = user.getPassword();
			MD5Encoder encoder = new MD5Encoder;
			String hashedPass = encoder.encode(pass.getBytes());
			user.setPassword(hashedPass);*/	
			//user.setUserProfile(user_profile);
			userService.save(user);		
			userProfileService.save(user_profile);			

			//userService.save(user);
			session.setAttribute("userSession", user.getUsername());
			model.addAttribute("message", "Se guardaron los datos del user");
			//model.addAttribute("user", session.getAttribute("user"));
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
