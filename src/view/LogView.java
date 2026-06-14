package view;

import model.LogEntry;
import java.util.List;
import java.util.Scanner;

public class LogView {
    private final Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {

        System.out.println("HISTORICO DE LOGS");
        System.out.println("1 - Listar todos os logs ");
        System.out.println("2 - Editar log ");
        System.out.println("3 - Remover log");
        System.out.println("4 - Limpar todos os logs");
        System.out.println("0 - Voltar");
        System.out.print("Opcao: ");
    }

    public void exibirLista(List<LogEntry> logs) {
        if (logs.isEmpty()) {
            System.out.println("Nenhum log encontrado.");
            return;
        }
        System.out.println("\n=== LOGS (" + logs.size() + " registros) ===");
        for (LogEntry log : logs) {
            System.out.println(log);
        }
    }

    public void exibirLog(LogEntry log) {
        if (log == null) {
            System.out.println("Log nao encontrado.");
            return;
        }
        System.out.println("\n" + log);
    }

    public int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public boolean confirmar(String mensagem) {
        System.out.print(mensagem + " (s/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("s");
    }

    public void exibirSucesso(String mensagem) {
        System.out.println("✔ " + mensagem);
    }

    public void exibirErro(String mensagem) {
        System.out.println("✘ ERRO: " + mensagem);
    }

    public void pausar() {
        System.out.print("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}
