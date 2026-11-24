package br.ufal.ic.p2.bikeshare.excecao.sistema;

/** Lancada quando o arquivo de estado nao pode ser lido ou nao e do tipo esperado. */
public class LeituraEstadoException extends PersistenciaException {

    private static final long serialVersionUID = 1L;

    public LeituraEstadoException(Throwable causa) {
        super("Nao foi possivel ler o estado do sistema.", causa);
    }
}
