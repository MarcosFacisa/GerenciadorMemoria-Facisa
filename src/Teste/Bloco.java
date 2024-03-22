package Teste;
public class Bloco {
    private int espacoTotal;
    private int espacoLivre;
    private Process processo;
    private boolean isAlocado;
    private int id;

    public Bloco(int espacoTotal) {
        this.espacoTotal = espacoTotal;
        this.espacoLivre = espacoTotal;
        this.processo = null;
        this.isAlocado = false;
        this.id = id;
    }

    public int getEspacoTotal() {
        return espacoTotal;
    }

    public void setEspacoTotal(int espacoTotal) {
        this.espacoTotal = espacoTotal;
    }

    public int getEspacoLivre() {
        return espacoLivre;
    }

    public void setEspacoLivre(int espacoLivre) {
        this.espacoLivre = espacoLivre;
    }

    public Process getProcesso() {
        return processo;
    }

    public void setProcesso(Process processo) {
        this.processo = processo;
    }

    public boolean isAlocado() {
        return isAlocado;
    }

    public void setAlocado(boolean alocado) {
        isAlocado = alocado;
    }

    public void setEspacoEmUso(int espacoEmUso) {
        this.espacoLivre = this.espacoTotal - espacoEmUso;
    }

    public int getEspacoEmUso() {
        return this.espacoTotal - this.espacoLivre;
    }

    public int getTamanho() {
        return espacoTotal; // Retorna o tamanho total do bloco
    }

    public int getId() {
        return id;
    }
}