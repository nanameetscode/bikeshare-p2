package br.ufal.ic.p2.bikeshare.excecao.estacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

public class EstacaoNaoEncontradaException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public EstacaoNaoEncontradaException() {
        super("Estacao nao existe.");
    }
}
