package br.com.application.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.HibernateException;

import br.com.application.dao.UnidadeProdutoDAO;
import br.com.application.domain.UnidadeProduto;

@FacesConverter(forClass = UnidadeProduto.class, value = "unidadeProdutoConverter")
public class UnidadeProdutoConverter implements Converter {

	private UnidadeProdutoDAO idGenericDao = new UnidadeProdutoDAO(); // = (IGenericDao) ContextLoaderListenerImpl.getBean(UnidadeProdutoDAO.class);
    private UnidadeProdutoDAO codigo;
	
	@SuppressWarnings("finally")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String unidade) {
		try {
			codigo = idGenericDao.buscar(Long.parseLong(unidade));
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return codigo;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object unidade) {

		if (unidade == null || (unidade != null && unidade.toString().isEmpty()))
			return "";
		if (((UnidadeProduto) unidade).getId() == null)
			return "";
		return ((UnidadeProduto) unidade).getId().toString();
	}

}
