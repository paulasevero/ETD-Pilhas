package Pilhas;

import java.util.Arrays;
import java.util.List;
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
        char[] caracteres = this.expressao.toCharArray();
        String resultado = "";
        Stack<Operador> operadores = new Stack();
        Operador temp = Operador.ADICAO;
        for (char caractere : caracteres) {
            
            Operador operador = this.getOperador(caractere).size() > 0
                                ? this.getOperador(caractere).get(0) : null;
            
            if (operador == null) {
                resultado = resultado + caractere;
            } else {
                if (operador.getPrioridade() >= temp.getPrioridade()) {
                    operadores.add(operador);
                } else {
                    resultado = resultado.concat(this.descarregarPilha(operadores));
                    operadores.add(operador);
                }
                temp = operador;
            }
        }
        resultado = resultado.concat(this.descarregarPilha(operadores));
        resultado = resultado.replaceAll("\\(", "");
        resultado = resultado.replaceAll("\\)", "");
        return resultado;
    }

    private List<Operador> getOperador(char caractere) {
        return Arrays.asList(Operador.values())
                .stream()
                .filter(operador -> operador.getForma() == caractere)
                .collect(toList());
    }

    private String descarregarPilha(Stack<Operador> pilha) {
        String retorno = "";
        while (pilha.size() > 0) {
            retorno = retorno + pilha.pop().getForma();
        }
        return retorno;
    }

}
