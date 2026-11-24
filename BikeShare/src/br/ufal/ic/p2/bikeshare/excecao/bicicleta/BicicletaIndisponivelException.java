package br.ufal.ic.p2.bikeshare.excecao.bicicleta;

/** Lancada quando a bicicleta esta em uso ou em manutencao. */
public class BicicletaIndisponivelException extends OperacaoBicicletaInvalidaException {

    private static final long serialVersionUID = 1L;

    public BicicletaIndisponivelException() {
        super("Bicicleta nao esta disponivel.");
    }
}
