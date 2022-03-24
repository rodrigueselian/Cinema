package models;

public class Ingresso {
	private int id;
	private boolean tipo;
	private int assento;
	private Sessao sessao;
	private boolean vendido;
	
	public Ingresso(boolean tipo, int assento) {
		this.tipo = tipo;
		this.assento = assento;
	}
	
	public Ingresso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getTipo() {
		return tipo;
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}

	public int getAssento() {
		return assento;
	}

	public void setAssento(int assento) {
		this.assento = assento;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public boolean getVendido() {
		return vendido;
	}

	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}

	@Override
	public String toString() {
		return "Ingresso [tipo=" + tipo + ", assento=" + assento + "]";
	}
	
	
}
