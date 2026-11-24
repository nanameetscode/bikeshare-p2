package br.ufal.ic.p2.bikeshare.excecao.pagamento;

/** Lancada ao pedir a chave pix a quem paga de outro jeito. */
public class UsuarioNaoUsaPixException extends MetodoPagamentoIncompativelException {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoUsaPixException() {
        super("Usuario nao usa pix.");
    }
}
