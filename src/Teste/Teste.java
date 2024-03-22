package Teste;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Teste {

    private static Memoria memoria;
    private static Scanner s;

    public static void main(String[] args) {

        s = new Scanner(System.in);
        memoria = new Memoria(10, 100);

        
        int opcao = 0;

        do {
            mostraMemoria();

            System.out.println("*Escolha o algoritmo de gerenciamento de espaço:*"
                    + "\n1 - First Fit (primeiro encaixe)"
                    + "\n2 - Next Fit (próximo encaixe)"
                    + "\n3 - Best Fit (melhor encaixe)"
                    + "\n4 - Remover processo pelo ID"
                    + "\n0 - Sair");

            opcao = s.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("(FIRST FIT) Insira o tamanho do processo:");
                    int tamanho1 = s.nextInt();
                    Gerenciador.allocateProcess("1", tamanho1, memoria);
                    break;
                case 2:
                    System.out.println("(NEXT FIT) Insira o tamanho do processo:");
                    int tamanho2 = s.nextInt();
                    Gerenciador.allocateProcess("2", tamanho2, memoria);
                    break;
                case 3:
                    System.out.println("(BEST FIT) Insira o tamanho do processo:");
                    int tamanho3 = s.nextInt();
                    Gerenciador.allocateProcess("3", tamanho3, memoria);
                    break;
                case 4:
                    System.out.println("Insira o ID do processo a ser removido:");
                    String id = s.next();
                    Gerenciador.removeProcessById(id, memoria);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Escolha inválida!");
            }

        } while (opcao != 0);

    }

        private static void mostraMemoria() {
            List<Bloco> blocos = memoria.getBlocos();
            DecimalFormat df = new DecimalFormat("#00");

            String saida = "";

            for (int i = 0; i < 10; i++) {

                saida += "<Bloco " + df.format(i + 1) + ">:    " + blocos.get(i).getEspacoEmUso() + "K   de   "
                        + blocos.get(i).getEspacoTotal() + "K   " + (blocos.get(i).isAlocado() ? "(Espaço Alocado)" : "")
                        + (blocos.get(i) == memoria.getUltimoBlocoModificado() ? " <---" : "") + "\n";
        }

            System.out.println(saida);

            List<Process> processos = memoria.getProcessosAlocados();
            DecimalFormat df2 = new DecimalFormat("#00");

            String saida2 = "";

            for (int i = 0; i < processos.size(); i++) {

                saida2 += "*Processo " + processos.get(i).getId() + "*:    " + processos.get(i).getSize() + "K   "
                    + (processos.get(i).isAllocated() ? "(Espaço Alocado)" : "") + "\n";
        }

            System.out.println(saida2);
    }
}