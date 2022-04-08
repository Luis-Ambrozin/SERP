package com.serp.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.serp.dao.ProdutoDAO;
import com.serp.modelo.Produto;
import com.serp.util.NegocioException;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDAO produtoDAO;

	public void salvar(Produto produto) throws NegocioException {
		this.produtoDAO.salvar(produto);
	}

	public List<Produto> buscarTodos() {
		return produtoDAO.buscarTodos();
	}

	public void excluir(Produto produto) throws NegocioException {
		produtoDAO.excluir(produto);
	}

	public Produto buscarPelaMarca(String marca) {
		return produtoDAO.buscarPelaMarca(marca);

	}

	public List<Produto> buscarPeloNome(String nome) {
		return produtoDAO.buscarPeloNome(nome);
	}

}