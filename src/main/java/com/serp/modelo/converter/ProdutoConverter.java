package com.serp.modelo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.serp.dao.ProdutoDAO;
import com.serp.modelo.Produto;
import com.serp.util.cdi.CDIServiceLocator;



@FacesConverter(forClass=Produto.class)
public class ProdutoConverter implements Converter<Object> {

	private ProdutoDAO produtoDAO;
	
	public ProdutoConverter() {
		this.produtoDAO = CDIServiceLocator.getBean(ProdutoDAO.class);
	}
	
	@Override    //converte tipo String para objeto - necessário mapear do modelo relacional para obj
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;

		if (value != null) {
			retorno = this.produtoDAO.buscarPeloCodigo(Long.parseLong(value));
		}

		return retorno;
	}

	@Override  //converte de objeto para codigo - necessário mapear do modelo obj para relacional
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Produto) value).getCodigo();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}