package com.opiagile.opibot.api.services;

import java.util.Optional;

import com.opiagile.opibot.api.entities.Usuario;

public interface UsuarioService {

	/**
	 * Retorna uma usuario dado por um email.
	 * 
	 * @param email
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorEmail(String email);
	
	/**
	 * Cadastra um novo usuário na base de dados.
	 * 
	 * @param Usuario
	 * @return usuario
	 */
	Usuario persistir(Usuario empresa);

}
