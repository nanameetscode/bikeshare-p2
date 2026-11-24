package br.ufal.ic.p2.bikeshare.excecao.usuario;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

public class UsuarioInadimplenteException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public UsuarioInadimplenteException() {
        super("Usuario inadimplente.");
    }
}
