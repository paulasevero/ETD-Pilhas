package Pilhas;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        int op;
        NotacaoPolonesaInvertida npi = new NotacaoPolonesaInvertidaImpl();
        System.out.println("Bem Vindos ao Programa de notação polonesa invertida");
        do {
            System.out.println("  1. Inserir Expressão\n  2. Avaliar Expressão\n  3. Imprimir Notação Polaca Invertida\n  99.Sair");
            op = t.nextInt();
            switch (op) {
                case 1:
                    t.nextLine();
                    npi.inserirExpressao(t.nextLine());
                    break;
                case 2:
                    System.out.println(npi.avaliarExpressao());
                    break;
                case 3:
                    throw new UnsupportedOperationException();
            }

        } while (op != 99);
    }
}
