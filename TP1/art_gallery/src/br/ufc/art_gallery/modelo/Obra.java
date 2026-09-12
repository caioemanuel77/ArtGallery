package br.ufc.art_gallery.modelo;
import java.util.Vector;

public abstract class Obra {
	
	private String titulo;
	private String autor;
	private boolean ativa;
	private Vector<Avaliacao> avaliacoes;
	
	public Obra (String titulo, String autor) {
		this.titulo = titulo;
		this.autor = autor;
		this.ativa = true;
		this.avaliacoes = new Vector<Avaliacao>();
	}
	
	public String getTitulo() { return this.titulo; }
	public String getAutor() { return this.autor;}
	public boolean isAtiva() { return this.ativa;}
	public void setAtiva(boolean ativa) { this.ativa = ativa;}
	
	public void adicionarAvaliacao(Avaliacao avaliacao) {
		this.avaliacoes.add(avaliacao);
	}
	
	public double mediaAvaliacoes() {
		if (avaliacoes.isEmpty()) {
			return 0.0;
		}
		
		double media = 0;
		for (Avaliacao av : avaliacoes) {
			media += av.getNota();
		}
		return media/avaliacoes.size();
	}
	
	public abstract String exibirDetalhes();
}
