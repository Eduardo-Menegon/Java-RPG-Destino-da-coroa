package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogRepository {
    private static final String ARQUIVO = "log.txt";

    public List<LogEntry> listarTodos() {
        List<LogEntry> logs = new ArrayList<>();
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return logs;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            int id = 1;
            while ((linha = reader.readLine()) != null) {
                if (!linha.isBlank()) {
                    logs.add(new LogEntry(id++, linha));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler logs.");
        }
        return logs;
    }

    public boolean editar(int id, String novaMensagem) {
        List<LogEntry> logs = listarTodos();
        if (id < 1 || id > logs.size()) return false;

        logs.get(id - 1).setMensagem(novaMensagem);
        return salvarTodos(logs);
    }

    public boolean remover(int id) {
        List<LogEntry> logs = listarTodos();
        if (id < 1 || id > logs.size()) return false;

        logs.remove(id - 1);
        return salvarTodos(logs);
    }

    public void limparTodos() {
        salvarTodos(new ArrayList<>());
    }

    private boolean salvarTodos(List<LogEntry> logs) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO, false))) {
            for (LogEntry log : logs) {
                writer.println(log.getMensagem());
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar logs.");
            return false;
        }
    }

    public static boolean arquivoExiste() {
        return new File(ARQUIVO).exists();
    }
}
