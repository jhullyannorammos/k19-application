package br.com.application.util;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ArquivoUpload")
public class Upload implements Serializable {

	@Id @GeneratedValue
	private Long id;

	@NotNull @Size(min = 1, max = 25)
	private String descricao;

	@NotNull
	@Lob private byte[] arquivo;
	
	public Upload() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
