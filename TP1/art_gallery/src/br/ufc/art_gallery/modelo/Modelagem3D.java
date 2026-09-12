package br.ufc.art_gallery.modelo;

public class Modelagem3D extends Obra {
	private int numPoligonos;
	private String engine;
	
	public Modelagem3D (String titulo, String autor, int numPoligonos, String engine) {
		super(titulo,autor);
		this.numPoligonos = numPoligonos;
		this.engine = engine;
	}
	
	public int getNumPoligonos() { return this.numPoligonos; }
	public String getEngine() { return this.engine; } 
	
	@Override
	public String exibirDetalhes() {
		return "Titulo: " + getTitulo() + 
				"\nAutor: " + getAutor() + 
				"\nTipo: Modelagem3D" + 
				"\nPolígonos: " + getNumPoligonos() + 
				"\nEngine: " + getEngine();
	}
}
