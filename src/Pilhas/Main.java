package Pilhas;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner t = new Scanner(System.in);
        int op;
        String output;
        NotacaoPolonesaInvertida npi = new NotacaoPolonesaInvertidaImpl();
        System.out.println("| Bem Vindos ao Programa de notação polonesa invertida |");
        do {
            System.out.println("\n    1. Inserir Expressão\n    2. Avaliar Expressão\n    "
                    + "3. Imprimir Notação Polaca Invertida\n    99.Sair");
            op = t.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Digite a expressão"
                            + "\n   OBS.: Para a expressão ser válida, é necessário que a mesma não contenha espaços"
                            + "\n         e seja somente operadores e números inteiros entre 0 e 9");
                    t.nextLine();
                    npi.inserirExpressao(t.nextLine());
                    break;
                case 2:
                    output = npi.avaliarExpressao() ? "Expressão Válida" : "Expressão Inválida";
                    System.out.println(output);
                    break;
                case 3:
                    output = npi.avaliarExpressao() ? npi.imprimirExpressao() 
                            : "Expressão inválida, favor inserir uma expressão válida";
                    System.out.println(output);
                    break;
            }

        } while (op != 99);
        System.out.println("Obrigado por utilizar o Programa de notação polonesa invertida ");
    }
}
