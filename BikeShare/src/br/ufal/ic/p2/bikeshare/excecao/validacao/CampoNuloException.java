package br.ufal.ic.p2.bikeshare.excecao.validacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Um campo obrigatorio foi informado vazio ou so com espacos. */
public class CampoNuloException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public CampoNuloException(String mensagem) {
        super(mensagem);
    }
}
