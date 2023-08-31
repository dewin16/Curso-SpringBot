package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JacksonInject.Value;

@RestController
@RequestMapping("/index")

public class IndexController {
	
	//RequestMapping es la formula principal, dentro de este se deberia de especificar el metodo. El metodo default es Get
	
	//@RequestMapping(value="/index, method=RequestMethod.Get) seria el ejemplo de la frase anterior
	
	//Utilizamos GetMapping en lugar de RequestMapping porque te ahorrarias especificar el metodo ya que este se incluye en el @
	
	
	@GetMapping(value = {"/index","/","/home"})
	
	public String index() {
		return "index";
	}

}
