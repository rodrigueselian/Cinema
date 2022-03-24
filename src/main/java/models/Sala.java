package models;

import java.util.ArrayList;
import java.util.List;

public class Sala {
	private int id;
	private int capacidade;
	
	public Sala(int nsala, int capacidade) {
		this.capacidade = capacidade;
	}

	public Sala() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	@Override
	public String toString() {
		return ""+id;
	}
	
	
}
