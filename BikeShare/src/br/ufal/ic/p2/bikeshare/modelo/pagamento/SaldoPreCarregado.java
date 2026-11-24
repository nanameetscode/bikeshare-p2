package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.PagamentoRecusadoException;

import java.math.BigDecimal;

/** Pagamento com saldo recarregado. O saldo fica no usuario, para nao se perder na troca de metodo. */
public class SaldoPreCarregado extends MetodoPagamento {

    public static final String TIPO = "saldoPreCarregado";
    private static final long serialVersionUID = 1L;
    private static final String SALDO_INSUFICIENTE = "Saldo insuficiente.";

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public void exigirSaldoPreCarregado() {
    }

    @Override
    public void autorizarRetirada(BigDecimal saldo) {
        if (saldo.signum() <= 0) {
            throw new PagamentoRecusadoException(SALDO_INSUFICIENTE);
        }
    }

    @Override
    public BigDecimal cobrar(BigDecimal saldo, BigDecimal valor) {
        if (valor.compareTo(saldo) > 0) {
            throw new PagamentoRecusadoException(SALDO_INSUFICIENTE);
        }
        return saldo.subtract(valor);
    }
}
