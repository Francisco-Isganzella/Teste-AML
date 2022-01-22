
package br.com.testeAml.application.tela;

import br.com.testeAml.application.util.Arquivo;
import br.com.testeAml.dao.TransacaoDao;
import br.com.testeAml.entidade.Transacao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class TelaPrincipal extends JFrame{
    
    
    public TelaPrincipal(){
        
        
        // JANELA
        setSize(900, 700);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Analise de lavagem de dinheiro");
        setResizable(false);
        
        // PAINEL HEADER
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(null);
        painelCabecalho.setBounds(0, 0, 900, 60);
        painelCabecalho.setVisible(true);
        
        JLabel labelTituloDados = new JLabel();
        labelTituloDados.setText("Dados de transações do cartão CPGF");
        labelTituloDados.setBounds(300, 30, 500, 30);
        labelTituloDados.setFont(new Font("Null", Font.BOLD, 16));
        painelCabecalho.add(labelTituloDados);
        
        JButton carregarDadosDoArquivo = new JButton("Carregar arquivos no banco de dados");
        carregarDadosDoArquivo.setBounds(610, 10, 250, 20);
        painelCabecalho.add(carregarDadosDoArquivo);
        
        JButton procurarArquivoCsv = new JButton("Procurar");
        procurarArquivoCsv.setBounds(495, 10, 100, 20);
        painelCabecalho.add(procurarArquivoCsv);
        
        JLabel labelArquivo = new JLabel();
        labelArquivo.setText("Arquivo");
        labelArquivo.setBounds(20, 10, 50, 20);
        labelArquivo.setFont(new Font("Null", Font.BOLD, 12));
        painelCabecalho.add(labelArquivo);
        
        JTextField campoArquivo = new JTextField();
        campoArquivo.setBounds(70, 10, 410, 20);
        campoArquivo.setEnabled(false);
        painelCabecalho.add(campoArquivo);
        
        JLabel labelAguardeCarregando = new JLabel();
        labelAguardeCarregando.setText("Aguarde, enviando dados...");
        labelAguardeCarregando.setBounds(600, 10, 300, 20);
        labelAguardeCarregando.setFont(new Font("Null", Font.BOLD, 16));
        labelAguardeCarregando.setVisible(false);
        painelCabecalho.add(labelAguardeCarregando);
        
        procurarArquivoCsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileFilter filter = new FileNameExtensionFilter("filtro", "csv");
                JFileChooser fc = new JFileChooser();
                fc.addChoosableFileFilter(filter);
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.showOpenDialog(carregarDadosDoArquivo);
                File f = fc.getSelectedFile();
                campoArquivo.setText(f.getPath());
                Arquivo.setPath(f.getPath());
                System.out.println(Arquivo.getPath());
            }
        });
        
        getContentPane().add(painelCabecalho);

        
        // TABELA DADOS
        DefaultTableModel tabelaModeloDados = new DefaultTableModel();
        JTable tabelaDados = new JTable(tabelaModeloDados);
        tabelaDados.setLayout(null);
        tabelaDados.setBounds(20, 60, 840, 200);
        tabelaModeloDados.addColumn("Id");
        tabelaModeloDados.addColumn("Órgão");
        tabelaModeloDados.addColumn("Portador");
        tabelaModeloDados.addColumn("Favorecido");
        tabelaModeloDados.addColumn("Transação");
        tabelaModeloDados.addColumn("Valor");
        tabelaDados.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaDados.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaDados.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabelaDados.setDefaultEditor(Object.class, null);
        
        JScrollPane scrollPaneDados = new JScrollPane(tabelaDados);
        scrollPaneDados.setBounds(20, 60, 840, 200);
        carregarTabelaDados(tabelaModeloDados);
        
        getContentPane().add(scrollPaneDados);
        
        if (tabelaDados.getRowCount() > 0) {
            carregarDadosDoArquivo.setVisible(false);
            labelArquivo.setVisible(false);
            campoArquivo.setVisible(false);
            procurarArquivoCsv.setVisible(false);
        }
        
        // PAINEL BODY
        JPanel painelCorpo = new JPanel();
        painelCorpo.setLayout(null);
        painelCorpo.setBounds(0, 260, 900, 70);
        painelCorpo.setVisible(true);
        
        JLabel labelTituloSigilosos = new JLabel();
        labelTituloSigilosos.setText("Transações sigilosas");
        labelTituloSigilosos.setBounds(140, 30, 500, 30);
        labelTituloSigilosos.setFont(new Font("Null", Font.BOLD, 16));
        painelCorpo.add(labelTituloSigilosos);
        
        JLabel labelTituloSigilososPorOrgao = new JLabel();
        labelTituloSigilososPorOrgao.setText("Transações sigilosas por órgão");
        labelTituloSigilososPorOrgao.setBounds(530, 30, 500, 30);
        labelTituloSigilososPorOrgao.setFont(new Font("Null", Font.BOLD, 16));
        painelCorpo.add(labelTituloSigilososPorOrgao);
        
        JLabel labelValorTotal = new JLabel();
        labelValorTotal.setText("Total:");
        labelValorTotal.setBounds(700, 8, 100, 20);
        labelValorTotal.setFont(new Font("Null", Font.BOLD, 14));
        painelCorpo.add(labelValorTotal);
        
        JLabel labelTotal = new JLabel();
        carregarSoma(labelTotal);
        labelTotal.setBounds(750, 3, 90, 30);
        labelTotal.setBorder(BorderFactory.createLoweredBevelBorder());
        painelCorpo.add(labelTotal);
        
        getContentPane().add(painelCorpo);
        
        // TABELA SIGILOSOS
        DefaultTableModel tabelaModeloDadosSigilosos = new DefaultTableModel();
        JTable tabelaDadosSigilosos = new JTable(tabelaModeloDadosSigilosos);
        tabelaDadosSigilosos.setLayout(null);
        tabelaDadosSigilosos.setBounds(20, 330, 410, 100);
        tabelaModeloDadosSigilosos.addColumn("Id");
        tabelaModeloDadosSigilosos.addColumn("Órgão");
        tabelaModeloDadosSigilosos.addColumn("Valor");
        tabelaDadosSigilosos.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaDadosSigilosos.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaDadosSigilosos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabelaDadosSigilosos.setDefaultEditor(Object.class, null);
        
        JScrollPane scrollPaneDadosSigilosos = new JScrollPane(tabelaDadosSigilosos);
        scrollPaneDadosSigilosos.setBounds(20, 330, 410, 100);
        carregarTabelaSigilosa(tabelaModeloDadosSigilosos);
        
        getContentPane().add(scrollPaneDadosSigilosos);
        
        
        
        // TABELA SIGILOSOS POR ORGAO
        DefaultTableModel tabelaModeloDadosSigilososPorOrgao = new DefaultTableModel();
        JTable tabelaDadosSigilososPorOrgao = new JTable(tabelaModeloDadosSigilososPorOrgao);
        tabelaDadosSigilososPorOrgao.setLayout(null);
        tabelaDadosSigilososPorOrgao.setBounds(450, 330, 410, 100);
        tabelaModeloDadosSigilososPorOrgao.addColumn("Id");
        tabelaModeloDadosSigilososPorOrgao.addColumn("Órgão");
        tabelaModeloDadosSigilososPorOrgao.addColumn("Quantidade transacoes");
        tabelaModeloDadosSigilososPorOrgao.addColumn("Valor");
        tabelaDadosSigilososPorOrgao.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaDadosSigilososPorOrgao.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaDadosSigilososPorOrgao.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabelaDadosSigilososPorOrgao.setDefaultEditor(Object.class, null);
        
        JScrollPane scrollPaneDadosSigilososPorOrgao = new JScrollPane(tabelaDadosSigilososPorOrgao);
        scrollPaneDadosSigilososPorOrgao.setBounds(450, 330, 410, 100);
        
        carregarTabelaSigilosaPorOrgao(tabelaModeloDadosSigilososPorOrgao);
        
        getContentPane().add(scrollPaneDadosSigilososPorOrgao);
        
        // PAINEL FOOTER
        JPanel painelRodape = new JPanel();
        painelRodape.setLayout(null);
        painelRodape.setBounds(0, 430, 900, 80);
        painelRodape.setVisible(true);
        
        JLabel labelTituloSaques = new JLabel();
        labelTituloSaques.setText("Saques por portador");
        labelTituloSaques.setBounds(140, 40, 500, 30);
        labelTituloSaques.setFont(new Font("Null", Font.BOLD, 16));
        painelRodape.add(labelTituloSaques);
        
        JLabel labelTituloFavorecido = new JLabel();
        labelTituloFavorecido.setText("Favorecidos");
        labelTituloFavorecido.setBounds(600, 40, 500, 30);
        labelTituloFavorecido.setFont(new Font("Null", Font.BOLD, 16));
        painelRodape.add(labelTituloFavorecido);
        
        JLabel labelValorTotalSigilosos = new JLabel();
        labelValorTotalSigilosos.setText("Total:");
        labelValorTotalSigilosos.setBounds(250, 8, 100, 20);
        labelValorTotalSigilosos.setFont(new Font("Null", Font.BOLD, 14));
        painelRodape.add(labelValorTotalSigilosos);
        
        JLabel labelTotalSigilosos = new JLabel();
        carregarSomaSigilosos(labelTotalSigilosos);
        labelTotalSigilosos.setBounds(300, 3, 90, 30);
        labelTotalSigilosos.setBorder(BorderFactory.createLoweredBevelBorder());
        painelRodape.add(labelTotalSigilosos);
        
        getContentPane().add(painelRodape);
        
        
        // TABELA SAQUES
        DefaultTableModel tabelaModeloDadosSaques = new DefaultTableModel();
        JTable tabelaDadosSaques = new JTable(tabelaModeloDadosSaques);
        tabelaDadosSaques.setLayout(null);
        tabelaDadosSaques.setBounds(20, 510, 410, 100);
        tabelaModeloDadosSaques.addColumn("Id");
        tabelaModeloDadosSaques.addColumn("Portador");
        tabelaModeloDadosSaques.addColumn("Órgão");
        tabelaModeloDadosSaques.addColumn("Quantidade");
        tabelaModeloDadosSaques.addColumn("Valor");
        tabelaDadosSaques.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaDadosSaques.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaDadosSaques.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabelaDadosSaques.setDefaultEditor(Object.class, null);
        
        JScrollPane scrollPaneDadosSaques = new JScrollPane(tabelaDadosSaques);
        scrollPaneDadosSaques.setBounds(20, 510, 410, 100);
        
        carregarTabelaSaque(tabelaModeloDadosSaques);
        
        getContentPane().add(scrollPaneDadosSaques);
        
        
        // TABELA FAVORECIDOS
        DefaultTableModel tabelaModeloDadosFavorecidos = new DefaultTableModel();
        JTable tabelaDadosFavorecidos = new JTable(tabelaModeloDadosFavorecidos);
        tabelaDadosFavorecidos.setLayout(null);
        tabelaDadosFavorecidos.setBounds(450, 510, 410, 100);
        tabelaModeloDadosFavorecidos.addColumn("Id");
        tabelaModeloDadosFavorecidos.addColumn("Favorecido");
        tabelaModeloDadosFavorecidos.addColumn("Quantidade transacoes");
        tabelaModeloDadosFavorecidos.addColumn("Valor");
        tabelaDadosFavorecidos.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaDadosFavorecidos.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaDadosFavorecidos.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabelaDadosFavorecidos.setDefaultEditor(Object.class, null);
        
        JScrollPane scrollPaneDadosFavorecidos = new JScrollPane(tabelaDadosFavorecidos);
        scrollPaneDadosFavorecidos.setBounds(450, 510, 410, 100);
        
        carregarTabelaFavorecidos(tabelaModeloDadosFavorecidos);
        
        getContentPane().add(scrollPaneDadosFavorecidos);
        
        // PAINEL FOOTER2
        JPanel painelRodapeAbaixo = new JPanel();
        painelRodapeAbaixo.setLayout(null);
        painelRodapeAbaixo.setBounds(0, 610, 900, 50);
        painelRodapeAbaixo.setVisible(true);
        
        JLabel labelTotalSaques = new JLabel();
        carregaSomaMovimentacoesSaque(labelTotalSaques);
        labelTotalSaques.setBounds(300, 3, 90, 30);
        labelTotalSaques.setBorder(BorderFactory.createLoweredBevelBorder());
        painelRodapeAbaixo.add(labelTotalSaques);
        
        JLabel labelValorTotalSaques = new JLabel();
        labelValorTotalSaques.setText("Total:");
        labelValorTotalSaques.setBounds(250, 8, 100, 20);
        labelValorTotalSaques.setFont(new Font("Null", Font.BOLD, 14));
        painelRodapeAbaixo.add(labelValorTotalSaques);
        
        getContentPane().add(painelRodapeAbaixo);
        
        
        carregarDadosDoArquivo.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            labelAguardeCarregando.setVisible(true);
            carregarDadosDoArquivo.setVisible(false);
            Arquivo.gravarDados();
            carregarTabelaDados(tabelaModeloDados);
            carregarTabelaFavorecidos(tabelaModeloDadosFavorecidos);
            carregarTabelaSaque(tabelaModeloDadosSaques);
            carregarTabelaSigilosa(tabelaModeloDadosSigilosos);
            carregarTabelaSigilosaPorOrgao(tabelaModeloDadosSigilososPorOrgao);
            carregarSoma(labelTotal);
            carregarSomaSigilosos(labelTotalSigilosos);
            carregaSomaMovimentacoesSaque(labelTotalSaques);
            labelAguardeCarregando.setVisible(false);
            labelArquivo.setVisible(false);
            campoArquivo.setVisible(false);
            procurarArquivoCsv.setVisible(false);
        }
        });
    }
    
    public static void carregarTabelaDados(DefaultTableModel tabelaModelo){
        tabelaModelo.getDataVector().clear();
        List<Transacao> listaTransacoes = new ArrayList();
        TransacaoDao tDao = new TransacaoDao();
        try {
            listaTransacoes = tDao.listarTransacoes();
        } catch (SQLException e) {
            System.out.println("Erro ao carregar tabela de dados " + e.getMessage());
        }
        if (!listaTransacoes.isEmpty()) {
            for (Transacao t : listaTransacoes) {
                tabelaModelo.addRow(new Object[]{t.getIdTransacao(), t.getOrgao(), t.getPortador(), t.getFavorecido(), t.getTipoTransacao(), String.format("R$ %.2f", t.getValor())});
            }
        }
    }
    
    public static void carregarTabelaSigilosa(DefaultTableModel tabelaModelo){
        tabelaModelo.getDataVector().clear();
        List<Transacao> listaTransacoes = new ArrayList();
        TransacaoDao tDao = new TransacaoDao();
        try {
            listaTransacoes = tDao.listarTransacoesSigilosas();
        } catch (SQLException e) {
            System.out.println("Erro ao carregar tabela de sigilosas " + e.getMessage());
        }
        if (!listaTransacoes.isEmpty()) {
            for (Transacao t : listaTransacoes) {
                tabelaModelo.addRow(new Object[]{t.getIdTransacao(), t.getOrgao(), String.format("R$ %.2f", t.getValor())});
            }
        }
    }
    
    public static void carregarTabelaSigilosaPorOrgao(DefaultTableModel tabelaModelo){
        tabelaModelo.getDataVector().clear();
        List<Transacao> listaTransacoes = new ArrayList();
        TransacaoDao tDao = new TransacaoDao();
        try {
            listaTransacoes = tDao.listarTransacoesSigilosasPorOrgao();
        } catch (SQLException e) {
            System.out.println("Erro ao carregar tabela de sigilosas " + e.getMessage());
        }
        if (!listaTransacoes.isEmpty()) {
            for (Transacao t : listaTransacoes) {
                tabelaModelo.addRow(new Object[]{t.getIdTransacao(), t.getOrgao(), t.getCount(), String.format("R$ %.2f", somaMovimentacoesSigilosasPorOrgao(t.getOrgao()))});
                
            }
        }
    }
    
    public static void carregarTabelaFavorecidos(DefaultTableModel tabelaModelo){
        tabelaModelo.getDataVector().clear();
        List<Transacao> listaTransacoes = new ArrayList();
        TransacaoDao tDao = new TransacaoDao();
        try {
            listaTransacoes = tDao.listarTransacoesSigilosasPorFavorecido();
        } catch (SQLException e) {
            System.out.println("Erro ao carregar tabela de favorecidos " + e.getMessage());
        }
        if (!listaTransacoes.isEmpty()) {
            for (Transacao t : listaTransacoes) {
                tabelaModelo.addRow(new Object[]{t.getIdTransacao(), t.getFavorecido(), t.getCount(), String.format("R$ %.2f", somaMovimentacoesPorNome(t.getFavorecido()))});
            }
        }
    }
    
    public static void carregarTabelaSaque(DefaultTableModel tabelaModelo){
        tabelaModelo.getDataVector().clear();
        List<Transacao> listaTransacoes = new ArrayList();
        TransacaoDao tDao = new TransacaoDao();
        try {
            listaTransacoes = tDao.listarTransacoesSaque();
        } catch (SQLException e) {
            System.out.println("Erro ao carregar tabela de saques " + e.getMessage());
        }
        if (!listaTransacoes.isEmpty()) {
            for (Transacao t : listaTransacoes) {
                tabelaModelo.addRow(new Object[]{t.getIdTransacao(), t.getPortador(), t.getOrgao(), t.getCount(), String.format("R$ %.2f", somaMovimentacoesSaquePorNome(t.getPortador()))});
            }
        }
    }
    
    public static double somaValores(List<Transacao> lista){
        double temp = 0.0;
        for (Transacao t : lista) {
            temp += t.getValor();
        }
        return temp;
    }
    
    public static void carregarSoma(JLabel label){
        List<Transacao> listaTransacoes = new ArrayList();
        TransacaoDao tDao = new TransacaoDao();
        try {
        listaTransacoes = tDao.listarTransacoes();
        } catch (SQLException e) {
        System.out.println("Erro ao carregar lista de dados " + e.getMessage());
        }
        double somaValores = somaValores(listaTransacoes);
        label.setText(String.format("R$ %.2f", somaValores));
    }
    
    public static void carregarSomaSigilosos(JLabel label){
        List<Transacao> listaTransacoes = new ArrayList();
        TransacaoDao tDao = new TransacaoDao();
        try {
        listaTransacoes = tDao.listarTransacoesSigilosas();
        } catch (SQLException e) {
        System.out.println("Erro ao carregar lista de dados " + e.getMessage());
        }
        double somaValores = somaValores(listaTransacoes);
        label.setText(String.format("R$ %.2f", somaValores));
    }
    
    public static void carregarValorTotalPorOrgao(String orgao){
        List<Transacao> listaTransacoes = new ArrayList();
        TransacaoDao tDao = new TransacaoDao();
        try {
            listaTransacoes = tDao.listarTransacoesSigilosasValorPorOrgao(orgao);
        } catch (SQLException e) {
            System.out.println("Erro ao carregar tabela de transacoes sigilosas por orgao " + e.getMessage());
        }
        for (Transacao t : listaTransacoes) {
            System.out.println(t);
        }
    }
    
    public static double somaMovimentacoesSigilosasPorOrgao(String orgao){
        List<Transacao> listaTransacoes = new ArrayList<>();
        TransacaoDao tDao = new TransacaoDao();
        double soma = 0.0;
        try {
            listaTransacoes = tDao.listarTransacoes();
        } catch (SQLException e) {
            System.out.println("erro " + e.getMessage());
        }
        for (Transacao t : listaTransacoes) {
            if (t.getOrgao().equals(orgao) && t.getTipoTransacao().equals("Sigiloso")) {
                soma += t.getValor();
            }
        }
        return soma;
    }
    
    public static double somaMovimentacoesSaquePorNome(String nome){
        List<Transacao> listaTransacoes = new ArrayList<>();
        TransacaoDao tDao = new TransacaoDao();
        double soma = 0.0;
        try {
            listaTransacoes = tDao.listarTransacoes();
        } catch (SQLException e) {
            System.out.println("erro " + e.getMessage());
        }
        for (Transacao t : listaTransacoes) {
            if (t.getPortador().equals(nome) && t.getTipoTransacao().equals("SAQUE")) {
                soma += t.getValor();
            }
        }
        return soma;
    }
    
    public static double somaMovimentacoesPorNome(String nome){
        List<Transacao> listaTransacoes = new ArrayList<>();
        TransacaoDao tDao = new TransacaoDao();
        double soma = 0.0;
        try {
            listaTransacoes = tDao.listarTransacoes();
        } catch (SQLException e) {
            System.out.println("erro " + e.getMessage());
        }
        for (Transacao t : listaTransacoes) {
            if (t.getFavorecido().equals(nome)) {
                soma += t.getValor();
            }
        }
        return soma;
    }
    
    public static void carregaSomaMovimentacoesSaque(JLabel label){
        List<Transacao> listaTransacoes = new ArrayList<>();
        TransacaoDao tDao = new TransacaoDao();
        double soma = 0.0;
        try {
            listaTransacoes = tDao.listarTransacoes();
        } catch (SQLException e) {
            System.out.println("erro " + e.getMessage());
        }
        for (Transacao t : listaTransacoes) {
            if (t.getTipoTransacao().equals("SAQUE")) {
                soma += t.getValor();
            }
        }
        label.setText(String.format("R$ %.2f", soma));
    }
    
    
}
