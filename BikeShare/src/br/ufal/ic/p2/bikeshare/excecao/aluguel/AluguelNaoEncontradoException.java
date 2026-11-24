package br.ufal.ic.p2.bikeshare.excecao.aluguel;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

public class AluguelNaoEncontradoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public AluguelNaoEncontradoException() {
        super("Aluguel nao existe.");
    }
}
