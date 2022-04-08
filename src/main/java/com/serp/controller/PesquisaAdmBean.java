package com.serp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class PesquisaAdmBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Administrador> adms = new ArrayList<Administrador>();
	private Administrador admSelecionado;
	
	@Inject
	AdmService admService;

		
	@PostConstruct
	public void inicializar() {
		adms = admService.findAll();
	}
	
	public void delete() {
		try {
			admService.delete(admSelecionado);			
			this.adms.remove(admSelecionado);
			MessageUtil.sucesso("Administrador " + admSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			MessageUtil.erro(e.getMessage());
		}
	}
	
	
	public List<Administrador> getAdministradores() {
		return adms;
	}

	public Administrador getAdministradorSelecionado() {
		return admSelecionado;
	}

	public void setAdministradorSelecionado(Administrador admSelecionado) {
		this.admSelecionado = admSelecionado;
	}

	
}