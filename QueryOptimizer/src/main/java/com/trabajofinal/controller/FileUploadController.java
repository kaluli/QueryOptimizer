package com.trabajofinal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
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

import com.trabajofinal.model.Configuracion;
import com.trabajofinal.model.User;
import com.trabajofinal.service.ConfiguracionService;
import com.trabajofinal.service.UserService;

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
    	String fullName = file.getOriginalFilename();
    	String name = FilenameUtils.getBaseName(fullName);
    	String path = "trabajo_final/";
        
    	if (!file.isEmpty()) {
            try {            	
                byte[] bytes = file.getBytes();                
                User usu = userService.findByUserName(session.getAttribute("userSession").toString());
            	
                if (configuracionService.findByName(name, user.getId()) == null) {            	    	            
                	BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(path + new File(name)));                                            	
            	
            		Configuracion configuracion = new Configuracion(
            				usu.getId(),
            				name,			
            				file.getOriginalFilename(),
            				"jdbc:mysql://127.0.0.1:3306/" + name,
            				date
        				);
	            	
	            	configuracionService.save(configuracion);
	            	stream.write(bytes);
	                stream.close();
	                return "redirect:configuracion.html";
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