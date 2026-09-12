package br.ufc.art_gallery.repositorio;
import java.util.Vector;
import br.ufc.art_gallery.modelo.Obra;
import br.ufc.art_gallery.excecoes.*;

public class RepositorioObra implements IRepositorioObra {
	
	private Vector<Obra> obras;
	
	public RepositorioObra () {
		this.obras = new Vector<Obra>();
	}
	
	@Override
	public void cadastrar (Obra obra) throws ObraJaCadastradaException {
		String titulo = obra.getTitulo();
		String autor = obra.getAutor();
		for (Obra ob : obras) {
			if( ob.getTitulo().equalsIgnoreCase(titulo) && ob.getAutor().equalsIgnoreCase(autor) ) {
				throw new ObraJaCadastradaException();
			}
		}
		this.obras.add(obra);
	}
	
	@Override
	public Obra buscar (String titulo) {
		for (Obra ob : obras) {
			if (ob.getTitulo().equalsIgnoreCase(titulo)) {
				return ob;
			}
		}
		return null;
	}
	
	@Override
	public void atualizar (Obra obra) throws ObraNaoEncontradaException {
        for (int i = 0; i < obras.size(); i++) {
            if (obras.get(i).getTitulo().equalsIgnoreCase(obra.getTitulo())) {
                obras.set(i, obra);
                return;
            }
        }
        throw new ObraNaoEncontradaException();
    }
	
	@Override
	public void remover (String titulo) {
		Obra ob = buscar(titulo);
		if (ob != null) {
			ob.setAtiva(false);
		}
	}
	
	public Vector<Obra> listar () {
		return this.obras;
	}
}
