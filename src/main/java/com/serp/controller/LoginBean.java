package com.serp.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;

import com.serp.modelo.Administrador;
import com.serp.service.AdmService;
import com.serp.util.MessageUtil;

@Named
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	AdmService admService;
	@Column(unique=true)
	private String email;
	private String senha;
	private Administrador adm;

	private Logger log = Logger.getLogger(LoginBean.class);

	public void inicializar() {
		log.info("Login bean inicializar...");
	}

	public Administrador isValidLogin() {
		System.out.println(admService.findByEmail(email));
		log.info("isValidLogin...");
		adm = (Administrador) admService.findByEmail(email);
		
		if (adm != null) {
			if (!senha.equals(adm.getSenha())) {
				throw new RuntimeException("senha inválida");
			}
		}
		return adm;

	}

	public String entrar() {
		Administrador adm = isValidLogin();
		log.info("Entrando...");
		
		if (adm != null) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			if ((Administrador) request.getSession().getAttribute("adm") == null) {
				log.info("usuario não logado:" + adm.getNome());
			} else {
				MessageUtil.alerta("Usuário já está logado");
			}
		}
		return "Home.xhtml";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Administrador getAdministrador() {
		return adm;
	}

	public void setAdministrador(Administrador adm) {
		this.adm = adm;
	}

}
