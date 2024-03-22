package Teste;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.UUID;

public class Gerenciador {
    private static long nextId = 1;

    public static void firstFit(int tamanho, Memoria memoria) {
        // Implementação do algoritmo First Fit
        Bloco blocoEncontrado = null;
        for (Bloco bloco : memoria.getBlocos()) {
            if (!bloco.isAlocado() && bloco.getEspacoTotal() >= tamanho) {
                blocoEncontrado = bloco;
                break;
            }
        }
        if (blocoEncontrado != null) {
            blocoEncontrado.setAlocado(true);
            blocoEncontrado.setEspacoEmUso(tamanho);
            memoria.setUltimoBlocoModificado(blocoEncontrado);
            memoria.addProcessoAlocado(new Process(tamanho));
        }
    }

    public static void nextFit(int tamanho, Memoria memoria) {
        // Implementação do algoritmo Next Fit
        Bloco blocoAnterior = memoria.getUltimoBlocoModificado();
        Bloco blocoEncontrado = null;
        if (blocoAnterior != null) {
            int posicao = memoria.getBlocos().indexOf(blocoAnterior) + 1;
            for (int i = posicao; i < memoria.getBlocos().size(); i++) {
                Bloco bloco = memoria.getBlocos().get(i);
                if (!bloco.isAlocado() && bloco.getEspacoTotal() >= tamanho) {
                    blocoEncontrado = bloco;
                    break;
                }
            }
        } else {
            for (Bloco bloco : memoria.getBlocos()) {
                if (!bloco.isAlocado() && bloco.getEspacoTotal() >= tamanho) {
                    blocoEncontrado = bloco;
                    break;
                }
            }
        }
        if (blocoEncontrado != null) {
            blocoEncontrado.setAlocado(true);
            blocoEncontrado.setEspacoEmUso(tamanho);
            memoria.setUltimoBlocoModificado(blocoEncontrado);
            memoria.addProcessoAlocado(new Process(tamanho));
        }
    }

    public static void bestFit(int tamanho, Memoria memoria) {
        Bloco blocoEncontrado = null;
        int menorDiferenca = Integer.MAX_VALUE;
        for (Bloco bloco : memoria.getBlocos()) {
            if (!bloco.isAlocado() && bloco.getEspacoTotal() >= tamanho && (bloco.getEspacoTotal() - tamanho) < menorDiferenca) {
                menorDiferenca = bloco.getEspacoTotal() - tamanho;
                blocoEncontrado = bloco;
            }
        }
        if (blocoEncontrado != null) {
            blocoEncontrado.setAlocado(true);
            blocoEncontrado.setEspacoEmUso(tamanho);
            memoria.setUltimoBlocoModificado(blocoEncontrado);
            Process processo = new Process(tamanho);
            processo.setIdJava(nextId++);
            memoria.addProcessoAlocado(processo);
            blocoEncontrado.setProcesso(processo);
            System.out.println("[" + blocoEncontrado.getId() + "]:    " + tamanho + "K");
            return;
        }
        System.out.println("Não foi possível alocar o processo de tamanho " + tamanho + ".");
    }

    public static void worstFit(int tamanho, Memoria memoria) {
        // Implementação do algoritmo Worst Fit
        Bloco blocoEncontrado = null;
        int maiorDiferenca = Integer.MIN_VALUE;
        for (Bloco bloco : memoria.getBlocos()) {
            if (!bloco.isAlocado() && bloco.getEspacoTotal() >= tamanho && bloco.getEspacoTotal() - tamanho > maiorDiferenca) {
                maiorDiferenca = bloco.getEspacoTotal() - tamanho;
                blocoEncontrado = bloco;
            }
        }
        if (blocoEncontrado != null) {
            blocoEncontrado.setAlocado(true);
            blocoEncontrado.setEspacoEmUso(tamanho);
            memoria.setUltimoBlocoModificado(blocoEncontrado);
            memoria.addProcessoAlocado(new Process(tamanho));
        }
    }

    public static void allocateProcess(String id, int tamanho, Memoria memoria) {
        // Implementação do algoritmo de alocação de processo
        switch (id) {
            case "1":
                firstFit(tamanho, memoria);
                break;
            case "2":
                nextFit(tamanho, memoria);
                break;
            case "3":
                bestFit(tamanho, memoria);
                break;
            case "4":
                worstFit(tamanho, memoria);
                break;
            default:
                System.out.println("Algoritmo de alocação de processo inválido.");
                return;
        }
        Process processo = memoria.getProcessosAlocados().get(memoria.getProcessosAlocados().size() - 1);
        processo.setIdJava(nextId++);
        // Encontra o bloco associado ao processo
        Bloco blocoAssociado = findAssociatedBlock(processo, memoria);
        if (blocoAssociado != null) {
            // Associa o processo ao bloco
            blocoAssociado.setProcesso(processo);
        }
    }
    
    private static Bloco findAssociatedBlock(Process processo, Memoria memoria) {
        for (Bloco bloco : memoria.getBlocos()) {
            if (bloco.isAlocado() && bloco.getProcesso() == null) {
                return bloco;
            }
        }
        return null;
    }
    
    public static void removeProcessById(String id, Memoria memoria) {
        Iterator<Process> iterator = memoria.getProcessosAlocados().iterator();
        while (iterator.hasNext()) {
            Process processo = iterator.next();
            if (processo.getId().equals(id)) {
                iterator.remove();
                // Remover o bloco associado ao processo
                for (Bloco bloco : memoria.getBlocos()) {
                    if (bloco.isAlocado() && bloco.getProcesso() != null && bloco.getProcesso().getId().equals(id)) {
                        bloco.setAlocado(false);
                        bloco.setProcesso(null);
                        break;
                    }
                }
                break;
            }
        }
    }
}
