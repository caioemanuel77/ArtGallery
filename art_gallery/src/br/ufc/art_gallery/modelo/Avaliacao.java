package br.ufc.art_gallery.modelo;

import br.ufc.art_gallery.excecoes.NotaInvalidaException;

public class Avaliacao {
	
    private String usuario;
    private int nota;
    private String comentario;

    public Avaliacao(String usuario, int nota, String comentario) throws NotaInvalidaException {
        if (nota < 0 || nota > 10) {
            throw new NotaInvalidaException();
        }
        this.usuario = usuario;
        this.nota = nota;
        this.comentario = comentario;
    }
    
    public String getUsuario() { return usuario; }
    public int getNota() { return nota; }
    public String getComentario() { return comentario; }
   
}