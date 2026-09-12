package br.ufc.art_gallery.excecoes;

public class ObraNaoEncontradaException extends Exception {
	
	private static final long serialVersionUID = -1449565776365650197L;
	
	public ObraNaoEncontradaException() {
        super("Obra não encontrada no sistema.");
    }
}
