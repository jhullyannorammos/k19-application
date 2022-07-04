package br.com.application.enumeracao;

public enum TipoUsuario {
	
	ADMINISTRADOR, 
	GERENTE, 
	ATENDENTE;

	@Override public String toString() {
		switch (this) {
		case ADMINISTRADOR: return "ADMINISTRADOR";
		case GERENTE: return "GERENTE";
		case ATENDENTE: return "ATENDENTE";
		default:
			return null;
		}
	}
}
