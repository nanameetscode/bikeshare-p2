package br.ufal.ic.p2.bikeshare.excecao.sistema;

/** Lancada quando o arquivo de configuracao existe mas nao pode ser lido. */
public class LeituraConfiguracaoException extends PersistenciaException {

    private static final long serialVersionUID = 1L;

    public LeituraConfiguracaoException(Throwable causa) {
        super("Nao foi possivel ler a configuracao do sistema.", causa);
    }
}
