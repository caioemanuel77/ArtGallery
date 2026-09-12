package br.ufc.art_gallery.modelo;

public class ArteGenerativa extends Obra {
	
	private String algoritmo;
	private long seed;
	
	public ArteGenerativa(String titulo, String autor, String algoritmo, long seed) {
        super(titulo, autor);
        this.algoritmo = algoritmo;
        this.seed = seed;
    }
	
	public String getAlgoritmo() { return this.algoritmo; }
    public long getSeed() { return this.seed; }
    
    public String exibirDetalhes() {
		return "Titulo: " + getTitulo() + 
				"\nAutor: " + getAutor() + 
				"\nTipo: Arte Generativa" + 
				"\nAlgoritmo: " + getAlgoritmo() + 
				"\nSeed: " + getSeed();
	}
}
