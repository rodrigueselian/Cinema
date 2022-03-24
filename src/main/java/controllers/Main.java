 package controllers;

import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import models.*;
import dao.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("*** BEM VINDO AO CINEMA DO ELIAN ***\n");
		while(true) {
			Main.mostrarMenuOpcao(Main.mostrarMenu(args));
		}	
	}
	
	public static int mostrarMenu(String[] args) {
		System.out.println("***  INICIO  ***");
		System.out.println("\nSELECIONE UMA OPCAO ABAIXO:\n");
		System.out.println
		("1. COMPRAR INGRESSO\n"
		+"2. FILME\n"
		+"3. GENERO\n"
		+"4. SALA\n"
		+"5. SESSAO\n");
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public static void mostrarMenuOpcao(int n) {
		switch (n){
        case 1:
            Main.comprar();
            break;
        case 2:
        	Main.filme();
            break;
        case 3:
        	Main.genero();
        	break;
        case 4:
        	Main.sala();
        	break;
        case 5:
            Main.sessao();
            break;
		}
	}
	
	public static void comprar() {
		System.out.println("***  QUE FILME VOCE QUER ASSISTIR HOJE?  ***\n");
		utils.showFilmes();
		Scanner filmeid = new Scanner(System.in);
		System.out.println("PROXIMAS SESSOES:");
		utils.showSessoesByFilme(filmeid.nextInt());
		System.out.println("SELECIONE O ID DA SESSAO:");
		Scanner ids = new Scanner(System.in);
		Sessao essasessao = new Sessao();
		int idsessao = ids.nextInt();
		essasessao = SessaoDAO.selectSessaoById(idsessao);
		Sala saladasessao = new Sala();
		saladasessao = SalaDAO.selectSalaById(essasessao.getSala().getId());
		int capacidade = saladasessao.getCapacidade();
		int maxi = capacidade/25;
		int maxj = capacidade/maxi;
		int assentos = 0;
		List<Ingresso> ingressos = new ArrayList<>();
		ingressos = IngressoDAO.selectIngressoById(idsessao);
		char letra = 65;
		for (int i = 0;i < maxi ;i++) {
			System.out.print(letra + " ");
			letra++;
			for (int j = 1; j <= maxj ;j++) {
				if(ingressos.get(assentos).getVendido()) {
					System.out.print("XX ");
				}
				else {
					if(j < 10) {
						System.out.print("0");
					}
					System.out.print(j+" ");
				}
				assentos++;
			}
			System.out.println("");
		}
		System.out.println("Escolha a letra do assento:");
		Scanner line = new Scanner(System.in);
		char letraselecionada = line.next().charAt(0);
		if(letraselecionada > 96) {
			letraselecionada = (char) (letraselecionada - 32);
		}
		System.out.println("Escolha o numero do assento:");
		System.out.print(letraselecionada);
		Scanner num = new Scanner(System.in);
		int numero = num.nextInt();
		numero = numero + (letraselecionada-65) * 25;
		System.out.println("O ingresso é meia-entrada?\n1.SIM\n2.NÃO");
		boolean meia = false;
		Scanner simounao = new Scanner(System.in);
		int ingresso = simounao.nextInt();
		if(ingresso == 1) {
			meia = true;
		}
		IngressoDAO.vendaIngresso(numero, meia, essasessao);
		System.out.println("Tanto faz é tudo de graça, Obrigado pela preferencia!");
	}
	
	public static void filme() {
		System.out.println("***  FILMES  ***\n");
		System.out.println
		("1. ADICIONAR\n"
		+"2. EDITAR\n"
		+"3. EXCLUIR\n"
		+"4. VOLTAR");
		Scanner fi = new Scanner(System.in);
		switch (fi.nextInt()) {
		case 1:
			Filme novofilme = new Filme();
			novofilme.setId(0);
			
			System.out.println("Insira o titulo do filme:");
			Scanner titulo = new Scanner(System.in);
			novofilme.setTitulo(titulo.nextLine());
			
			System.out.println("Insira a duracao do filme '00:00:00':");
			Scanner duracao = new Scanner(System.in);
			novofilme.setDuracao(Time.valueOf(duracao.nextLine()));
			
			utils.showGeneros();
			System.out.println("Insira o ID do genero do filme:");
			Scanner idgen = new Scanner(System.in);
			novofilme.setGenero(GeneroDAO.selectGeneroById(idgen.nextInt()));
			
			FilmeDAO.insertFilme(novofilme);
			System.out.println("*** FILME ADICIONADO COM SUCESSO ***\n");
			break;
		case 2:
			utils.showFilmes();
			Filme editfilme = new Filme();
			
			System.out.println("\nINSIRA O ID DO FILME A SER EDITADO:");
			Scanner sc = new Scanner(System.in);
			editfilme.setId(sc.nextInt());
			
			System.out.println("\nINSIRA O NOVO TITULO:");
			Scanner sc2 = new Scanner(System.in);
			editfilme.setTitulo(sc2.nextLine());
			
			System.out.println("\nINSIRA A NOVA DURACAO:");
			Scanner sc3 = new Scanner(System.in);
			editfilme.setDuracao(Time.valueOf(sc3.nextLine()));
			
			utils.showGeneros();
			System.out.println("\nSELECIONE O ID DO NOVO GENERO");
			Scanner sc4 = new Scanner(System.in);
			editfilme.setGenero(GeneroDAO.selectGeneroById(sc4.nextInt()));
			
			FilmeDAO.updateFilme(editfilme);
			System.out.println("\n*** FILME ATUALIZADO COM SUCESSO ***\n");
			break;
		case 3:
			//Delete
			break;
		case 4:
			break;
		}
	}
	
	public static void genero() {
		System.out.println("***  GENEROS  ***\n");
		System.out.println
		("1. ADICIONAR\n"
		+"2. EDITAR\n"
		+"3. EXCLUIR\n"
		+"4. VOLTAR");
		Scanner ge = new Scanner(System.in);
		switch (ge.nextInt()) {
		case 1:
			Genero novogenero = new Genero();
			novogenero.setId(0);

			System.out.println("Insira a descricao do genero:\n");
			Scanner desc = new Scanner(System.in);
			novogenero.setDescricao(desc.nextLine());
			
			GeneroDAO.insertGenero(novogenero);
			System.out.println("*** GENERO ADICIONADO COM SUCESSO ***\n");
			break;
		case 2:
			utils.showGeneros();
			Genero editgen = new Genero();
			
			System.out.println("\nINSIRA O ID DO GENERO A SER EDITADO:");
			Scanner idgen = new Scanner(System.in);
			editgen.setId(idgen.nextInt());
			
			System.out.println("INSIRA A NOVA DESCRICAO:");
			Scanner descgen = new Scanner(System.in);
			editgen.setDescricao(descgen.nextLine());
			
			GeneroDAO.updateGenero(editgen);
			System.out.println("\n*** GENERO ATUALIZADO COM SUCESSO ***\n");
			break;
		case 3:
			//delete
			break;
		case 4:
			break;
		}
	}
	
	public static void sala() {
		System.out.println("***  SALAS  ***\n");
		System.out.println
		("1. ADICIONAR\n"
		+"2. EDITAR\n"
		+"3. EXCLUIR\n"
		+"4. VOLTAR");
		Scanner sa = new Scanner(System.in);
		switch (sa.nextInt()) {
		case 1:
			Sala novasala = new Sala();
			novasala.setId(0);

			System.out.println("Insira a capacidade da sala:\n");
			Scanner cap = new Scanner(System.in);
			novasala.setCapacidade(cap.nextInt());
			
			SalaDAO.insertSala(novasala);
			System.out.println("*** SALA ADICIONADA COM SUCESSO ***\n");
			break;
		case 2:
			utils.showSalas();
			Sala editsala = new Sala();
			
			System.out.println("\nINSIRA O ID DA SALA A SER EDITADO:");
			Scanner idsala = new Scanner(System.in);
			editsala.setId(idsala.nextInt());
			
			System.out.println("INSIRA A NOVA CAPACIDADE:");
			Scanner editcap = new Scanner(System.in);
			editsala.setCapacidade(editcap.nextInt());
			
			SalaDAO.updateSala(editsala);
			System.out.println("\n*** SALA ATUALIZADA COM SUCESSO ***\n");
			break;
		case 3:
			//delete
			break;
		case 4:
			break;
		}
	}
	
	public static void sessao() {
		System.out.println("***  SESSOES  ***\n");
		System.out.println
		("1. ADICIONAR\n"
		+"2. EDITAR\n"
		+"3. ENCERRAR\n"
		+"4. VOLTAR");
		Scanner se = new Scanner(System.in);
		switch (se.nextInt()) {
		case 1:
			Sessao novasessao = new Sessao();
			novasessao.setId(0);

			System.out.println("Insira a data da sessao 'yyyy-mm-dd' :\n");
			Scanner data = new Scanner(System.in);
			novasessao.setDt_sessao(Date.valueOf(data.nextLine()));
			
			System.out.println("Insira o horario da sessao 'hh:mm:ss':\n");
			Scanner hr = new Scanner(System.in);
			novasessao.setHr_sessao(Time.valueOf(hr.nextLine()));
			
			System.out.println("Insira o valor do ingresso:\n");
			Scanner in = new Scanner(System.in);
			novasessao.setValor_inteira(in.nextDouble());
			novasessao.setValor_meia(novasessao.getValor_inteira()/2);
			
			utils.showSalas();
			System.out.println("Insira o ID da sala:\n");
			Scanner sase = new Scanner(System.in);
			novasessao.setSala(SalaDAO.selectSalaById(sase.nextInt()));
			
			utils.showFilmes();
			System.out.println("Insira o ID do filme:\n");
			Scanner fise = new Scanner(System.in);
			novasessao.setFilme(FilmeDAO.selectFilmeById(fise.nextInt()));
			
			SessaoDAO.insertSessao(novasessao);
			System.out.println("Criando ingressos...\n");
			for(int i = 1; i <= novasessao.getSala().getCapacidade(); i++) {
				IngressoDAO.insertIngresso(i, novasessao);
			}
			System.out.println("*** SESSAO ADICIONADA COM SUCESSO ***\n");
			break;
		case 2:
			utils.showSessoes();
			Sessao editsessao = new Sessao();
			
			System.out.println("\nINSIRA O ID DA SESSAO A SER EDITADA:");
			Scanner editse = new Scanner(System.in);
			editsessao.setId(editse.nextInt());
			
			System.out.println("INSIRA A NOVA DATA DA SESSAO 'yyyy-mm-dd':");
			Scanner editdase = new Scanner(System.in);
			editsessao.setDt_sessao(Date.valueOf(editdase.nextLine()));
			
			System.out.println("INSIRA O NOVO HORARIO DA SESSAO 'hh:mm:ss':");
			Scanner edithrse = new Scanner(System.in);
			editsessao.setHr_sessao(Time.valueOf(edithrse.nextLine()));
			
			System.out.println("INSIRA O NOVO VALOR DO INGRESSO:");
			Scanner editinse = new Scanner(System.in);
			editsessao.setValor_inteira(editinse.nextDouble());
			editsessao.setValor_meia(editsessao.getValor_inteira()/2);
			
			utils.showSalas();
			System.out.println("INSIRA O NOVO ID DA SALA:");
			Scanner editsase = new Scanner(System.in);
			editsessao.setSala(SalaDAO.selectSalaById(editsase.nextInt()));
			
			utils.showFilmes();
			System.out.println("INSIRA O NOVO ID DO FILME:");
			Scanner editfise = new Scanner(System.in);
			editsessao.setFilme(FilmeDAO.selectFilmeById(editfise.nextInt()));
			
			SessaoDAO.updateSessao(editsessao);
			System.out.println("\n*** SESSAO ATUALIZADA COM SUCESSO ***\n");
			break;
		case 3:
			utils.showSessoes();
			System.out.println("\nINSIRA O ID DA SESSAO A SER ENCERRADA:");
			Scanner enc = new Scanner(System.in);
			SessaoDAO.encerraSessao(enc.nextInt());
			System.out.println("SESSAO ENCERRADA COM SUCESSO!");
			break;
		case 4:
			break;
		}
	}
}