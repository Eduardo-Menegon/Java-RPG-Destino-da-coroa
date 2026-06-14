package controller;

import model.Inventario;
import model.Item;
import view.InventarioView;
import java.io.IOException;
import java.util.List;

public class InventarioController {
    private Inventario inventario;
    private final InventarioView view;
    private boolean executando = true;

    public InventarioController() {
        this.view = new InventarioView();
    }

    // novo construtor ← aqui, fora de tudo
    public InventarioController(Inventario inventario) {
        this.inventario = inventario;
        this.view = new InventarioView();
    }

    public void iniciarNoJogo() {
        executando = true;
        while (executando) {
            view.exibirCabecalho(inventario);
            view.exibirMenuPrincipal();
            try {
                int opcao = Integer.parseInt(System.console() != null
                        ? System.console().readLine()
                        : new java.util.Scanner(System.in).nextLine().trim());
                processarOpcaoPrincipalNoJogo(opcao); // <-- usa esse
            } catch (NumberFormatException e) {
                view.exibirErro("Opção inválida.");
            }
        }
    }
    private void acaoSairNoJogo() {
        executando = false; // só fecha o inventário, volta pro jogo
    }

    private void inicializarInventario() {
        if (Inventario.arquivoExiste() && view.perguntarCarregarSalvo()) {
            try {
                inventario = Inventario.carregar();
                view.exibirSucesso("Inventário carregado com sucesso!");
            } catch (IOException | ClassNotFoundException e) {
                view.exibirErro("Erro ao carregar. Criando novo inventário.");
                criarNovoInventario();
            }
        } else {
            criarNovoInventario();
        }
    }
    private void criarNovoInventario() {
        String[] dados = view.lerDadosPersonagem();
        String nome = dados[0].isEmpty() ? "Jogador" : dados[0];
        double capacidade;
        try {
            capacidade = Double.parseDouble(dados[1]);
        } catch (NumberFormatException e) {
            capacidade = 50.0;
        }
        inventario = new Inventario(nome, capacidade);
        view.exibirSucesso("Inventário criado para " + nome + "!");
    }

    private void processarOpcaoPrincipalNoJogo(int opcao) {
        switch (opcao) {
            case 1 -> acaoListar();
            case 2 -> acaoAdicionar();
            case 3 -> acaoEditar();
            case 4 -> acaoRemover();
            case 5 -> acaoBuscar();
            case 6 -> acaoEstatisticas();
            case 0 -> executando = false; // volta pro jogo sem salvar
            default -> view.exibirErro("Opção inválida.");
        }
    }
    private void acaoListar() {
        view.exibirMenuListar();
        int opcao = lerOpcao();
        List<Item> lista = switch (opcao) {
            case 1 -> inventario.listarTodos();
            case 2 -> inventario.listarOrdenadoPorNome();
            default -> {
                view.exibirErro("Opção inválida.");
                yield List.of();
            }
        };
        view.exibirLista(lista);
        view.pausar();
    }
    private void acaoAdicionar() {
        String[] dados = view.lerDadosItem(null);
        try {
            String nome = dados[0];
            String descricao = dados[1];
            int quantidade = Integer.parseInt(dados[3]);
            double peso = Double.parseDouble(dados[4]);
            if (nome.isEmpty()) { view.exibirErro("Nome não pode ser vazio."); return; }
            if (quantidade <= 0) { view.exibirErro("Quantidade deve ser maior que zero."); return; }
            if (peso < 0) { view.exibirErro("Peso não pode ser negativo."); return; }
            boolean ok = inventario.adicionarItem(nome, descricao, quantidade, peso);
            if (ok) {
                view.exibirSucesso("Item '" + nome + "' adicionado ao inventário!");
            } else {
                view.exibirErro("Capacidade de carga insuficiente! Item não adicionado.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            view.exibirErro("Dados inválidos. Tente novamente.");
        }
        view.pausar();
    }
    private void acaoEditar() {
        int id = view.lerInteiro("ID do item a editar: ");
        Item itemAtual = inventario.buscarPorId(id);

        if (itemAtual == null) {
            view.exibirErro("Item com ID " + id + " não encontrado.");
            view.pausar();
            return;
        }
        view.exibirDetalheItem(itemAtual);
        String[] dados = view.lerDadosItem(itemAtual);
        try {
            String nome = dados[0];
            String descricao = dados[1];
            int quantidade = Integer.parseInt(dados[3]);
            double peso = Double.parseDouble(dados[4]);
            int valor = Integer.parseInt(dados[5]);
            boolean ok = inventario.editarItem(id, nome, descricao,quantidade, peso);
            if (ok) {
                view.exibirSucesso("Item atualizado com sucesso!");
            } else {
                view.exibirErro("Não foi possível editar: capacidade de carga excedida.");
            }
        } catch (NumberFormatException e) {
            view.exibirErro("Dados inválidos.");
        }
        view.pausar();
    }
    private void acaoRemover() {
        int id = view.lerInteiro("ID do item a remover: ");
        Item item = inventario.buscarPorId(id);

        if (item == null) {
            view.exibirErro("Item não encontrado.");
            view.pausar();
            return;
        }
        view.exibirDetalheItem(item);
        if (view.confirmar("Tem certeza que deseja remover '" + item.getNome() + "'?")) {
            inventario.removerItem(id);
            view.exibirSucesso("Item removido do inventário.");
        } else {
            view.exibirInfo("Remoção cancelada.");
        }
        view.pausar();
    }
    private void acaoBuscar() {
        view.exibirMenuBuscar();
        int opcao = lerOpcao();
        switch (opcao) {
            case 1 -> {
                int id = view.lerInteiro("ID: ");
                view.exibirDetalheItem(inventario.buscarPorId(id));
            }
            case 2 -> {
                String termo = view.lerTexto("Nome (parcial): ");
                List<Item> resultado = inventario.buscarPorNome(termo);
                view.exibirInfo(resultado.size() + " resultado(s) encontrado(s):");
                view.exibirLista(resultado);
            }
        }
        view.pausar();
    }
    private void acaoEstatisticas() {
        view.exibirEstatisticas(inventario);
        view.pausar();
    }
    private void acaoSalvar() {
        try {
            inventario.salvar();
            view.exibirSucesso("Inventário salvo em 'inventario.dat'!");
        } catch (IOException e) {
            view.exibirErro("Erro ao salvar: " + e.getMessage());
        }
        view.pausar();
    }
    private void acaoSair() {
        if (view.confirmar("Deseja salvar antes de sair?")) {
            acaoSalvar();
        }
        executando = false;
    }
    private int lerOpcao() {
        try {
            return Integer.parseInt(
                    new java.util.Scanner(System.in).nextLine().trim()
            );
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private Item.Tipo escolherTipo() {
        view.exibirMenuTipos();
        int opcao = lerOpcao();
        Item.Tipo[] tipos = Item.Tipo.values();
        if (opcao >= 1 && opcao <= tipos.length) return tipos[opcao - 1];
        view.exibirErro("Tipo inválido.");
        return null;
    }
}
