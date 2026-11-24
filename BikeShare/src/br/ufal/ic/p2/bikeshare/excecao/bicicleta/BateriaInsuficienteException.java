package br.ufal.ic.p2.bikeshare.excecao.bicicleta;

/** Lancada quando a eletrica esta abaixo da carga minima para retirada. */
public class BateriaInsuficienteException extends OperacaoBicicletaInvalidaException {

    private static final long serialVersionUID = 1L;

    public BateriaInsuficienteException() {
        super("Nivel de bateria insuficiente.");
    }
}
