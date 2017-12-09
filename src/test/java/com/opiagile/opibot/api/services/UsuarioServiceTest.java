package com.opiagile.opibot.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.opiagile.opibot.api.entities.Usuario;
import com.opiagile.opibot.api.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@MockBean
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	private static final String EMAIL = "teste@teste.com.br";
	private static final String NOME = "Nome Teste";
	private static final Long ID = 1L;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.usuarioRepository.findByEmail(Mockito.anyString())).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.findById(Mockito.anyLong())).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.findByNome(Mockito.anyString())).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class))).willReturn(new Usuario());
	}
	
	@Test
	public void testPersistirUsuario() {
		Usuario usuario = this.usuarioService.persistir(new Usuario());

		assertNotNull(usuario);
	}	
	
	@Test
	public void testBuscarEmpresaPorEmail() {
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(EMAIL);

		assertTrue(usuario.isPresent());
	}	

	@Test
	public void testBuscarEmpresaPorNome() {
		Optional<Usuario> usuario = this.usuarioService.buscarPorNome(NOME);

		assertTrue(usuario.isPresent());
	}	

	@Test
	public void testBuscarEmpresaPorId() {
		Optional<Usuario> usuario = this.usuarioService.buscarPorId(ID);

		assertTrue(usuario.isPresent());
	}	
	
}
