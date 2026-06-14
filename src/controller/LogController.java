package controller;

import model.LogEntry;
import model.LogRepository;
import view.LogView;
import java.util.List;

public class LogController {
    private final LogRepository repository;
    private final LogView view;
    private boolean executando;

    public LogController() {
        this.repository = new LogRepository();
        this.view = new LogView();
    }

    public void iniciar() {
        executando = true;
        while (executando) {
            view.exibirMenu();
            int opcao = view.lerOpcao();
            processarOpcao(opcao);
        }
    }

    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1 -> acaoListar();
            case 2 -> acaoEditar();
            case 3 -> acaoRemover();
            case 4 -> acaoLimpar();
            case 0 -> executando = false;
            default -> view.exibirErro("Opcao invalida.");
        }
    }

    private void acaoListar() {
        List<LogEntry> logs = repository.listarTodos();
        view.exibirLista(logs);
        view.pausar();
    }

    private void acaoEditar() {
        List<LogEntry> logs = repository.listarTodos();
        view.exibirLista(logs);

        if (logs.isEmpty()) {
            view.pausar();
            return;
        }

        int id = view.lerInteiro("ID do log a editar: ");
        if (id < 1 || id > logs.size()) {
            view.exibirErro("ID invalido.");
            view.pausar();
            return;
        }

        view.exibirLog(logs.get(id - 1));
        String novaMensagem = view.lerTexto("Nova mensagem: ");

        if (novaMensagem.isEmpty()) {
            view.exibirErro("Mensagem nao pode ser vazia.");
            view.pausar();
            return;
        }

        if (repository.editar(id, novaMensagem)) {
            view.exibirSucesso("Log atualizado com sucesso!");
        } else {
            view.exibirErro("Nao foi possivel editar o log.");
        }
        view.pausar();
    }

    private void acaoRemover() {
        List<LogEntry> logs = repository.listarTodos();
        view.exibirLista(logs);

        if (logs.isEmpty()) {
            view.pausar();
            return;
        }

        int id = view.lerInteiro("ID do log a remover: ");
        if (id < 1 || id > logs.size()) {
            view.exibirErro("ID invalido.");
            view.pausar();
            return;
        }

        view.exibirLog(logs.get(id - 1));
        if (view.confirmar("Tem certeza que deseja remover este log?")) {
            if (repository.remover(id)) {
                view.exibirSucesso("Log removido com sucesso!");
            } else {
                view.exibirErro("Nao foi possivel remover o log.");
            }
        } else {
            System.out.println("Remocao cancelada.");
        }
        view.pausar();
    }

    private void acaoLimpar() {
        if (view.confirmar("Tem certeza que deseja limpar TODOS os logs?")) {
            repository.limparTodos();
            view.exibirSucesso("Todos os logs foram removidos!");
        } else {
            System.out.println("Operacao cancelada.");
        }
        view.pausar();
    }
}

