package com.usuarioapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.usuarioapp.entity.Usuario;
import com.usuarioapp.repository.UsuarioRepository;

@Service
public class IUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepositoryI; 
	
	public List<Usuario> listar(){
		return (List<Usuario>) usuarioRepositoryI.findAll();
	}
	
	public Usuario insertar(Usuario usuarioI) {
		return usuarioRepositoryI.save(usuarioI);
	}
	
	public Usuario actualizar(Usuario usuarioI) {
		return usuarioRepositoryI.save(usuarioI);
	}
	
	public Usuario buscarUsuario(Long id) {
		return usuarioRepositoryI.findById(id).orElse(null);
	}
	
	public void eliminar(Long id) {
		usuarioRepositoryI.deleteById(id);
	}
	
	public void delete(Usuario usuarioI) {
		usuarioRepositoryI.delete(usuarioI);
	}

}
