package br.ufc.art_gallery.principal;
import java.util.Vector;
import br.ufc.art_gallery.modelo.*;
import br.ufc.art_gallery.excecoes.*;

public interface IArtGallery {
    public void publicarObra(Obra obra) throws ObraJaCadastradaException;
    public void removerObra(String titulo) throws ObraNaoEncontradaException;
    public void avaliarObra(String titulo, Avaliacao avaliacao) throws ObraNaoEncontradaException;
    public Vector<Obra> listarObras();
    public Vector<Obra> buscarPorAutor(String autor);
    public Vector<Obra> topObras();
    public Vector<Obra> obrasExpostas(String nomeExposicao) throws ExposicaoInexistenteException;
    public void reativarObra(String titulo) throws ObraNaoEncontradaException;
    public Vector<Obra> listarObrasInativas();
}