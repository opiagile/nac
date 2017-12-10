package com.opiagile.opibot.api.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opiagile.opibot.api.dto.CadastroUsuarioDTO;
import com.opiagile.opibot.api.entities.Empresa;
import com.opiagile.opibot.api.entities.Usuario;
import com.opiagile.opibot.api.enums.PerfilEnum;
import com.opiagile.opibot.api.response.Response;
import com.opiagile.opibot.api.services.EmpresaService;
import com.opiagile.opibot.api.services.UsuarioService;
import com.opiagile.opibot.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-usuario")
@CrossOrigin(origins = "*")
public class CadastroUsuarioController {

	private static final Logger log = LoggerFactory.getLogger(CadastroUsuarioController.class);

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private EmpresaService empresaService;

	public CadastroUsuarioController() {
	}
	
	/**
	 * Cadastrar um usuário no sistema.
	 * 
	 * @param cadastroUsuarioDTO
	 * @param result
	 * @return ResponseEntity<Response<CadastroUsuarioDTO>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<CadastroUsuarioDTO>> cadastrar(@Valid @RequestBody CadastroUsuarioDTO cadastroUsuarioDTO,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Usuário: {}", cadastroUsuarioDTO.toString());
		Response<CadastroUsuarioDTO> response = new Response<CadastroUsuarioDTO>();

		validarDadosExistentes(cadastroUsuarioDTO, result);
		Empresa empresa = this.converterDtoParaEmpresa(cadastroUsuarioDTO);
		Usuario usuario = this.converterDtoParaUsuario(cadastroUsuarioDTO, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.empresaService.persistir(empresa);
		usuario.setEmpresa(empresa);
		this.usuarioService.persistir(usuario);

		response.setData(this.converterCadastroUsuarioDTO(usuario));
		return ResponseEntity.ok(response);
	}
	
	
	/**
	 * Verifica se a empresa ou usuário já existem na base de dados.
	 * 
	 * @param cadastroUsuarioDTO
	 * @param result
	 */
	private void validarDadosExistentes(CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult result) {
		this.empresaService.buscarPorCnpjCpf(cadastroUsuarioDTO.getCnpjCpf())
				.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente.")));

		this.usuarioService.buscarPorEmail(cadastroUsuarioDTO.getEmail())
				.ifPresent(func -> result.addError(new ObjectError("usuario", "Email já existente.")));
	}
	
	/**
	 * Converte os dados do DTO para empresa.
	 * 
	 * @param cadastroUsuarioDTO
	 * @return Empresa
	 */
	private Empresa converterDtoParaEmpresa(CadastroUsuarioDTO cadastroUsuarioDTO) {
		Empresa empresa = new Empresa();
		empresa.setCnpjCpf(cadastroUsuarioDTO.getCnpjCpf());
		empresa.setRazaoSocial(cadastroUsuarioDTO.getRazaoSocial());

		return empresa;
	}
	
	/**
	 * Converte os dados do DTO para usuário.
	 * 
	 * @param cadastroUsuarioDTO
	 * @param result
	 * @return Usuario
	 * @throws NoSuchAlgorithmException
	 */
	private Usuario converterDtoParaUsuario(CadastroUsuarioDTO cadastroUsuarioDTO, BindingResult result)
			throws NoSuchAlgorithmException {
		Usuario usuario = new Usuario();
		usuario.setNome(cadastroUsuarioDTO.getNome());
		usuario.setEmail(cadastroUsuarioDTO.getEmail());
		usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
		usuario.setSenha(PasswordUtils.gerarBCrypt(cadastroUsuarioDTO.getSenha()));

		return usuario;
	}

	/**
	 * Popula o DTO de cadastro com os dados do usuário e empresa.
	 * 
	 * @param usuario
	 * @return CadastroUsuarioDto
	 */
	private CadastroUsuarioDTO converterCadastroUsuarioDTO(Usuario usuario) {
		CadastroUsuarioDTO cadastroUsuarioDTO = new CadastroUsuarioDTO();
		cadastroUsuarioDTO.setId(usuario.getId());
		cadastroUsuarioDTO.setNome(usuario.getNome());
		cadastroUsuarioDTO.setEmail(usuario.getEmail());
		cadastroUsuarioDTO.setCnpjCpf(usuario.getEmpresa().getCnpjCpf());
		cadastroUsuarioDTO.setRazaoSocial(usuario.getEmpresa().getRazaoSocial());
		cadastroUsuarioDTO.setSenha(usuario.getSenha());
		cadastroUsuarioDTO.setPerfil(usuario.getPerfil());
		cadastroUsuarioDTO.setPlano(usuario.getEmpresa().getPlano());

		return cadastroUsuarioDTO;
	}
	
}
