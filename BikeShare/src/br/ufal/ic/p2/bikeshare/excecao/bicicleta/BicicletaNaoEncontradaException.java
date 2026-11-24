package br.ufal.ic.p2.bikeshare.excecao.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

public class BicicletaNaoEncontradaException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public BicicletaNaoEncontradaException() {
        super("Bicicleta nao existe.");
    }
}
