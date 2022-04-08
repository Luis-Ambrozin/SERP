package com.serp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.serp.modelo.Cliente;
import com.serp.modelo.enums.Sexo;
import com.serp.service.ClienteService;
import com.serp.util.MessageUtil;
import com.serp.util.NegocioException;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;
	private List<Sexo> sexos;
	
	@Inject
	private ClienteService clienteService;
	
	
	public void salvar() {
		try {
			
			this.clienteService.salvar(cliente);
			MessageUtil.sucesso("Cliente salvo com sucesso!");
		} catch (NegocioException e) {
			MessageUtil.erro(e.getMessage());
		} catch (Exception ex) {
			MessageUtil.erro(ex.getMessage());
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void inicializar(){
		sexos = Arrays.asList(Sexo.values());
		limpar();		
	}	

	public void limpar() {
		this.cliente = new Cliente();		
	}	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Sexo> getSexos() {
		return sexos;
	}
	
}
