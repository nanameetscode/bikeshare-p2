package br.ufal.ic.p2.bikeshare.excecao.pagamento;

/** Lancada quando o saldo pre-carregado nao libera a retirada ou nao cobre a corrida. */
public class SaldoInsuficienteException extends PagamentoRecusadoException {

    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException() {
        super("Saldo insuficiente.");
    }
}
