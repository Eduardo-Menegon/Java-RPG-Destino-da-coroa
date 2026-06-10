package controller;
import model.Character;
import view.CharacterView;

public class CharacterController {
    private Character character;
    private CharacterView view;

    public CharacterController(Character character, CharacterView view){
        this.character = character;
        this.view = view;
    }

    public void mostrarCaracter(){
        view.mostrarCharacter(character);
    }

    public void receberDano(int dano){
        character.receberDano(dano);

        if (!character.vivo()){
            view.mostrarMensagem(character.getNome() + "Foi derrotado");
        }
    }

    public boolean estaVivo(){
        return character.vivo();
    }

    public Character getCharacter(){
        return character;
    }
}