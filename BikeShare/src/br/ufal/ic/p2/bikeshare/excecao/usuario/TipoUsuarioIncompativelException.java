package br.ufal.ic.p2.bikeshare.excecao.usuario;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** O dado pedido so existe para o outro tipo de usuario (pessoa fisica x juridica). */
public class TipoUsuarioIncompativelException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public TipoUsuarioIncompativelException(String mensagem) {
        super(mensagem);
    }
}
