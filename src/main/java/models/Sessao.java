package models;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Sessao {
	private int id;
	private Date dt_sessao;
	private Time hr_sessao;
	private double valor_inteira;
	private double valor_meia;
	private boolean encerrada;
	private Sala sala;
	private Filme filme;
	private List<Ingresso> ingressos = new ArrayList<>();
	
	public Sessao(Date dt_sessao, Time hr_sessao, double valor_inteira, double valor_meia, boolean encerrada, Filme filme) {
		this.dt_sessao = dt_sessao;
		this.hr_sessao = hr_sessao;
		this.valor_inteira = valor_inteira;
		this.valor_meia = valor_meia;
		this.encerrada = encerrada;
		this.filme = filme;
	}

	public Sessao() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDt_sessao() {
		return dt_sessao;
	}

	public void setDt_sessao(Date dt_sessao) {
		this.dt_sessao = dt_sessao;
	}

	public Time getHr_sessao() {
		return hr_sessao;
	}

	public void setHr_sessao(Time hr_sessao) {
		this.hr_sessao = hr_sessao;
	}

	public double getValor_inteira() {
		return valor_inteira;
	}

	public void setValor_inteira(double valor_inteira) {
		this.valor_inteira = valor_inteira;
	}

	public double getValor_meia() {
		return valor_meia;
	}

	public void setValor_meia(double valor_meia) {
		this.valor_meia = valor_meia;
	}

	public boolean getEncerrada() {
		return encerrada;
	}

	public void setEncerrada(boolean b) {
		this.encerrada = b;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	@Override
	public String toString() {
		return "Sessao [dt_sessao=" + dt_sessao + ", hr_sessao=" + hr_sessao + ", valor_inteira=" + valor_inteira
				+ ", valor_meia=" + valor_meia + ", encerrada=" + encerrada + ", filme=" + filme + "]";
	}
	
	
}
