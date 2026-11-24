package br.ufal.ic.p2.bikeshare.excecao.validacao;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Um campo obrigatorio foi informado vazio ou so com espacos; a mensagem vem do proprio campo. */
public class CampoNuloException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public CampoNuloException(CampoObrigatorio campo) {
        super(campo.getMensagem());
    }
}
