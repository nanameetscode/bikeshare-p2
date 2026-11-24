package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.UsuarioNaoUsaSaldoException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Forma como o usuario paga as corridas. Cada subclasse decide se autoriza a retirada,
 * como cobra a corrida e quais dados proprios expoe.
 */
public abstract class MetodoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

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
        throw new UsuarioNaoUsaSaldoException();
    }

    public void recarregar(BigDecimal valor) {
        throw new UsuarioNaoUsaSaldoException();
    }
}
