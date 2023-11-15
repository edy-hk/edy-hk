package org.example;

import java.util.List;

// Interface para operações de banco de dados
interface ProdutoDAO {
    void adicionarProduto(Produto produto);
    void atualizarProduto(Produto produto);
    void excluirProduto(int idProduto);
    List<Produto> listarProdutos();
}

// Classe abstrata para encapsular a conexão e operações com o banco de dados
class Produto {
    private int idProduto;
    private String nome;
    private double preco;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
