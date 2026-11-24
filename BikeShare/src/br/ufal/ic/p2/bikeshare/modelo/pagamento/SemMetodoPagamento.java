package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.PagamentoRecusadoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** Situacao inicial do usuario: nenhum metodo de pagamento cadastrado. */
public class SemMetodoPagamento extends MetodoPagamento {

    public static final String TIPO = "nenhum";
    private static final long serialVersionUID = 1L;
    private static final String NAO_CADASTRADO = "Metodo de pagamento nao cadastrado.";

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public void autorizarRetirada(LocalDateTime data) {
        throw new PagamentoRecusadoException(NAO_CADASTRADO);
    }

    @Override
    public void cobrar(BigDecimal valor) {
        throw new PagamentoRecusadoException(NAO_CADASTRADO);
    }
}
