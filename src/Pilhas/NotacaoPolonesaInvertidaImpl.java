package Pilhas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;

public class NotacaoPolonesaInvertidaImpl implements NotacaoPolonesaInvertida {

    public String expressao, resultado = "";

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
        this.resultado = "";
        while (this.expressao.contains("(")) {
            List<String> conteudosDosParenteses = this.getConteudoParenteses(this.expressao);
            conteudosDosParenteses.stream().forEach(conteudo -> {
                this.realizarNPI(conteudo);
                this.limparExpressao(conteudo);
            });
        }
        this.realizarNPI(this.expressao);
        return this.resultado;
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

    private List<String> getConteudoParenteses(String expressao) {
        List<String> retorno = new ArrayList<>();
        String[] partesDaExpressao = expressao.split("\\(");
        partesDaExpressao = Arrays.copyOfRange(partesDaExpressao, 1, partesDaExpressao.length);
        for (String semParentesesAbertos : partesDaExpressao) {
            retorno.add(semParentesesAbertos.split("\\)")[0]);
        }
        return retorno;
    }

    private void limparExpressao(String conteudoParenteses) {
        this.expressao = this.expressao
                .replace(conteudoParenteses, "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "");
    }

    private void realizarNPI(String expressaoParametro) {
        char[] caracteres = expressaoParametro.toCharArray();
        Stack<Operador> operadores = new Stack();
        Operador temp = Operador.ADICAO;
        for (char caractere : caracteres) {
            Operador operador = this.getOperador(caractere).size() > 0
                    ? this.getOperador(caractere).get(0) : null;

            if (operador == null) {
                this.resultado = this.resultado + caractere;
            } else {
                if (operador.getPrioridade() >= temp.getPrioridade()) {
                    operadores.add(operador);
                } else {
                    this.resultado = this.resultado.concat(this.descarregarPilha(operadores));
                    operadores.add(operador);
                }
                temp = operador;
            }
        }
        this.resultado = this.resultado.concat(this.descarregarPilha(operadores));
    }

}
