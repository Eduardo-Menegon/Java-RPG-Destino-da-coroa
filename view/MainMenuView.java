package com.rpg.view;

import java.util.Scanner;

public class MainMenuView {
    private Scanner scanner;

    public MainMenuView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayTitle() {
        System.out.println("========================================");
        System.out.println("         DESTINO DA COROA - RPG         ");
        System.out.println("========================================");
    }

    public void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Options");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    public int getOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void displayInvalidOption() {
        System.out.println("Invalid option. Try again.");
    }

    public void displayExit() {
        System.out.println("Thanks for playing Destino da Coroa!");
    }

    public String getPlayerName() {
        System.out.print("\nEnter your name, hero: ");
        return scanner.nextLine();
    }

    public Scanner getScanner() { return scanner; }
}
