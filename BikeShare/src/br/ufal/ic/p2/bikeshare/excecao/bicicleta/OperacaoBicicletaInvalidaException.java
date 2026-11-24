package br.ufal.ic.p2.bikeshare.excecao.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** A bicicleta existe, mas nao pode passar pela operacao pedida no estado ou lugar em que esta. */
public abstract class OperacaoBicicletaInvalidaException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    protected OperacaoBicicletaInvalidaException(String mensagem) {
        super(mensagem);
    }
}
