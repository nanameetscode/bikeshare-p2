package br.ufal.ic.p2.bikeshare.excecao.pagamento;

/** Lancada na retirada com cartao vencido. */
public class CartaoVencidoException extends PagamentoRecusadoException {

    private static final long serialVersionUID = 1L;

    public CartaoVencidoException() {
        super("Validade do cartao invalida.");
    }
}
