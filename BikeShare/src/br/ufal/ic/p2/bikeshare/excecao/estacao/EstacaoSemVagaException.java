package br.ufal.ic.p2.bikeshare.excecao.estacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

public abstract class EstacaoSemVagaException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    protected EstacaoSemVagaException(String mensagem) {
        super(mensagem);
    }
}
