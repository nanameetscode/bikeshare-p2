package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.MetodoPagamentoNaoCadastradoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Situacao inicial do usuario: nenhum metodo de pagamento cadastrado. */
public class SemMetodoPagamento extends MetodoPagamento {

    public static final String TIPO = "nenhum";
    private static final long serialVersionUID = 1L;

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public void autorizarRetirada(LocalDateTime data) {
        throw new MetodoPagamentoNaoCadastradoException();
    }

    @Override
    public void cobrar(BigDecimal valor) {
        throw new MetodoPagamentoNaoCadastradoException();
    }
}
