package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


class ProdutoDAOJDBC extends ConexaoBD implements ProdutoDAO {
    @Override
    public void adicionarProduto(Produto produto) {
        try (Connection connection = obterConexao() ;
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produtos (nome, preco) VALUES (?, ?)")) {

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setDouble(2, produto.getPreco());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizarProduto(Produto produto) {
        try (Connection connection = obterConexao();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produtos SET nome = ?, preco = ? WHERE id_produto = ?")) {

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setDouble(2, produto.getPreco());
            preparedStatement.setInt(3, produto.getIdProduto());

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto atualizado com sucesso!");
            } else {
                System.out.println("Nenhum produto foi atualizado. Verifique o ID do produto.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluirProduto(int idProduto) {
        try (Connection connection = obterConexao();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produtos WHERE id_produto = ?")) {

            preparedStatement.setInt(1, idProduto);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto excluído com sucesso!");
            } else {
                System.out.println("Nenhum produto foi excluído. Verifique o ID do produto.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();

        try (Connection connection = obterConexao();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM produtos")) {

            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(resultSet.getInt("id_produto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setPreco(resultSet.getDouble("preco"));
                produtos.add(produto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}
