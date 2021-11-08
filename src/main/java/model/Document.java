package model;

public class Document {

    private String name;

    private boolean luat;       //arata daca documentul a fost sau nu luat de catre client
                                // la crearea documentului, luat va fi intotdeauna pe fals
    private int durata;     //cat timp dureaza ca un client sa-si procure un document(durata de sleep a thread-ului

    public Document(String name, int durata, boolean luat) {
        this.name = name;
        this.luat = luat;
        this.durata = durata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLuat() {
        return luat;
    }

    public void setLuat(boolean luat) {
        this.luat = luat;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }


}
