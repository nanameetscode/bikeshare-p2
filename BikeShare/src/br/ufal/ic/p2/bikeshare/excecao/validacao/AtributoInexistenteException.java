package br.ufal.ic.p2.bikeshare.excecao.validacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** O atributo consultado nao existe na entidade. */
public class AtributoInexistenteException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public AtributoInexistenteException() {
        super("Atributo nao existe.");
    }
}
