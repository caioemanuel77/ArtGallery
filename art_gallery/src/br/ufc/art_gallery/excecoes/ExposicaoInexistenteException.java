package br.ufc.art_gallery.excecoes;

public class ExposicaoInexistenteException extends Exception {
	
	private static final long serialVersionUID = 5035825740129026414L;

	public ExposicaoInexistenteException() {
        super("A exposição informada não existe no sistema.");
    }
}