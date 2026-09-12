package br.ufc.art_gallery.principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import br.ufc.art_gallery.modelo.*;
import br.ufc.art_gallery.excecoes.*;

public class TelaPrincipal extends JFrame {
	
    private static final long serialVersionUID = 1L;
    private ArtGallery galeria;
    private JTextArea txtAreaSaida;

    public TelaPrincipal(ArtGallery galeria) {
        this.galeria = galeria;

        //CONFIGURAÇÕES BÁSICAS DA JANELA
        setTitle("ArtGallery - UFC");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //TITULO SUPERIOR
        JPanel painelSuperior = new JPanel();
        painelSuperior.setBackground(new Color(33, 43, 54));
        JLabel lblTitulo = new JLabel("ArtGallery");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        painelSuperior.add(lblTitulo);
        add(painelSuperior, BorderLayout.NORTH);

        //PAINEL CENTRAL
        txtAreaSaida = new JTextArea();
        txtAreaSaida.setEditable(false);
        txtAreaSaida.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(txtAreaSaida);
        add(scrollPane, BorderLayout.CENTER);

        //PAINEL ESQUERDO
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(11, 1, 5, 5));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //DECLARANDO BOTOES
        JButton btnPublicar = new JButton("Publicar Obra");
        JButton btnDesativar = new JButton("Desativar Obra");
        JButton btnReativar = new JButton("Reativar Obra");
        JButton btnAvaliar = new JButton("Avaliar Obra");
        JButton btnListarAtivas = new JButton("Listar Obras Ativas");
        JButton btnListarInativas = new JButton("Listar Obras Inativas");
        JButton btnBuscarAutor = new JButton("Buscar Obras por Autor");
        JButton btnRanking = new JButton("Ranking Top Obras");
        JButton btnObrasExpo = new JButton("Obras da Exposição");
        JButton btnCriarExpo = new JButton("Criar Exposição");
        JButton btnAddObraExpo = new JButton("Adicionar Obra Exposição");
        JButton btnListarExpo = new JButton("Listar Exposições");
        JButton btnSair = new JButton("Sair");

        //CUSTOMIZANDO BOTAO 'SAIR'
        btnSair.setBackground(new Color(180, 50, 50));
        btnSair.setForeground(Color.WHITE);

        //ADICIONANDO COMPONENTES
        painelBotoes.add(btnPublicar);
        painelBotoes.add(btnAvaliar);
        painelBotoes.add(btnDesativar);
        painelBotoes.add(btnReativar);
        painelBotoes.add(btnListarAtivas);
        painelBotoes.add(btnListarInativas);
        painelBotoes.add(btnBuscarAutor);
        painelBotoes.add(btnRanking);
        painelBotoes.add(btnObrasExpo);
        painelBotoes.add(btnCriarExpo);
        painelBotoes.add(btnAddObraExpo);
        painelBotoes.add(btnListarExpo);
        painelBotoes.add(btnSair);
        add(painelBotoes, BorderLayout.WEST);

        //TRATAMENTO DE EVENTOS

        // PUBLICAR OBRA
        btnPublicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] tipos = {"Pintura Digital", "Modelagem 3D", "Arte Generativa"};
                String tipoSelecionado = (String) JOptionPane.showInputDialog(
                        TelaPrincipal.this, "Escolha o tipo de obra para cadastrar:",
                        "Publicar Obra", JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

                if (tipoSelecionado == null) return;

                String titulo = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o título da obra:");
                if (titulo == null || titulo.trim().isEmpty()) return;

                String autor = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o autor da obra:");
                if (autor == null || autor.trim().isEmpty()) return;

                Obra novaObra = null;

                try {
                    if (tipoSelecionado.equals("Pintura Digital")) {
                        String resolucao = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite a resolução (ex: 4K, 1080p):");
                        String software = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o software utilizado:");
                        novaObra = new PinturaDigital(titulo, autor, resolucao, software);
                    } else if (tipoSelecionado.equals("Modelagem 3D")) {
                        String poligonosStr = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o número de polígonos:");
                        int poligonos = Integer.parseInt(poligonosStr);
                        String engine = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite a engine utilizada:");
                        novaObra = new Modelagem3D(titulo, autor, poligonos, engine);
                    } else if (tipoSelecionado.equals("Arte Generativa")) {
                        String algoritmo = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o algoritmo utilizado:");
                        String seedStr = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite a Seed (número):");
                        long seed = Long.parseLong(seedStr);
                        novaObra = new ArteGenerativa(titulo, autor, algoritmo, seed);
                    }

                    if (novaObra != null) {
                        galeria.publicarObra(novaObra);
                        JOptionPane.showMessageDialog(TelaPrincipal.this, "Obra '" + titulo + "' publicada com sucesso!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "Erro: Insira valores numéricos válidos para polígonos/seed.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                } catch (ObraJaCadastradaException ex) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // DESATIVAR OBRA
        btnDesativar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o título da obra que deseja desativar:");
                if (titulo != null && !titulo.trim().isEmpty()) {
                    try {
                        galeria.removerObra(titulo);
                        JOptionPane.showMessageDialog(TelaPrincipal.this, "Obra desativada com sucesso!");
                    } catch (ObraNaoEncontradaException ex) {
                        JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // REATIVAR OBRA
        btnReativar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o título da obra que deseja reativar:");
                if (titulo != null && !titulo.trim().isEmpty()) {
                    try {
                        galeria.reativarObra(titulo);
                        JOptionPane.showMessageDialog(TelaPrincipal.this, "Obra reativada com sucesso!");
                    } catch (ObraNaoEncontradaException ex) {
                        JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // AVALIAR OBRA
        btnAvaliar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o título da obra a ser avaliada:");
                if (titulo == null || titulo.trim().isEmpty()) return;

                String usuario = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite seu nome de usuário:");
                if (usuario == null || usuario.trim().isEmpty()) return;

                String notaStr = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite a nota (0 a 10):");
                if (notaStr == null) return;

                String comentario = JOptionPane.showInputDialog(TelaPrincipal.this, "Deixe um comentário (opcional):");

                try {
                    int nota = Integer.parseInt(notaStr);
                    Avaliacao av = new Avaliacao(usuario, nota, comentario);
                    galeria.avaliarObra(titulo, av);
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "Avaliação registrada com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "A nota deve ser um número inteiro.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (NotaInvalidaException ex) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Nota Inválida", JOptionPane.ERROR_MESSAGE);
                } catch (ObraNaoEncontradaException ex) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // LISTAR OBRAS ATIVAS
        btnListarAtivas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaSaida.setText("=== LISTAGEM DE OBRAS ATIVAS NA GALERIA ===\n\n");
                Vector<Obra> ativas = galeria.listarObras();
                if (ativas.isEmpty()) {
                    txtAreaSaida.append("Nenhuma obra ativa cadastrada no momento.");
                } else {
                    for (Obra o : ativas) {
                        txtAreaSaida.append(o.exibirDetalhes() + "\nMédia de Avaliações: " + o.mediaAvaliacoes() + "\n");
                        txtAreaSaida.append("------------------------------------------\n");
                    }
                }
            }
        });

        // LISTAR OBRAS INATIVAS
        btnListarInativas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaSaida.setText("=== LISTAGEM DE OBRAS INATIVAS NA GALERIA ===\n\n");
                Vector<Obra> inativas = galeria.listarObrasInativas();
                if (inativas.isEmpty()) {
                    txtAreaSaida.append("Nenhuma obra inativa/arquivada no momento.");
                } else {
                    for (Obra o : inativas) {
                        txtAreaSaida.append(o.exibirDetalhes() + "\nMédia de Avaliações (Antes da desativação): " + o.mediaAvaliacoes() + "\n");
                        txtAreaSaida.append("------------------------------------------\n");
                    }
                }
            }
        });

        // BUSCAR OBRAS POR AUTOR
        btnBuscarAutor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String autor = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o nome do autor que deseja buscar:");
                if (autor != null && !autor.trim().isEmpty()) {
                    txtAreaSaida.setText("=== OBRAS DO AUTOR: " + autor.toUpperCase() + " ===\n\n");
                    Vector<Obra> resultado = galeria.buscarPorAutor(autor);
                    if (resultado.isEmpty()) {
                        txtAreaSaida.append("Nenhuma obra encontrada para o autor informado.");
                    } else {
                        for (Obra o : resultado) {
                        	String status; 
                        	if (o.isAtiva() == true) status = "Ativa";
                        	else status = "Inativa";
                            txtAreaSaida.append(o.exibirDetalhes() + "\nStatus: " + status + "\n");
                            txtAreaSaida.append("------------------------------------------\n");
                        }
                    }
                }
            }
        });

        // RANKING TOP OBRAS
        btnRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaSaida.setText("=== RANKING TOP OBRAS (ORDENADO POR MÉDIA) ===\n\n");
                Vector<Obra> top = galeria.topObras();
                if (top.isEmpty()) {
                    txtAreaSaida.append("Não há obras avaliadas ou ativas para gerar o ranking.");
                } else {
                    int posicao = 1;
                    for (Obra o : top) {
                        txtAreaSaida.append(posicao + "º Lugar: " + 
                        					o.getTitulo().toUpperCase() + 
                        					"\n" + o.exibirDetalhes() + 
                        					"\nNota Média: " + o.mediaAvaliacoes() + "\n");
                        txtAreaSaida.append("------------------------------------------\n");
                        posicao++;
                    }
                }
            }
        });

        // OBRAS DA EXPOSIÇÃO
        btnObrasExpo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeExpo = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o nome da Exposição temática:");
                if (nomeExpo != null && !nomeExpo.trim().isEmpty()) {
                    try {
                        Vector<Obra> expostas = galeria.obrasExpostas(nomeExpo);
                        txtAreaSaida.setText("=== OBRAS NA EXPOSIÇÃO: " + nomeExpo.toUpperCase() + " ===\n\n");
                        if (expostas.isEmpty()) {
                            txtAreaSaida.append("Nenhuma obra vinculada a esta exposição temática até o momento.");
                        } else {
                            for (Obra o : expostas) {
                                txtAreaSaida.append(o.exibirDetalhes() + "\n------------------------------------------\n");
                            }
                        }
                    } catch (ExposicaoInexistenteException ex) {
                        JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro de Exposição", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        // CRIAR EXPOSIÇÃO 
        btnCriarExpo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeExpo = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o nome da nova Exposição:");
                if (nomeExpo != null && !nomeExpo.trim().isEmpty()) {
                    galeria.criarExposicao(nomeExpo);
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "Exposição '" + nomeExpo + "' criada com sucesso e pronta para receber obras!");
                }
            }
        });
        
        // ADICIONAR OBRA EM EXPOSIÇÃO
        btnAddObraExpo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeExpo = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o nome da Exposição de destino:");
                if (nomeExpo == null || nomeExpo.trim().isEmpty()) return;

                String tituloObra = JOptionPane.showInputDialog(TelaPrincipal.this, "Digite o título da Obra a ser vinculada:");
                if (tituloObra == null || tituloObra.trim().isEmpty()) return;

                Vector<Obra> ativas = galeria.listarObras();
                Obra alvo = null;
                for (Obra o : ativas) {
                    if (o.getTitulo().equalsIgnoreCase(tituloObra)) {
                        alvo = o;
                        break;
                    }
                }

                if (alvo != null) {
                    try {
                        galeria.adicionarObraEmExposicao(nomeExpo, alvo);
                        JOptionPane.showMessageDialog(TelaPrincipal.this, "Obra '" + tituloObra + "' adicionada com sucesso à exposição '" + nomeExpo + "'!");
                    } catch (ExposicaoInexistenteException ex) {
                        JOptionPane.showMessageDialog(TelaPrincipal.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "Obra não encontrada no acervo ou está inativa.", "Erro de Obra", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // LISTAR EXPOSICOES
        btnListarExpo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<br.ufc.art_gallery.modelo.Exposicao> listaExposicoes = galeria.listarExposicoes();
                
                txtAreaSaida.setText("=== EXPOSIÇÕES CADASTRADAS ===\n\n");
                
                if (listaExposicoes == null || listaExposicoes.isEmpty()) {
                    txtAreaSaida.append("Não há exposições criadas.");
                } else {
                    for (br.ufc.art_gallery.modelo.Exposicao expo : listaExposicoes) {
                        txtAreaSaida.append("- " + expo.getNome() + "\n");
                    }
                }
            }
        });

        // SAIR
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}