package com.opiagile.opibot.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opiagile.opibot.api.entities.Usuario;
import com.opiagile.opibot.api.repositories.UsuarioRepository;
import com.opiagile.opibot.api.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		log.info("Buscando um usuário pelo email {}", email);
		return Optional.ofNullable(usuarioRepository.findByEmail(email));
	}

	@Override
	public Usuario persistir(Usuario usuario) {
		log.info("Persistindo usuário: {}", usuario);
		return this.usuarioRepository.save(usuario);
	}

	
}
