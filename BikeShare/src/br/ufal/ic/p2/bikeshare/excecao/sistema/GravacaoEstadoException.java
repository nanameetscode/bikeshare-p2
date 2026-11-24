package br.ufal.ic.p2.bikeshare.excecao.sistema;

/** Lancada quando o estado nao pode ser gravado em disco. */
public class GravacaoEstadoException extends PersistenciaException {

    private static final long serialVersionUID = 1L;

    public GravacaoEstadoException(Throwable causa) {
        super("Nao foi possivel gravar o estado do sistema.", causa);
    }
}
