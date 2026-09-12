package br.ufc.art_gallery.repositorio;
import java.util.Vector;
import br.ufc.art_gallery.modelo.Obra;
import br.ufc.art_gallery.excecoes.*;

public interface IRepositorioObra {
	public void cadastrar(Obra obra) throws ObraJaCadastradaException;
    public Obra buscar(String titulo);
    public void atualizar(Obra obra) throws ObraNaoEncontradaException;
    public void remover(String titulo);
    public Vector<Obra> listar();
}