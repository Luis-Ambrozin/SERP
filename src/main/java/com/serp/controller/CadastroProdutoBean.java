package com.serp.controller;

import java.io.Serializable;

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
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	
	@Inject
	private ProdutoService produtoService;
	
	
	public void salvar() {
		try {
			
			this.produtoService.salvar(produto);
			MessageUtil.sucesso("Produto salvo com sucesso!");
		} catch (NegocioException e) {
			MessageUtil.erro(e.getMessage());
		} catch (Exception ex) {
			MessageUtil.erro(ex.getMessage());
		}
		
		this.limpar();
	}
	
	@PostConstruct
	public void limpar() {
		this.produto = new Produto();		
	}	

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
