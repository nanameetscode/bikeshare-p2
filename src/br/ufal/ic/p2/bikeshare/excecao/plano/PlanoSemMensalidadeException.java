package br.ufal.ic.p2.bikeshare.excecao.plano;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** O plano nao cobra mensalidade, entao ela nao pode ser informada nem consultada. */
public class PlanoSemMensalidadeException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public PlanoSemMensalidadeException() {
        super("Plano nao possui mensalidade.");
    }
}
