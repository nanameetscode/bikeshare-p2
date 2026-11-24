package br.ufal.ic.p2.bikeshare.excecao.validacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Um campo foi informado, mas com formato ou valor fora do permitido; a mensagem vem do motivo. */
public class ValorInvalidoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public ValorInvalidoException(ValorRecusado motivo) {
        super(motivo.getMensagem());
    }
}
