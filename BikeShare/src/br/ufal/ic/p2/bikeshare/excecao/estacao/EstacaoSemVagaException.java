package br.ufal.ic.p2.bikeshare.excecao.estacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

public class EstacaoSemVagaException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public EstacaoSemVagaException(String mensagem) {
        super(mensagem);
    }
}
