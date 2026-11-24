package br.ufal.ic.p2.bikeshare.excecao.plano;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Lancada ao incluir no catalogo um plano com nome ja usado. */
public class PlanoJaExisteException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public PlanoJaExisteException() {
        super("Plano ja existe.");
    }
}
