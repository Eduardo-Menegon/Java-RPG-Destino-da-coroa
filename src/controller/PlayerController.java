package controller;

import view.PlayerView;
import model.*;
import model.Character;


public class PlayerController {
    private Player player;
    private PlayerView view;

    public PlayerController(PlayerView view){
        this.view = view;
    }

    public void criarPersonagem(){
        String nome = view.pedirNome();
        int classe = view.escolherClasse();

        switch(classe){
            case 1:
                player = new Guerreiro(nome);
                break;
            case 2:
                player = new Mago(nome);
                break;
            case 3:
                player = new Arqueiro(nome);
                break;
            default:
                view.mostrarMensagem("Classe inválida! Guerreiro selecionado.");
                player = new Guerreiro(nome);
        }
        view.mostrarMensagem("Personagem criado com sucesso");
    }

    public void mostrarPlayer(){
        if (player != null){
            view.mostrarPlayer(player);
        }
    }

    public void ganharXp(int xp){
        if (player != null){
            player.addXp(xp);
        }
    }

    public void ganharOuro(int ouro){
        if (player != null){
            player.addOuro(ouro);
        }
    }

    public void atacar(Character alvo){
        if (player != null && player.vivo()){
            player.ataque(alvo);
        }
    }

    public Player getPlayer(){
        return player;
    }
}