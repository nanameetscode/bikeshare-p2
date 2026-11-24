package br.ufal.ic.p2.bikeshare.excecao.pagamento;

/** Lancada ao pedir dado de cartao a quem paga de outro jeito. */
public class UsuarioNaoUsaCartaoException extends MetodoPagamentoIncompativelException {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoUsaCartaoException() {
        super("Usuario nao usa cartao.");
    }
}
