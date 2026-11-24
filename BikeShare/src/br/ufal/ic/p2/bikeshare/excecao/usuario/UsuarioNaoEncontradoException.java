package br.ufal.ic.p2.bikeshare.excecao.usuario;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Nenhum usuario cadastrado tem o identificador ou email informado. */
public class UsuarioNaoEncontradoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoEncontradoException() {
        super("Usuario nao existe.");
    }
}
