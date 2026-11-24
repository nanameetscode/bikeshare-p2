package br.ufal.ic.p2.bikeshare.excecao.sistema;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Lancada ao guardar no cadastro um item nulo ou sem identificador. */
public class RegistroInvalidoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public RegistroInvalidoException() {
        super("Registro invalido.");
    }
}
