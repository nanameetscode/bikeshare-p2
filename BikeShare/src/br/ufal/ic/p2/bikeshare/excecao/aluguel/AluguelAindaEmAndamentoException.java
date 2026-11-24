package br.ufal.ic.p2.bikeshare.excecao.aluguel;

/** Lancada ao consultar fim, destino ou valor de aluguel nao encerrado. */
public class AluguelAindaEmAndamentoException extends EstadoAluguelInvalidoException {

    private static final long serialVersionUID = 1L;

    public AluguelAindaEmAndamentoException() {
        super("Aluguel ainda esta em andamento.");
    }
}
