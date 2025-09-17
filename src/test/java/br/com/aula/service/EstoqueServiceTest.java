package br.com.aula.service;

import br.com.aula.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*; // Apenas esta linha para asserções

public class EstoqueServiceTest {

    private EstoqueService estoqueService;

    @BeforeEach
    void setUp() {
        estoqueService = new EstoqueService();
    }

    @Test
    void deveCadastrarProdutoComSucesso() {
        Produto novoProduto = estoqueService.cadastrarNovoProduto("Caneta Azul", "Caneta esferográfica azul", 100);

        assertNotNull(novoProduto);
        assertNotNull(novoProduto.getId());
        assertEquals("Caneta Azul", novoProduto.getNome());
    }

    @Test
    void naoDeveCadastrarProdutoComNomeDuplicado() {
        estoqueService.cadastrarNovoProduto("Lápis Preto", "Lápis HB", 200);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estoqueService.cadastrarNovoProduto("Lápis Preto", "Outra descrição", 50);
        });

        assertEquals("Já existe um produto com o nome informado.", exception.getMessage());
    }

    @Test
    void naoDeveCadastrarProdutoComQuantidadeNegativa() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estoqueService.cadastrarNovoProduto("Borracha", "Borracha branca", -10);
        });

        assertEquals("A quantidade inicial não pode ser negativa.", exception.getMessage());
    }
    @Test
    void deveListarProdutosCadastrados() {
        // Preparação
        estoqueService.cadastrarNovoProduto("Caneta Azul", "Caneta esferográfica", 100);
        estoqueService.cadastrarNovoProduto("Lápis Preto", "Lápis HB", 200);

        // Ação
        List<Produto> produtos = estoqueService.listarTodosProdutos();

        // Verificação
        assertNotNull(produtos);
        assertEquals(2, produtos.size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverProdutos() {
        // Ação
        List<Produto> produtos = estoqueService.listarTodosProdutos();

        // Verificação
        assertNotNull(produtos);
        assertTrue(produtos.isEmpty());
    }
    //teste
}