package view;
import model.Player;

import java.util.Scanner;

public class PlayerView extends CharacterView{

    private Scanner sc = new Scanner(System.in);

    public void mostrarPlayer(Player player){

        mostrarCharacter(player);

        System.out.println("Level: " + player.getLevel());
        System.out.println("XP: " + player.getXp());
        System.out.println("Ouro: " + player.getOuro());
    }


    public String pedirNome(){
        System.out.println("Digite o nome do seu personagem: ");
        return sc.nextLine();
    }

    public int escolherClasse(){
        System.out.println("===== ESCOLHA SUA CLASSE =====");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.print("Opção: ");

        return sc.nextInt();
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

}
