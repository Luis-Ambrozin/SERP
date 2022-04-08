package com.serp.modelo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.serp.dao.AdmDAO;
import com.serp.modelo.Administrador;
import com.serp.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Administrador.class)
public class AdmConverter implements Converter<Object> {

	private AdmDAO admDAO;

	public AdmConverter() {
		this.admDAO = CDIServiceLocator.getBean(AdmDAO.class);
	}

	@Override // converte tipo String para objeto - necessário mapear do modelo relacional
				// para obj
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Administrador retorno = null;

		if (value != null) {
			retorno = this.admDAO.findById(Integer.parseInt(value));
		}

		return retorno;
	}

	@Override // converte de objeto para codigo - necessário mapear do modelo obj para
				// relacional
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Integer codigo = ((Administrador) value).getId();
			String retorno = (codigo == null ? null : codigo.toString());

			return retorno;
		}

		return "";
	}

}