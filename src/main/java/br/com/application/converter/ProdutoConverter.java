package br.com.application.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.HibernateException;

import br.com.application.dao.ProdutoDAO;
import br.com.application.domain.Produto;

@FacesConverter(forClass = Produto.class, value = "produtoConverter")
public class ProdutoConverter implements Converter {

	private ProdutoDAO idGenericDao = new ProdutoDAO(); // = (IGenericDao) ContextLoaderListenerImpl.getBean(ProdutoDAO.class);
    private Produto codigo;
	
	@SuppressWarnings("finally")
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String unidade) {
		try {
			codigo = idGenericDao.buscar(Long.parseLong(unidade));
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return codigo;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object unidade) {

		if (unidade == null || (unidade != null && unidade.toString().isEmpty()))
			return "";
		if (((Produto) unidade).getId() == null)
			return "";
		return ((Produto) unidade).getId().toString();
	}

}
