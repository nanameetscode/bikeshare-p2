package br.ufal.ic.p2.bikeshare.excecao.pagamento;

/** Lancada na retirada por usuario sem forma de pagamento. */
public class MetodoPagamentoNaoCadastradoException extends PagamentoRecusadoException {

    private static final long serialVersionUID = 1L;

    public MetodoPagamentoNaoCadastradoException() {
        super("Metodo de pagamento nao cadastrado.");
    }
}
