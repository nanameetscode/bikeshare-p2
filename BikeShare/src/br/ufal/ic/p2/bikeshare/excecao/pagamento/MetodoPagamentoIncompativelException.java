package br.ufal.ic.p2.bikeshare.excecao.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** O dado ou operacao pedido nao existe no metodo de pagamento atual do usuario. */
public abstract class MetodoPagamentoIncompativelException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    protected MetodoPagamentoIncompativelException(String mensagem) {
        super(mensagem);
    }
}
