package br.ufc.art_gallery.principal;

import br.ufc.art_gallery.repositorio.IRepositorioObra;
import br.ufc.art_gallery.repositorio.RepositorioObra;

public class Main {
    public static void main(String[] args) {
        // INSTANCIA REPOSITÓRIO
        IRepositorioObra repositorio = new RepositorioObra();

        // GERA FACHADA DE CONTROLE COM REPOSITÓRIO DE REFERÊNCIA
        ArtGallery galeriaControladora = new ArtGallery(repositorio);

        // INSTANCIA TELA PRINCIPAL
        TelaPrincipal tela = new TelaPrincipal(galeriaControladora);
        
        // TORNA JANELA VISÍVEL
        tela.setVisible(true);
    }
}