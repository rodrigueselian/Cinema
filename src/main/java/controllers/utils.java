package controllers;
import java.text.DecimalFormat;
import java.util.*;
import dao.*;
import models.*;

public class utils {
	static int maxchar = 0;
	
	public static void showGeneros() {
		List<Genero> generos = new ArrayList<>();
		generos = GeneroDAO.selectGeneros();
		System.out.println("id | descricao");
		generos.forEach(g -> {
			System.out.println(g.getId() + "  | " + g.getDescricao()+"");
		});
	}
	
	public static void showFilmes() {
		List<Filme> filmes = new ArrayList<>();
		filmes = FilmeDAO.selectFilmes();
		
		filmes.forEach(f -> {
			if (f.getTitulo().chars().count() > maxchar) {
				maxchar = (int) f.getTitulo().chars().count();
			}
		});
		maxchar -= 6;
		String espacos = "";
		for(int i = 0; i < maxchar; i++) {
			espacos = espacos + " ";
		}
		System.out.println("id | Titulo"+ espacos +" | Duracao  | Genero");
		maxchar += 6;
		filmes.forEach(f -> {
			int actualsize = (int) f.getTitulo().chars().count();
			maxchar = maxchar - actualsize;
			String espacoprofilme = "";
			for(int i = 0; i < maxchar; i++) {
				espacoprofilme = espacoprofilme + " ";
			}
			maxchar = maxchar + actualsize;
			System.out.println(f.getId() + "  | " + f.getTitulo()+ 
			espacoprofilme +" | " + f.getDuracao() + " | " + f.getGenero());
		});
	}
	
	public static void showSalas() {
		List<Sala> salas = new ArrayList<>();
		salas = SalaDAO.selectSalas();
		System.out.println("id | capacidade");
		salas.forEach(s -> {
			System.out.println(s.getId() + "  | " + s.getCapacidade()+"");
		});
	}
	
	public static void showSessoes() {
		List<Sessao> sessoes = new ArrayList<>();
		sessoes = SessaoDAO.selectSessoes();
		final DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("id |    Data    |   Hora   | Valor | Meia | Sala | Filme ");
		sessoes.forEach(s -> {
			if(s.getId() < 10)
				System.out.print("0");
			System.out.print(s.getId() + " | " + s.getDt_sessao() + " | " 
			+ s.getHr_sessao() + " | " + df.format(s.getValor_inteira()) + " | " 
			+ df.format(s.getValor_meia()) + " | ");
			if(s.getSala().getId() < 10)
				System.out.print(" 0");
			System.out.print(s.getSala() + "  | " + s.getFilme().getTitulo() + "\n");
		});
	}
	
	public static void showSessoesByFilme(int idfilme) {
		List<Sessao> sessoes = new ArrayList<>();
		sessoes = SessaoDAO.selectSessaoByFilme(idfilme);
		final DecimalFormat df = new DecimalFormat("0.00");
		System.out.println("id |    Data    |   Hora   | Valor | Meia | Sala | Filme ");
		sessoes.forEach(s -> {
			if(s.getId() < 10)
				System.out.print("0");
			System.out.print(s.getId() + " | " + s.getDt_sessao() + " | " 
			+ s.getHr_sessao() + " | " + df.format(s.getValor_inteira()) + " | " 
			+ df.format(s.getValor_meia()) + " | ");
			if(s.getSala().getId() < 10)
				System.out.print(" 0");
			System.out.print(s.getSala() + "  | " + s.getFilme().getTitulo() + "\n");
		});
	}
}
