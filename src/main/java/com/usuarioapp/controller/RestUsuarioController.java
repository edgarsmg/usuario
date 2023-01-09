package com.usuarioapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarioapp.entity.Usuario;
import com.usuarioapp.service.IUsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class RestUsuarioController {
	
	@Autowired
	IUsuarioService usuarioService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/lista")
	public List<Usuario> listar(){
		return usuarioService.listar();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/insertar")
	public Usuario insertar(@RequestBody Usuario usuarioI) {
		return usuarioService.insertar(usuarioI);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/actualizar")
	public Usuario actualizar(@RequestBody Usuario usuarioI) {
		return usuarioService.actualizar(usuarioI);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/eliminar")
	public void eliminar(@RequestBody Usuario usuarioI) {
		usuarioService.delete(usuarioI);
	}

}
