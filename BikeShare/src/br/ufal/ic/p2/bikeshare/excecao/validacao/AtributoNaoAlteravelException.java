package br.ufal.ic.p2.bikeshare.excecao.validacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** O atributo nao existe ou e controlado pelo sistema, entao nao pode ser alterado. */
public class AtributoNaoAlteravelException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public AtributoNaoAlteravelException() {
        super("Atributo nao pode ser alterado.");
    }
}
