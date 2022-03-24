package models;

public class Genero {
	private int id;
	private String descricao;

	public Genero(String descricao) {
		this.descricao = descricao;
	}
	
	public Genero() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
	
	

}
