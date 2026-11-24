package br.ufal.ic.p2.bikeshare.excecao.estacao;

/** Lancada ao devolver bicicleta em estacao lotada. */
public class EstacaoCheiaException extends EstacaoSemVagaException {

    private static final long serialVersionUID = 1L;

    public EstacaoCheiaException() {
        super("Estacao cheia.");
    }
}
