package br.ufal.ic.p2.bikeshare.excecao.aluguel;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** A operacao nao combina com a situacao atual do aluguel (em andamento ou encerrado). */
public abstract class EstadoAluguelInvalidoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    protected EstadoAluguelInvalidoException(String mensagem) {
        super(mensagem);
    }
}
