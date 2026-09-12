package br.ufc.art_gallery.excecoes;

public class ObraJaCadastradaException extends Exception {
	
	private static final long serialVersionUID = 8133347888799534733L;

	public ObraJaCadastradaException() {
        super("Esta obra já está cadastrada no sistema.");
    }
}