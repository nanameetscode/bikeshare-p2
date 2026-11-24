package br.ufal.ic.p2.bikeshare.excecao.bicicleta;

/** Lancada quando a bicicleta nao esta na estacao informada. */
public class BicicletaForaDaEstacaoException extends OperacaoBicicletaInvalidaException {

    private static final long serialVersionUID = 1L;

    public BicicletaForaDaEstacaoException() {
        super("Bicicleta nao esta nesta estacao.");
    }
}
