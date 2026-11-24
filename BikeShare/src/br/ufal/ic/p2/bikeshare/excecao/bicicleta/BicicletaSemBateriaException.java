package br.ufal.ic.p2.bikeshare.excecao.bicicleta;

/** Lancada quando se pede a bateria de uma bicicleta convencional. */
public class BicicletaSemBateriaException extends OperacaoBicicletaInvalidaException {

    private static final long serialVersionUID = 1L;

    public BicicletaSemBateriaException() {
        super("Bicicleta nao possui bateria.");
    }
}
