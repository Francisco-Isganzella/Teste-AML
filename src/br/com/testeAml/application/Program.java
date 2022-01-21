
package br.com.testeAml.application;

import br.com.testeAml.application.tela.TelaPrincipal;
import java.awt.EventQueue;


public class Program {
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal();
            }
        });
        
    }
    
}
