package br.ufal.ic.p2.bikeshare.excecao.sistema;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Lancada ao remover do cadastro um identificador que nao existe. */
public class IdentificadorInexistenteException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public IdentificadorInexistenteException() {
        super("Identificador nao cadastrado.");
    }
}
