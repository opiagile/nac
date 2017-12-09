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

import com.opiagile.opibot.api.entities.Usuario;
import com.opiagile.opibot.api.enums.PerfilEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private static final String EMAIL = "teste@teste.com";
	private static final String NOME = "nome usuario";
	private static final Long ID = 2L;
	
	@Before
	public void setUp() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setNome(NOME);
		usuario.setEmail(EMAIL);
		usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
		usuario.setSenha("aaaa-bbbb-cccc");
		usuario.setDataAtualizacao(new Date());
		usuario.setDataCriacao(new Date());
		this.usuarioRepository.save(usuario);
	}
	
	@After
    public final void tearDown() { 
		this.usuarioRepository.deleteAll();
	}

	@Test
	public void testBuscarPorEmail() {
		Usuario usuario = this.usuarioRepository.findByEmail(EMAIL);
		
		assertEquals(EMAIL, usuario.getEmail());
	}

	@Test
	public void testBuscarPorNome() {
		Usuario usuario = this.usuarioRepository.findByNome(NOME);
		
		assertEquals(NOME, usuario.getNome());
	}

	@Test
	public void testBuscarPorId() {
		Usuario usuario = this.usuarioRepository.findById(ID);
		
		assertEquals(ID, usuario.getId());
	}
	
}
