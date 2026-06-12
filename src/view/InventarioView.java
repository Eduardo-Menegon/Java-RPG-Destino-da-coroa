package view;

import model.Item;
import model.Inventario;
import java.util.List;
import java.util.Scanner;

public class InventarioView {
    private final Scanner scanner = new Scanner(System.in);
    public void exibirMenuPrincipal() {
        System.out.println("INVENTÁRIO RPG");
        System.out.println("1. Listar itens");
        System.out.println("2. Adicionar item");
        System.out.println("3. Editar item");
        System.out.println("4. Remover item");
        System.out.println("5. Buscar item");
        System.out.println("6. Estatísticas");
        System.out.println("7. Salvar inventário");
        System.out.println("0. Sair");
    }
    public void exibirMenuListar() {
        System.out.println("Listar");
        System.out.println("1. Todos os itens");
        System.out.println("2. Por tipo");
        System.out.println("3. Ordenado por nome");
    }
    public void exibirMenuBuscar() {
        System.out.println("Buscar");
        System.out.println("1. Por ID");
        System.out.println("2. Por nome (parcial)");
        System.out.println("3. Por tipo");
    }
    public void exibirMenuTipos() {
        System.out.println("Tipos de Item");
        Item.Tipo[] tipos = Item.Tipo.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.printf("  %d. %s%n", i + 1, tipos[i]);
        }
    }
    public void exibirCabecalho(Inventario inv) {
        System.out.printf("%nPersonagem: %-20s ─ Peso: %.1f/%.1f kg ─ Itens: %d%n",
                inv.getNomePersonagem(),
                inv.calcularPesoTotal(),
                inv.getCapacidadeMaxima(),
                inv.getTotalItens());
    }
    public void exibirLista(List<Item> itens) {
        if (itens.isEmpty()) {
            System.out.println("(nenhum item encontrado)");
            return;
        }
        System.out.println("".repeat(70));
        System.out.printf("%-5s %-20s %-10s %-6s %-8s",
                "ID", "NOME", "TIPO", "QTDE", "PESO");
        System.out.println("".repeat(70));
        for (Item item : itens) {
            System.out.println("" + item);
        }
        System.out.println("".repeat(70));
    }
    public void exibirDetalheItem(Item item) {
        if (item == null) {
            exibirErro("Item não encontrado.");
            return;
        }
        System.out.println("Detalhes do Item");
        System.out.printf("ID:%d%n", item.getId());
        System.out.printf("Nome:%s%n", item.getNome());
        System.out.printf("Qtde:%d%n", item.getQuantidade());
        System.out.printf("Peso:%.1f kg%n", item.getPeso());
        System.out.printf("Desc:%s%n", item.getDescricao());
    }
    public void exibirEstatisticas(Inventario inv) {
        System.out.println("Estatísticas");
        System.out.printf("Personagem:%s%n", inv.getNomePersonagem());
        System.out.printf("Total de itens:%d%n", inv.getTotalItens());
        System.out.printf("Peso total:%.2f / %.2f kg%n",inv.calcularPesoTotal(), inv.getCapacidadeMaxima());
        double pct = (inv.calcularPesoTotal() / inv.getCapacidadeMaxima()) * 100;
        int barras = (int)(pct / 5);
        System.out.printf("Capacidade: [%s%s] %.0f%%%n",
                "".repeat(barras), "".repeat(20 - barras), pct);
    }
    public String[] lerDadosItem(Item itemAtual) {
        System.out.println(itemAtual != null ? "\n Editar Item" : "\n Novo Item");
        String[] dados = new String[6];
        System.out.print("Nome" + (itemAtual != null ? " [" + itemAtual.getNome() + "]" : "") + ": ");
        dados[0] = lerLinhaOuManter(itemAtual != null ? itemAtual.getNome() : null);
        System.out.print("Descrição" + (itemAtual != null ? " [" + itemAtual.getDescricao() + "]" : "") + ": ");
        dados[1] = lerLinhaOuManter(itemAtual != null ? itemAtual.getDescricao() : null);
        exibirMenuTipos();
        dados[2] = scanner.nextLine().trim();
        System.out.print("Quantidade" + (itemAtual != null ? " [" + itemAtual.getQuantidade() + "]" : "") + ": ");
        dados[3] = lerLinhaOuManter(itemAtual != null ? String.valueOf(itemAtual.getQuantidade()) : null);
        System.out.print("Peso (kg)" + (itemAtual != null ? " [" + itemAtual.getPeso() + "]" : "") + ": ");
        dados[4] = lerLinhaOuManter(itemAtual != null ? String.valueOf(itemAtual.getPeso()) : null);
        return dados;
    }
    public int lerInteiro(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public String lerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    public void exibirSucesso(String msg) {
        System.out.println(" OK " + msg);
    }
    public void exibirErro(String msg) {
        System.out.println(" ERRO " + msg);
    }
    public void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
    public String[] lerDadosPersonagem() {
        System.out.println("NOVO INVENTÁRIO");
        String nome = lerTexto("Nome do personagem: ");
        String cap = lerTexto("Capacidade de carga (kg): ");
        return new String[]{nome, cap};
    }
    private String lerLinhaOuManter(String valorAtual) {
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty() && valorAtual != null) return valorAtual;
        return entrada;
    }

    public boolean confirmar(String s) {
        return Boolean.parseBoolean(null);
    }

    public boolean perguntarCarregarSalvo() {
        return Boolean.parseBoolean(null);
    }

    public void exibirInfo(String s) {
    }
}
