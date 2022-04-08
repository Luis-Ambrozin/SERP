package com.serp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.serp.modelo.Cliente;
import com.serp.service.ClienteService;
import com.serp.util.MessageUtil;
import com.serp.util.NegocioException;


@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Cliente clienteSelecionado;
	
	@Inject
	ClienteService clienteService;

		
	@PostConstruct
	public void inicializar() {
		clientes = clienteService.buscarTodos();
	}
	
	public void excluir() {
		try {
			clienteService.excluir(clienteSelecionado);			
			this.clientes.remove(clienteSelecionado);
			MessageUtil.sucesso("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			MessageUtil.erro(e.getMessage());
		}
	}
	
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	
}