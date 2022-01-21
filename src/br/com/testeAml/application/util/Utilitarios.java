
package br.com.testeAml.application.util;

import java.text.Normalizer;


public class Utilitarios {
    
    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    
    public static String removerCaracteresEspeciais(String str){
        return str.replaceAll("^\"+|\"+$", "").replace("-", " ");
    }
    
    public static String filtroTipoTransacao(String str){
        return str.substring(0, str.indexOf(" ")).replaceAll("Informacoes", "Sigiloso");
    }
    
}
