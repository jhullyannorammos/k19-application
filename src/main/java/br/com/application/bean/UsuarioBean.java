package br.com.application.bean;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;

import org.hibernate.HibernateException;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import br.com.application.dao.UsuarioDAO;
import br.com.application.domain.Usuario;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private List<Usuario> usuarios = new ArrayList<>();
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public UsuarioBean() throws Exception {
		listar();
	}

	//@PostConstruct
	public void listar() throws Exception {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar("tipoUsuario");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuários");
			erro.printStackTrace();
		}
	}

	public void novo() throws Exception {
		try {
			usuario = new Usuario();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar criar um novo usuário");
			erro.printStackTrace();
		}
	}

	public void salvar() throws Exception {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuario = new Usuario();
			usuarios = usuarioDAO.listar("tipoUsuario");

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o usuário");
			erro.printStackTrace();
		} finally {
			Messages.addGlobalInfo(Faces.getResourceBundle("msg").getString("usuarioSalvo"));
		}
	}
	
	/*
	 * Retorna caminho onde os relatórios (.jasper e .jrxml e tmps) ficam
	 * armazenados
	 **/
	private String reportSourcePath() {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/relatorios/") + "/";
	}
	
	public void findByAllUsuarios() throws ClassCastException, HibernateException, Exception {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		
		ServletContext sc =	(ServletContext) context.getExternalContext().getContext();
		
		String relPath = sc.getRealPath("/");
		//String imagemLogo =	relPath + "resources/imagens/especializa.jpg";
		
		HashMap paramRel = new HashMap<>();
		//paramRel.put("imagemLogo", imagemLogo);
		paramRel.put("nmSistema", "Relatorios");
		paramRel.put("REPORT_LOCALE", new Locale("pt", "BR"));
		JasperPrint print = null;
		
		UsuarioDAO usDao = new UsuarioDAO();

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(usuarios);
		print = JasperFillManager.fillReport(reportSourcePath() + "usuarios.jasper", paramRel, ds);
		
		response.setContentType("application/pdf");
		response.addHeader("Content-disposition", "attachment;filename=\"" + "usuarios.pdf\"");
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		
		ServletOutputStream responseStream = response.getOutputStream();

		responseStream.flush();
		responseStream.close();
		FacesContext.getCurrentInstance().renderResponse();
		FacesContext.getCurrentInstance().responseComplete();
		
	}

	public void findByAllUsuarios2() throws HibernateException, Exception {

		HashMap params = new HashMap<>();
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(usuarios);
		JasperReport jreport = JasperCompileManager.compileReport("/WEB-INF/relatorios/usuarios.jasper");
		JasperPrint jrPrint = JasperFillManager.fillReport(jreport, params, ds);

		EventQueue.invokeLater(() -> {
			JFrame frame = new JFrame();
			JRViewer viewer = new JRViewer(jrPrint);

			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(new Dimension(750, 650));
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.add(viewer);

		});
	}
	
	
}
