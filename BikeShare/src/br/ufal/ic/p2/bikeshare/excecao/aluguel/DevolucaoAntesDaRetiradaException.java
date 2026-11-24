package br.ufal.ic.p2.bikeshare.excecao.aluguel;

/** Lancada quando a devolucao e anterior a retirada. */
public class DevolucaoAntesDaRetiradaException extends EstadoAluguelInvalidoException {

    private static final long serialVersionUID = 1L;

    public DevolucaoAntesDaRetiradaException() {
        super("Data de devolucao anterior a data de retirada.");
    }
}
