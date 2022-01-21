
package br.com.testeAml.application.util;

import br.com.testeAml.dao.TransacaoDao;
import br.com.testeAml.entidade.Transacao;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;


public class Arquivo {
    
        
        public static void gravarDados(){
            //String path = "c:\\Usuários\\franc\\Documentos\\NetBeansProjects\\testeAml\\arquivoCsv\\202110_CPGF.csv";
            String path = "c:\\temp\\202110_CPGF.csv";
        
            String orgao;
            String portador;
            String favorecido;
            String transacao;
            Double valor;
        
            Transacao dadosTransacoes;
            TransacaoDao tDao = new TransacaoDao();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.ISO_8859_1))){
                br.readLine();
                String line = br.readLine();
            
            while (line != null) {                
                String[] dados = line.split(";");
                
                orgao = Utilitarios.removerCaracteresEspeciais(Utilitarios.removerAcentos(dados[3]).replace(",", ""));
                portador = Utilitarios.removerCaracteresEspeciais(Utilitarios.removerAcentos(dados[9]).replace(",", ""));
                favorecido = Utilitarios.removerCaracteresEspeciais(Utilitarios.removerAcentos(dados[11]).replace(",", ""));
                transacao = Utilitarios.filtroTipoTransacao(Utilitarios.removerCaracteresEspeciais(Utilitarios.removerAcentos(dados[12]).replace(",", "")));
                valor = Double.parseDouble(Utilitarios.removerCaracteresEspeciais(dados[14].replace(",", ".")));
                
                dadosTransacoes = new Transacao(orgao, portador, favorecido, transacao, valor);
                try {
                    tDao.salvar(dadosTransacoes);
                } catch (SQLException e) {
                    System.out.println("Erro ao enviar dados" + e.getMessage());
                }
               
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erro em ler o arquivo: " + e.getMessage());
        }
        }
        
}
