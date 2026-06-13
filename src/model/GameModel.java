package model;

public class GameModel {

    private Player player;
    private String state;
    private int inimigosDerrotados;
    private int maxHp;
    private int fase;
    private int inimigosNestaFase;
    private int gold;

    public GameModel() {
        this.state = "MENU";
        this.inimigosDerrotados = 0;
        this.fase = 1;
        this.inimigosNestaFase = 0;
        this.gold = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.maxHp = player.getHp();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getInimigosDerrotados() {
        return inimigosDerrotados;
    }

    public void incrementarInimigos() {
        this.inimigosDerrotados++;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
        this.inimigosNestaFase = 0;
    }

    public int getInimigosNestaFase() {
        return inimigosNestaFase;
    }

    public void incrementarInimigosNaFase() {
        this.inimigosNestaFase++;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void addGold(int valor) {
        if (valor > 0) {
            this.gold += valor;
        }
    }

    public boolean gastarGold(int valor) {
        if (valor > 0 && this.gold >= valor) {
            this.gold -= valor;
            return true;
        }
        return false;
    }
}
