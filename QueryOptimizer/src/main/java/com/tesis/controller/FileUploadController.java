package com.tesis.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tesis.model.Configuracion;
import com.tesis.model.User;
import com.tesis.service.ConfiguracionService;
import com.tesis.service.UserService;

@Controller
public class FileUploadController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ConfiguracionService configuracionService;
	
    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "Puede subir un archivo poniendo esta misma URL.";
    }

 
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public String handleFileUpload(@Valid @ModelAttribute("user") User user,
    		BindingResult result, HttpSession session, Model model, 
            @RequestParam("file") MultipartFile file){
    	Date date = new Date();
    	String name = file.getOriginalFilename();    	
    	String path = "trabajo_final/";
        
    	if (!file.isEmpty()) {
            try {            	
                byte[] bytes = file.getBytes();                
                User usu = userService.findByUserName(session.getAttribute("userSession").toString());
            	
                if (configuracionService.findByName(name) == null) {            	    	            
                	BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(path + new File(name)));                                            	
            	
            		Configuracion configuracion = new Configuracion(
            				usu.getId(),
            				file.getOriginalFilename(),            				
            				file.getOriginalFilename(),
            				date
        				);
	            	
	            	configuracionService.save(configuracion);
	            	stream.write(bytes);
	                stream.close();
	                return "redirect:/cargar_database.html";
            	}
            	else{
            		model.addAttribute("message", "Error al subir " + name + " el archivo ya existe.");            		
            	}                
            } catch (Exception e) {
                return "Error al subir " + name + " => " + e.getMessage();
            }
        } else {
            return "Error al subir " + name + " el archivo estaba vacío.";
        }
        
		return "redirect:configuracion.html";
    }

}