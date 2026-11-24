package br.ufal.ic.p2.bikeshare.excecao.aluguel;

/** Lancada ao devolver ou encerrar aluguel ja encerrado. */
public class AluguelNaoEmAndamentoException extends EstadoAluguelInvalidoException {

    private static final long serialVersionUID = 1L;

    public AluguelNaoEmAndamentoException() {
        super("Aluguel nao esta em andamento.");
    }
}
