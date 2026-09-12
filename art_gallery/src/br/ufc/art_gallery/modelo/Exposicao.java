package br.ufc.art_gallery.modelo;
import java.util.Vector;

public class Exposicao {
	
	private String nome;
	private Vector<Obra> obras;
	
	public Exposicao (String nome) {
		this.nome = nome;
		this.obras = new Vector<Obra>();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void adicionarObra (Obra obra) {
		this.obras.add(obra);
	}
	
	public Vector<Obra> listarObras() {
		return this.obras;
	}
	
}
