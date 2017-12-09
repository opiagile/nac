package com.opiagile.opibot.api.services;

import java.util.Optional;

import com.opiagile.opibot.api.entities.Empresa;

public interface EmpresaService {

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpjCpf
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpjCpf(String cnpjCpf);
	
	/**
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);

}
