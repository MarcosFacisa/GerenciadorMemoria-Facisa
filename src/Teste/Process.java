package Teste;
import java.util.UUID;

public class Process {
    private String id;
    private int size;
    private long idJava;
    private boolean isAllocated;

    public Process(int size) {
        this.id = UUID.randomUUID().toString();
        this.size = size;
        this.isAllocated = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getIdJava() {
        return idJava;
    }

    public void setIdJava(long idJava) {
        this.idJava = idJava;
    }

    public boolean isAllocated() {
        return isAllocated;
    }

    public void setAllocated(boolean allocated) {
        isAllocated = allocated;
    }
}