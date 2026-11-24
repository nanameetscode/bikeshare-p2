package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.SaldoInsuficienteException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Pagamento com saldo recarregado. O usuario guarda sempre a mesma carteira,
 * entao o saldo nao se perde quando ele troca de metodo e depois volta.
 */
public class SaldoPreCarregado extends MetodoPagamento {

    public static final String TIPO = "saldoPreCarregado";
    private static final long serialVersionUID = 1L;

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
            throw new ValorInvalidoException(ValorRecusado.VALOR_NAO_POSITIVO);
        }
        saldo = saldo.add(valor);
    }

    @Override
    public void autorizarRetirada(LocalDateTime data) {
        if (saldo.signum() <= 0) {
            throw new SaldoInsuficienteException();
        }
    }

    @Override
    public void cobrar(BigDecimal valor) {
        if (valor.signum() < 0) {
            throw new ValorInvalidoException(ValorRecusado.VALOR_NEGATIVO);
        }
        if (valor.compareTo(saldo) > 0) {
            throw new SaldoInsuficienteException();
        }
        saldo = saldo.subtract(valor);
    }
}
