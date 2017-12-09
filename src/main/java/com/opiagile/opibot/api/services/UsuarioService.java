package com.opiagile.opibot.api.services;

import java.util.Optional;

import com.opiagile.opibot.api.entities.Usuario;

public interface UsuarioService {

	/**
	 * Retorna um usuário buscando por Email.
	 * 
	 * @param email
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorEmail(String email);

	/**
	 * Retorna um usuário buscando por Nome.
	 * 
	 * @param nome
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorNome(String nome);

	/**
	 * Cadastra um novo usuário na base de dados.
	 * 
	 * @param usuario
	 * @return Usuario
	 */
	Usuario persistir(Usuario usuario);

	/**
	 * Busca e retorna um usuário por ID.
	 * 
	 * @param id
	 * @return Optional<Usuario>
	 */
	Optional<Usuario> buscarPorId(Long id);
	
}
