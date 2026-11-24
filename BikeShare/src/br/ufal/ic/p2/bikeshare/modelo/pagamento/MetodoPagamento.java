package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.MetodoPagamentoIncompativelException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Forma como o usuario paga as corridas. Cada subclasse decide se autoriza a retirada,
 * como cobra a corrida e quais dados proprios expoe.
 */
public abstract class MetodoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String NAO_USA_SALDO = "Usuario nao usa saldo pre-carregado.";

    /** Nome do metodo no formato dos testes (ex.: {@code pix}). */
    public abstract String getTipo();

    /** Dado proprio do metodo, como o numero do cartao ou a chave pix. */
    public String getDado(String atributo) {
        throw DadoPagamento.erroPara(atributo);
    }

    public void autorizarRetirada(LocalDateTime data) {
    }

    public void cobrar(BigDecimal valor) {
    }

    public BigDecimal getSaldo() {
        throw new MetodoPagamentoIncompativelException(NAO_USA_SALDO);
    }

    public void recarregar(BigDecimal valor) {
        throw new MetodoPagamentoIncompativelException(NAO_USA_SALDO);
    }
}
