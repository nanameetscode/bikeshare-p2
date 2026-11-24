package br.ufal.ic.p2.bikeshare.excecao.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** O metodo de pagamento do usuario nao autoriza a retirada ou nao cobre o valor. */
public class PagamentoRecusadoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public PagamentoRecusadoException(String mensagem) {
        super(mensagem);
    }
}
