package br.com.aula.service;


import br.com.aula.model.Produto;
import br.com.aula.repository.ProdutoRepository;

public class EstoqueService {
    private final ProdutoRepository produtoRepository = new ProdutoRepository();

    public Produto cadastrarNovoProduto(String nome, String descricao, int quantidadeInicial) {
        if (quantidadeInicial < 0) {
            throw new IllegalArgumentException("A quantidade inicial não pode ser negativa.");
        }

        if (produtoRepository.buscarPorNome(nome).isPresent()) {
            throw new IllegalArgumentException("Já existe um produto com o nome informado.");
        }

        Produto produtoParaSalvar = new Produto(null, nome, descricao, quantidadeInicial);
        return produtoRepository.salvar(produtoParaSalvar);
    }
}