package br.ufc.art_gallery.principal;
import br.ufc.art_gallery.modelo.*;
import br.ufc.art_gallery.repositorio.*;
import br.ufc.art_gallery.excecoes.*;
import java.util.Vector;
import java.util.Collections;

public class ArtGallery implements IArtGallery {
	
	private IRepositorioObra repositorio;
	private Vector<Exposicao> exposicoes;
	
	public ArtGallery(IRepositorioObra repositorio) {
        this.repositorio = repositorio;
        this.exposicoes = new Vector<Exposicao>();
    }
	
	public void criarExposicao(String nome) {
        for (Exposicao e : exposicoes) {
            if (e.getNome().equalsIgnoreCase(nome)) return;
        }
        exposicoes.add(new Exposicao(nome));
    }
	
	public void adicionarObraEmExposicao(String nomeExposicao, Obra obra) throws ExposicaoInexistenteException {
        for (Exposicao e : exposicoes) {
            if (e.getNome().equalsIgnoreCase(nomeExposicao)) {
                e.adicionarObra(obra);
                return;
            }
        }
        throw new ExposicaoInexistenteException();
    }
	
	public Vector<Exposicao> listarExposicoes() {
        return this.exposicoes;
    }
	
	@Override
	public void publicarObra(Obra obra) throws ObraJaCadastradaException {
		repositorio.cadastrar(obra);
	}

	@Override
	public void removerObra(String titulo) throws ObraNaoEncontradaException {
		Obra ob = repositorio.buscar(titulo);
        if (ob == null || ob.isAtiva() == false) {
            throw new ObraNaoEncontradaException();
        }
        ob.setAtiva(false);
	}

	@Override
	public void avaliarObra(String titulo, Avaliacao avaliacao) throws ObraNaoEncontradaException {
		Obra ob = repositorio.buscar(titulo);
        if (ob == null || ob.isAtiva() == false) {
            throw new ObraNaoEncontradaException();
        }
        ob.adicionarAvaliacao(avaliacao);
	}

	@Override
	public Vector<Obra> listarObras() {
		Vector<Obra> ativas = new Vector<>();
        for (Obra o : repositorio.listar()) {
            if (o.isAtiva() == true) ativas.add(o);
        }
        return ativas;
	}

	@Override
	public Vector<Obra> buscarPorAutor(String autor) {
		Vector<Obra> doAutor = new Vector<>();
        for (Obra o : repositorio.listar()) {
            if (o.getAutor().equalsIgnoreCase(autor)) doAutor.add(o);
        }
        return doAutor;
	}

	@Override
	public Vector<Obra> topObras() {
		Vector<Obra> todas = listarObras();
        Collections.sort(todas, (o1, o2) -> Double.compare(o2.mediaAvaliacoes(), o1.mediaAvaliacoes()));
        return todas;
	}

	@Override
	public Vector<Obra> obrasExpostas(String nomeExposicao) throws ExposicaoInexistenteException {
		for (Exposicao e : exposicoes) {
            if (e.getNome().equalsIgnoreCase(nomeExposicao)) {
                return e.listarObras();
            }
        }
        throw new ExposicaoInexistenteException();
	}

    @Override
    public void reativarObra(String titulo) throws ObraNaoEncontradaException {
        Obra ob = repositorio.buscar(titulo);
        if (ob == null || ob.isAtiva() == true) {
            throw new ObraNaoEncontradaException();
        }
        ob.setAtiva(true);
    }

    @Override
    public Vector<Obra> listarObrasInativas() {
        Vector<Obra> inativas = new Vector<>();
        for (Obra o : repositorio.listar()) {
            if (o.isAtiva() == false) inativas.add(o);
        }
        return inativas;
    }
}