package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.MetodoPagamentoIncompativelException;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Forma como o usuario paga as corridas. Por padrao nenhum dado especifico existe;
 * cada subclasse sobrescreve so os dados que realmente possui.
 */
public abstract class MetodoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String NAO_USA_CARTAO = "Usuario nao usa cartao.";
    private static final String NAO_USA_PIX = "Usuario nao usa pix.";
    private static final String NAO_USA_SALDO = "Usuario nao usa saldo pre-carregado.";

    /** Nome do metodo no formato dos testes (ex.: {@code pix}). */
    public abstract String getTipo();

    public String getNumeroCartao() {
        throw new MetodoPagamentoIncompativelException(NAO_USA_CARTAO);
    }

    public String getValidadeCartao() {
        throw new MetodoPagamentoIncompativelException(NAO_USA_CARTAO);
    }

    public String getChavePix() {
        throw new MetodoPagamentoIncompativelException(NAO_USA_PIX);
    }

    public void autorizarRetirada(BigDecimal saldo) {
    }

    public void exigirSaldoPreCarregado() {
        throw new MetodoPagamentoIncompativelException(NAO_USA_SALDO);
    }
}
