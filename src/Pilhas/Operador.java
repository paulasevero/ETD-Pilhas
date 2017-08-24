package Pilhas;

/**
 *
 * @author Paula
 */
public enum Operador {
    ADICAO(1, '+'),
    SUBTRACAO(1, '-'),
    MULTIPLICACAO(2, '*'),
    DIVISAO(2, '/'),
    EXPONENCIACAO(3, '^'),
    RAIZ(3, '#'),
    PARENTESESABERTO(4, '('),
    PARENTESESFECHADO(4, ')');

    public int prioridade;
    char forma;

    Operador(int prioridade, char forma) {
        this.prioridade = prioridade;
        this.forma = forma;
    }

    int getPrioridade() {
        return prioridade;
    }

    char getForma() {
        return forma;
    }
}
