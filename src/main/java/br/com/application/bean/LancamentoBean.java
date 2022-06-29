package br.com.application.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.hibernate.HibernateException;

import br.com.application.dao.LancamentoDAO;
import br.com.application.domain.Lancamento;
import br.com.application.domain.Usuario;

@ViewScoped
@ManagedBean(name = "lancementoBean")
public class LancamentoBean {

	private Lancamento lancamento = new Lancamento();
	private LancamentoDAO daoGeneric = new LancamentoDAO();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	
	public String salvar () throws HibernateException, Exception{
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario pessoaUser = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
		
		lancamento.setUsuario(pessoaUser);
		lancamento = daoGeneric.merge(lancamento);
		
		carregarLancamentos();
		
		return "";
	}
	
	@PostConstruct
	private void carregarLancamentos() throws HibernateException, Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Usuario pessoaUser = (Usuario) externalContext.getSessionMap().get("usuarioLogado");
		lancamentos = daoGeneric.findBy(pessoaUser.getCodigo());
	}

	public String novo(){
		lancamento = new Lancamento();
		return "";
	}
	
	public String remover() throws HibernateException, Exception{
		daoGeneric.excluir(lancamento);
		lancamento = new Lancamento();
		carregarLancamentos();
		return "";
				
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
