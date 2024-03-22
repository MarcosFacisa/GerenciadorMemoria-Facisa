package Teste;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Memoria {
    private List<Bloco> blocos;
    private List<Process> processos;
    private List<Process> processosAlocados;
    private Bloco ultimoBlocoModificado;

    public Memoria() {
        this(10, 100);
    }

    public Memoria(int numBlocos, int tamanhoMaximoBloco) {
        this.blocos = new ArrayList<>();
        this.processos = new ArrayList<>();
        this.processosAlocados = new ArrayList<>();
        
        Random random = new Random(); // Criando um objeto Random
        
        // Inicializando os blocos com tamanhos variados
        for (int i = 0; i < numBlocos; i++) {
            int tamanhoBloco = random.nextInt(tamanhoMaximoBloco) + 1; // Gerando tamanho aleatório entre 1 e tamanhoMaximoBloco
            blocos.add(new Bloco(tamanhoBloco));
        }
    }

    public List<Bloco> getBlocos() {
        return blocos;
    }

    public void addBloco(Bloco bloco) {
        blocos.add(bloco);
    }

    public List<Process> getProcessos() {
        return processos;
    }

    public void addProcesso(Process processo) {
        processos.add(processo);
    }

    public void removeProcesso(String id) {
        Process processoRemovido = null;
        for (Process processo : processos) {
            if (processo.getId().equals(id)) {
                processoRemovido = processo;
                break;
            }
        }
        if (processoRemovido != null) {
            processos.remove(processoRemovido);
            // Remove o bloco associado ao processo
            for (Bloco bloco : blocos) {
                if (bloco.isAlocado() && bloco.getProcesso() != null && bloco.getProcesso().getId().equals(id)) {
                    bloco.setAlocado(false);
                    bloco.setProcesso(null);
                    break;
                }
            }
        }
    }

    public List<Process> getProcessosAlocados() {
        return processosAlocados;
    }

    public void addProcessoAlocado(Process processo) {
        processosAlocados.add(processo);
    }

    public void removeProcessoAlocado(Process processo) {
        processosAlocados.remove(processo);
    }

    public Bloco getUltimoBlocoModificado() {
        return ultimoBlocoModificado;
    }

    public void setUltimoBlocoModificado(Bloco bloco) {
        ultimoBlocoModificado = bloco;
    }
}