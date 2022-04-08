package com.serp.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.serp.modelo.Administrador;
import com.serp.service.AdmService;
import com.serp.util.MessageUtil;
import com.serp.util.NegocioException;
@Named
@ViewScoped
public class CadastroAdmBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Administrador adm;
	
	@Inject
	private AdmService admService;
	
	
	public void save() {
		try {
			
			this.admService.save(adm);
			MessageUtil.sucesso("Administrador salvo com sucesso!");
		} catch (NegocioException e) {
			MessageUtil.erro(e.getMessage());
		} catch (Exception ex) {
			MessageUtil.erro(ex.getMessage());
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar(){
		limpar();		
	}	

	public void limpar() {
		this.adm = new Administrador();		
	}	

	public Administrador getAdministrador() {
		return adm;
	}

	public void setAdministrador(Administrador adm) {
		this.adm = adm;
	}
	

}
