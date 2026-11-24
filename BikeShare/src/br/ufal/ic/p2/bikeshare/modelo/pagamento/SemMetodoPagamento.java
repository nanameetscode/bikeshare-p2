package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.pagamento.PagamentoRecusadoException;

import java.math.BigDecimal;

/** Situacao inicial do usuario: nenhum metodo de pagamento cadastrado. */
public class SemMetodoPagamento extends MetodoPagamento {

    private static final long serialVersionUID = 1L;
    private static final String NAO_CADASTRADO = "Metodo de pagamento nao cadastrado.";

    @Override
    public String getTipo() {
        return "nenhum";
    }

    @Override
    public void autorizarRetirada(BigDecimal saldo) {
        throw new PagamentoRecusadoException(NAO_CADASTRADO);
    }
}
