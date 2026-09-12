package br.ufc.art_gallery.excecoes;

public class NotaInvalidaException extends Exception {
    
	private static final long serialVersionUID = -3682393333979183505L;

	public NotaInvalidaException() {
        super("A nota deve estar entre 0 e 10.");
    }
}