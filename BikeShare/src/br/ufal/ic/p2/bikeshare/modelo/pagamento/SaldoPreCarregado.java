package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.PagamentoRecusadoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Pagamento com saldo recarregado. O usuario guarda sempre a mesma carteira,
 * entao o saldo nao se perde quando ele troca de metodo e depois volta.
 */
public class SaldoPreCarregado extends MetodoPagamento {

    public static final String TIPO = "saldoPreCarregado";
    private static final long serialVersionUID = 1L;
    private static final String SALDO_INSUFICIENTE = "Saldo insuficiente.";

    private BigDecimal saldo = BigDecimal.ZERO;

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public BigDecimal getSaldo() {
        return saldo;
    }

    @Override
    public void recarregar(BigDecimal valor) {
        if (valor.signum() <= 0) {
            throw new ValorInvalidoException("Valor deve ser positivo.");
        }
        saldo = saldo.add(valor);
    }

    @Override
    public void autorizarRetirada(LocalDateTime data) {
        if (saldo.signum() <= 0) {
            throw new PagamentoRecusadoException(SALDO_INSUFICIENTE);
        }
    }

    @Override
    public void cobrar(BigDecimal valor) {
        if (valor.signum() < 0) {
            throw new ValorInvalidoException("Valor nao pode ser negativo.");
        }
        if (valor.compareTo(saldo) > 0) {
            throw new PagamentoRecusadoException(SALDO_INSUFICIENTE);
        }
        saldo = saldo.subtract(valor);
    }
}
