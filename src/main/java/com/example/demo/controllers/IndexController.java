package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Usuario;


@Controller

//Añadir "/index" al @Request mapping hace que tengas que añadir /index en la busqueda y por lo tanto
//localhost:8080 no devuelve nada


@RequestMapping("/index")

public class IndexController {
	
//RequestMapping es la formula principal, dentro de este se deberia de especificar el metodo. El metodo default es Get
	
//@RequestMapping(value="/index, method=RequestMethod.Get) seria el ejemplo de la frase anterior
	
//Utilizamos GetMapping en lugar de RequestMapping porque te ahorrarias especificar el metodo ya que este se incluye en el @
	
	
	@GetMapping(value = {"/index","/","","/home"})
	
	
	public String index(Model model) {
		model.addAttribute("titulo", "42");
		// index es la vista,el archivo html creado
		return "index"; 
		
	}
	
	@RequestMapping("/perfil")
	
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Paco");
		usuario.setApellido("Salas");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Perfil del usuario: ".concat(usuario.getNombre()));
		
		return "perfil";
		
	}

}
