package com.opiagile.opibot.api.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.opiagile.opibot.api.enums.PerfilEnum;

public class CadastroUsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private PerfilEnum perfil;
	private Date dataCriacao;
	private Date dataAtualizacao;

	public CadastroUsuarioDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 100, message = "Nome deve conter entre 3 e 100 caracteres.")	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 50, message = "Email deve conter entre 5 e 50 caracteres.")
	@Email(message="Email inválido.")	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty(message = "Senha não pode ser vazia.")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
		
	@Override
	public String toString() {
		return "CadastroUsuarioDTO [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", perfil="
				+ perfil + ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + "]";
	}
	
}
