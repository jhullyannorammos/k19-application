package br.com.application.report;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import br.com.application.dao.UsuarioDAO;
import br.com.application.domain.Country;

import javax.activation.DataSource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;

import org.hibernate.HibernateException;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
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
public class CountryBean implements Serializable {
	
	public void print() throws Exception {
		String nomeRelatorio = "Relatorio de listagem países";
		try {
			List<Country> listagemResultado = Country.findAll();
			HashMap paramRel = null;
			paramRel.put("REPORT_LOCALE", new Locale("pt", "BR"));
			paramRel.put("nmSistema", "Gerar Relatorio");

			gerarRelatorio(nomeRelatorio, paramRel, listagemResultado);
		}catch(Exception exception) {
			exception.printStackTrace();
		}finally {
			FacesContext.getCurrentInstance().renderResponse();
			FacesContext.getCurrentInstance().responseComplete();
		}
	}

	private void gerarRelatorio(String nomeRelatorio, HashMap paramRel, List<Country> listaRel) throws Exception {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		ServletContext sc = (ServletContext) context.getExternalContext().getContext();
		String relPath = sc.getRealPath("/");
		String logo = relPath + "pages/logo.jpg";
		paramRel.put("imagemLogo", logo);
		
		JasperPrint print = null;
		JRBeanCollectionDataSource rel = new JRBeanCollectionDataSource(listaRel);
		print = JasperFillManager.fillReport(relPath + "relatorios/" + nomeRelatorio + ".jasper", paramRel, rel);
		
		response.setContentType("application/pdf");
		response.addHeader("Content-disposition", "attachment;filename=\"" + nomeRelatorio + ".pdf\"");
		JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
		ServletOutputStream responseStream = response.getOutputStream();
		responseStream.flush();
		responseStream.close();
	}
	
	
	
	public void reportByCountry() throws JRException {

		//JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(Country.findAll());
		//JasperReport jreport = JasperCompileManager.compileReport("/relatorios/reports.xml");

		//HashMap params = new HashMap<>();
		//JasperPrint jrPrint = JasperFillManager.fillReport(jreport, params, ds);

		EventQueue.invokeLater(() -> {
			JFrame frame = new JFrame();
			//JRViewer viewer = new JRViewer(jrPrint);

			//frame.add(viewer);
			frame.setSize(new Dimension(750, 650));
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

}
