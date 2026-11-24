package br.ufal.ic.p2.bikeshare.excecao.usuario;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

public class UsuarioComAluguelException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public UsuarioComAluguelException(String mensagem) {
        super(mensagem);
    }
}
