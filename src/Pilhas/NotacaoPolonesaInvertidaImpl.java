package Pilhas;

import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;

public class NotacaoPolonesaInvertidaImpl implements NotacaoPolonesaInvertida {

    String expressao;

    @Override
    public void inserirExpressao(String expressao) {
        this.expressao = expressao;
    }

    @Override
    public boolean avaliarExpressao() {
        Pattern pattern = Pattern.compile("([+*-\\/])[)]|([+*-\\/])[A-Z|a-z]");
        Matcher matcher = pattern.matcher(this.expressao);
        boolean parentesesCoincidem = this.expressao.length()
                - this.expressao.replace(")", "").length()
                == this.expressao.length()
                - this.expressao.replace("(", "").length();

        return parentesesCoincidem && !matcher.find() && !expressao.contains(" ");
    }

    @Override
    public String imprimirExpressao() {
        char[] caracteres;
        caracteres = this.expressao.toCharArray();
        Stack operadores = new Stack();
        for (char caractere : caracteres) {
            if (this.isOperador(caractere)) {
                
            }
        }
        return null;
    }

    private Operador getOperador(char caractere) {
        return Arrays.asList(Operador.values())
                .stream()
                .filter(operador -> operador.getForma() == caractere)
                .collect(toList())
                .get(0);
    }

    private boolean isOperador(char caractere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
