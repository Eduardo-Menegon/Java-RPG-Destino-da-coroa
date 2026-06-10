package view;

import model.Character;
import model.*;

public class CharacterView {

    public void mostrarCharacter(Character character) {
        System.out.println("===== PERSONAGEM =====");
        System.out.println("Nome: " + character.getNome());
        System.out.println("Classe: " + character.getClass().getSimpleName());
        System.out.println("HP: " + character.getHp());
        System.out.println("Ataque: " + character.getAtaque());
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

}