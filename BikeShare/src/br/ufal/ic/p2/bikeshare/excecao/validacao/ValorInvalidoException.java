package br.ufal.ic.p2.bikeshare.excecao.validacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Um campo foi informado, mas com formato ou valor fora do permitido. */
public class ValorInvalidoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}
