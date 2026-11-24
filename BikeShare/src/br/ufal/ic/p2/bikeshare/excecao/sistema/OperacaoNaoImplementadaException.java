package br.ufal.ic.p2.bikeshare.excecao.sistema;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Comando previsto na Facade, mas de uma User Story ainda nao implementada. */
public class OperacaoNaoImplementadaException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public OperacaoNaoImplementadaException() {
        super("Ainda nao implementado.");
    }
}
