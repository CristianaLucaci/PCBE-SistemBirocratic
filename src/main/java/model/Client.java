package model;

public class Client implements Runnable {

    private int id;     //numarul(id-ul) clientului
    private Birou birou;    //biroul de la care trebuie sa ia actul
    private Dosar dosar;    //dosarul clientului, gol la inceput

    public Client(int id, Birou birou, Dosar dosar) {
        this.id = id;
        this.birou = birou;
        this.dosar = dosar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Birou getBirou() {
        return birou;
    }

    public void setBirou(Birou birou) {
        this.birou = birou;
    }

    public Dosar getDosar() {
        return dosar;
    }

    public void setDosar(Dosar dosar) {
        this.dosar = dosar;
    }

    @Override
    public void run() {

    }
}
