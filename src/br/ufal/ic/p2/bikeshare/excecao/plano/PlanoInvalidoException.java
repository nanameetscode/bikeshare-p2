package br.ufal.ic.p2.bikeshare.excecao.plano;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** O nome de plano informado nao corresponde a nenhum plano cadastrado. */
public class PlanoInvalidoException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public PlanoInvalidoException() {
        super("Plano invalido.");
    }
}
