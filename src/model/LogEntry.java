package model;

public class LogEntry {
    private int id;
    private String mensagem;

    public LogEntry(int id, String mensagem) {
        this.id = id;
        this.mensagem = mensagem;
    }

    public int getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        if (!mensagem.isEmpty()) {
            this.mensagem = mensagem;
        }
    }

    @Override
    public String toString(){
        return String.format("[#%03d] %s", id, mensagem);
    }
}
