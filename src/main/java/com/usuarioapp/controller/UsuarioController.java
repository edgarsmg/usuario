package com.usuarioapp.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.usuarioapp.entity.Usuario;
import com.usuarioapp.service.IUsuarioService;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		
		List<Usuario> listaUsuarios = usuarioService.listar();
		model.addAttribute("titulo", "Lista Usuarios");
		model.addAttribute("usuarios", listaUsuarios);
		return "listar";
	}
	
	@GetMapping("/crear")
	public String insertar(Model model) {
		
		Usuario usuario = new Usuario();
		
		model.addAttribute("titulo", "Formulario: Nuevo Usuario");
		model.addAttribute("usuario", usuario);
		model.addAttribute("method", "post");
		
		return "frmInsertar";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid @ModelAttribute Usuario usuario, BindingResult result, 
			Model model, @RequestParam("file") MultipartFile imagen) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("titulo", "Formulario: Nuevo Usuario");
			model.addAttribute("usuario", usuario);
			model.addAttribute("method", "post");
			
			return "frmInsertar";
		}
		
		if(!imagen.isEmpty()) {
			Path dirImagenes = Paths.get("src//main//resources//static/images");
			String pathAbs = dirImagenes.toFile().getAbsolutePath();
			
			try {
				byte[] bytesImg = imagen.getBytes();
				Path pathCompl = Paths.get(pathAbs + "//" + imagen.getOriginalFilename());
				Files.write(pathCompl, bytesImg);
				usuario.setImagen(imagen.getOriginalFilename());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		usuarioService.insertar(usuario);
		return "redirect:/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long idUsuario,Model model) {
		
		Usuario usuario = null;
		
		if(idUsuario>0) {
			usuario = usuarioService.buscarUsuario(idUsuario);
			
			if(usuario == null) {
				return "redirect:/listar";
			}
			
		}
		else {
			return "redirect:/listar";
		}
		
		model.addAttribute("titulo", "Formulario: Editar Usuario");
		model.addAttribute("usuario", usuario);
		model.addAttribute("method", "put");
		
		return "frmInsertar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String editar(@PathVariable("id") Long idUsuario) {
		
		Usuario usuario = null;
		
		if(idUsuario>0) {
			usuario = usuarioService.buscarUsuario(idUsuario);
			
			if(usuario == null) {
				return "redirect:/listar";
			}
			
		}
		else {
			return "redirect:/listar";
		}
		
		usuarioService.eliminar(idUsuario);
		return "redirect:/listar";
	}
	
	
	

}
