package br.ufal.ic.p2.bikeshare.excecao.bicicleta;

/** Lancada ao devolver uma bicicleta que nao foi retirada. */
public class BicicletaNaoEmUsoException extends OperacaoBicicletaInvalidaException {

    private static final long serialVersionUID = 1L;

    public BicicletaNaoEmUsoException() {
        super("Bicicleta nao esta em uso.");
    }
}
