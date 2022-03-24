package models;

import java.sql.Time;

public class Filme {
	private int id;
	private String titulo;
	private Time duracao;
	private Genero genero;
	
	public Filme(String titulo, Time duracao, Genero genero) {
		this.titulo = titulo;
		this.duracao = duracao;
		this.genero = genero;
	}
	
	public Filme() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Time getDuracao() {
		return duracao;
	}
	public void setDuracao(Time duracao) {
		this.duracao = duracao;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	@Override
	public String toString() {
		return titulo + "  ---  " + duracao + "  ---  " + genero+"\n";
	}
	
}
