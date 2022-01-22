
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
        private static String path = "";
    
        
        public static void gravarDados(){
            //String path = "C:\\Users\\franc\\Documents\\NetBeansProjects\\testeAml\\arquivoCsv\\202110_CPGF.csv";
        
            String orgao;
            String portador;
            String favorecido;
            String transacao;
            Double valor;
        
            Transacao dadosTransacoes;
            TransacaoDao tDao = new TransacaoDao();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(getPath()), StandardCharsets.ISO_8859_1))){
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
            br.close();
        } catch (IOException e) {
            System.out.println("Erro em ler o arquivo: " + e.getMessage());
        }
        }

    public static String getPath() {
        return path;
    }

    public static void setPath(String aPath) {
        path = aPath;
    }
        
}
