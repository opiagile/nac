package com.opiagile.opibot.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.opiagile.opibot.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	@Transactional(readOnly = true)
	Empresa findByCnpjCpf(String cnpjCpf);		

}
