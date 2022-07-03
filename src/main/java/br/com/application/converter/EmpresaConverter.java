package br.com.application.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.HibernateException;

import br.com.application.dao.EmpresaDAO;
import br.com.application.domain.Empresa;

@FacesConverter(forClass = Empresa.class, value = "empresaConverter")
public class EmpresaConverter implements Converter {

	private EmpresaDAO idGenericDao = new EmpresaDAO(); // = (IGenericDao) ContextLoaderListenerImpl.getBean(EmpresaDAO.class);
    private Empresa codigo;
	
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
		if (((Empresa) unidade).getId() == null)
			return "";
		return ((Empresa) unidade).getId().toString();
	}

}
