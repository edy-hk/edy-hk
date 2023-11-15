package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final ProdutoDAO produtoDAO = new ProdutoDAOJDBC();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Sair");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    adicionarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    private static void adicionarProduto(Scanner scanner) {
        System.out.println("Digite o nome do produto:");
        String nome = scanner.next();

        System.out.println("Digite o preço do produto:");
        double preco = scanner.nextDouble();

        Produto novoProduto = new Produto();
        novoProduto.setNome(nome);
        novoProduto.setPreco(preco);

        produtoDAO.adicionarProduto(novoProduto);
        System.out.println("Produto adicionado com sucesso!");
    }

    private static void listarProdutos() {
        List<Produto> produtos = produtoDAO.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println("ID: " + produto.getIdProduto() + ", Nome: " + produto.getNome() + ", Preço: " + produto.getPreco());
            }
        }
    }
}
