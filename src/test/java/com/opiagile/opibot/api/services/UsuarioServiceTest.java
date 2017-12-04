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
import com.opiagile.opibot.api.services.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@MockBean
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	private static final String EMAIL = "email@email.com";

	@Before
	public void setUp() throws Exception {

		BDDMockito.given(this.usuarioRepository.findByEmail(Mockito.anyString())).willReturn(new Usuario());
		BDDMockito.given(this.usuarioRepository.save(Mockito.any(Usuario.class))).willReturn(new Usuario());
	}

	@Test
	public void testBuscarUsuarioPorEmail() {
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(EMAIL);

		assertTrue(usuario.isPresent());
	}

	@Test
	public void testPersistirUsuario() {
		Usuario usuario = this.usuarioService.persistir(new Usuario());

		assertNotNull(usuario);
	}
	
}
