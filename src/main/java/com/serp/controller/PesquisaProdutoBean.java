package com.serp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.serp.modelo.Produto;
import com.serp.service.ProdutoService;
import com.serp.util.MessageUtil;
import com.serp.util.NegocioException;


@Named
@ViewScoped
public class PesquisaProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	private Produto produtoSelecionado;
	
	@Inject
	ProdutoService produtoService;

		
	@PostConstruct
	public void inicializar() {
		produtos = produtoService.buscarTodos();
	}
	
	public void excluir() {
		try {
			produtoService.excluir(produtoSelecionado);			
			this.produtos.remove(produtoSelecionado);
			MessageUtil.sucesso("Produto " + produtoSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			MessageUtil.erro(e.getMessage());
		}
	}
	
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

	
}