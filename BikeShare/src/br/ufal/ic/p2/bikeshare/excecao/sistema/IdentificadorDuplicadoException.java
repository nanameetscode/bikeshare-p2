package br.ufal.ic.p2.bikeshare.excecao.sistema;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Lancada ao guardar no cadastro um identificador que ja esta em uso. */
public class IdentificadorDuplicadoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public IdentificadorDuplicadoException() {
        super("Identificador ja cadastrado.");
    }
}
