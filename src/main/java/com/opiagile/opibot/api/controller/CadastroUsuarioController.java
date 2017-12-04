package com.opiagile.opibot.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.opiagile.opibot.api.dto.CadastroUsuarioDTO;
import com.opiagile.opibot.api.entities.Usuario;
import com.opiagile.opibot.api.enums.PerfilEnum;
import com.opiagile.opibot.api.response.Response;
import com.opiagile.opibot.api.services.UsuarioService;
import com.opiagile.opibot.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-usuario")
@CrossOrigin(origins = "*")
public class CadastroUsuarioController {

	private static final Logger log = LoggerFactory.getLogger(CadastroUsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	public CadastroUsuarioController() {
	}

	/**
	 * Cadastra um usuário no sistema.
	 * 
	 * @param CadastroUsuarioDTO
	 * @param result
	 * @return ResponseEntity<Response<CadastroUsuarioDTO>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroUsuarioDTO>> cadastrar(
			@Valid @RequestBody CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Usuário: {}", cadastroUsuarioDTO.toString());
		Response<CadastroUsuarioDTO> response = new Response<CadastroUsuarioDTO>();

		validarDadosExistentes(cadastroUsuarioDTO, result);
		Usuario funcionario = this.ConverterDTOParaUsuario(cadastroUsuarioDTO, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
/*
		Optional<Usuario> empresa = this.usuarioService.buscarPorCnpj(cadastroPFDto.getCnpj());
		empresa.ifPresent(emp -> funcionario.setEmpresa(emp));
		this.funcionarioService.persistir(funcionario);

		response.setData(this.converterCadastroPFDto(funcionario));
*/
		return ResponseEntity.ok(response);

	}

	/**
	 * Verifica se o usuário já existe na base de dados.
	 * 
	 * @param CadastroUsuarioDTO
	 * @param result
	 */
	private void validarDadosExistentes(CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult result) {
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(cadastroUsuarioDTO.getEmail());
		if (!usuario.isPresent()) {
			result.addError(new ObjectError("email", "Email não cadastrado."));
		}
	}

	/**
	 * Converte os dados do DTO para usuário.
	 * 
	 * @param cadastroUsuarioDTO
	 * @param result
	 * @return Usuário
	 * @throws NoSuchAlgorithmException
	 */
	private Usuario ConverterDTOParaUsuario(CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setNome(cadastroUsuarioDTO.getNome());
		usuario.setEmail(cadastroUsuarioDTO.getEmail());
		usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
		usuario.setSenha(PasswordUtils.gerarBCrypt(cadastroUsuarioDTO.getSenha()));
		usuario.setDataCriacao(cadastroUsuarioDTO.getDataCriacao());
		usuario.setDataAtualizacao(cadastroUsuarioDTO.getDataAtualizacao());		
		usuario.setNome(cadastroUsuarioDTO.getNome());
		
		return usuario;
	}

	/**
	 * Popula o DTO de cadastro com os dados do usuário.
	 * 
	 * @param usuario
	 * @return CadastroUsuarioDTO
	 */
	private CadastroUsuarioDTO converterCadastroUsuarioDTO(Usuario usuario) {
		CadastroUsuarioDTO cadastroUsuarioDTO = new CadastroUsuarioDTO();
		cadastroUsuarioDTO.setId(usuario.getId());
		cadastroUsuarioDTO.setNome(usuario.getNome());
		cadastroUsuarioDTO.setEmail(usuario.getEmail());
		cadastroUsuarioDTO.setPerfil(usuario.getPerfil());
		cadastroUsuarioDTO.setSenha("");
		cadastroUsuarioDTO.setDataCriacao(usuario.getDataCriacao());
		cadastroUsuarioDTO.setDataAtualizacao(usuario.getDataAtualizacao());

		return cadastroUsuarioDTO;
	}
}
