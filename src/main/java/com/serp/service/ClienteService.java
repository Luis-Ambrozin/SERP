package com.serp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.serp.dao.ClienteDAO;
import com.serp.modelo.Cliente;
import com.serp.util.NegocioException;

public class ClienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteDAO clienteDAO;


	public void salvar(Cliente cliente) throws NegocioException {		
		this.clienteDAO.salvar(cliente);		
	}	
	
	public List<Cliente> buscarTodos() {
		return clienteDAO.buscarTodos();
	}
	
	public void excluir(Cliente cliente) throws NegocioException {
		clienteDAO.excluir(cliente);		
	}

	
	public Cliente buscarPeloEmail(String email) {
		return clienteDAO.buscarPeloEmail(email);
		
	}
	
	public List<Cliente> buscarPeloNome(String nome){
		return clienteDAO.buscarPeloNome(nome);
	}
	
}