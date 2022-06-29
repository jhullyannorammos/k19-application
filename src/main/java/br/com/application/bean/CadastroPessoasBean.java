package br.com.application.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import br.com.application.domain.Pessoa;
import br.com.application.domain.PessoaFisica;
import br.com.application.domain.PessoaJuridica;
import br.com.application.enumeracao.Sexo;

@ManagedBean
@SessionScoped
public class CadastroPessoasBean {
	
	private Pessoa pessoaSelecionada;
	private Collection<Pessoa> lista;
	private String tipoNovaPessoa;
	
	public CadastroPessoasBean() {
		lista = new ArrayList<Pessoa>();
		for (int x = 0; x < 10; x++) {
			Pessoa p = (x%2==0) ? new PessoaFisica() : 
				                  new PessoaJuridica();
			
			p.setNome(String.format("Fulano %02d", x));
			p.setEmail(String.format("fulano%02d@teste.com", x));
			p.setTelefone(String.format("9999.88%02d", x));
			
			lista.add(p);
		}
	}
	
	public void criar() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		if (tipoNovaPessoa == null) {
			contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Você deve especificar o tipo", ""));
			return;
		}
		
		if (tipoNovaPessoa.equals("PF")) {
			pessoaSelecionada = new PessoaFisica();
		} else if (tipoNovaPessoa.equals("PJ")) {
			pessoaSelecionada =	new PessoaJuridica();
		}
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pessoa criada com sucesso!", ""));

	}

	public void salvar() {
		if (!lista.contains(pessoaSelecionada)) {
			lista.add(pessoaSelecionada);
		}
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Edição efetuada com sucesso!", ""));
	}
	
	public String cancelar() {
		pessoaSelecionada = null;
		tipoNovaPessoa = null;
		return "inicio";
	}
	
	public void excluir() {
		lista.remove(pessoaSelecionada);
		pessoaSelecionada = null;
		String mensagem = ResourceBundle.getBundle("bundles.mensagens",	FacesContext.getCurrentInstance().getExternalContext().getRequestLocale()).getString("excluida");
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, ""));
 	}
	
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}
	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}
	public Collection<Pessoa> getLista() {
		return lista;
	}
	public void setLista(Collection<Pessoa> lista) {
		this.lista = lista;
	}

	public String getTipoNovaPessoa() {
		return tipoNovaPessoa;
	}

	public void setTipoNovaPessoa(String tipoNovaPessoa) {
		this.tipoNovaPessoa = tipoNovaPessoa;
	}
	
	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public boolean isPessoaFisicaSelecionada() {
		return pessoaSelecionada instanceof PessoaFisica;
	}

	public boolean isPessoaJuridicaSelecionada() {
		return pessoaSelecionada instanceof PessoaJuridica;
	}
	
	public void ouvinteAjax(AjaxBehaviorEvent event) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("AJAX " + event.getPhaseId());
	}

	public void ouvinteAjax(ValueChangeEvent event) {
		Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).info("AJAX VALUE CHANGE ");
	}
}
