package com.opiagile.opibot.api.repositories;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.opiagile.opibot.api.entities.Empresa;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	private static final String cnpjCpf = "20715815000164";
	
	@Before
	public void setUp() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setCnpjCpf(cnpjCpf);
		empresa.setDataAtualizacao(new Date());
		empresa.setDataCriacao(new Date());
		this.empresaRepository.save(empresa);
	}
	
	@After
    public final void tearDown() { 
		this.empresaRepository.deleteAll();
	}
	
	@Test
	public void testBuscarPorCnpjCpf() {
		Empresa empresa = this.empresaRepository.findByCnpjCpf(cnpjCpf);
		
		assertEquals(cnpjCpf, empresa.getCnpjCpf());
	}
	
}
