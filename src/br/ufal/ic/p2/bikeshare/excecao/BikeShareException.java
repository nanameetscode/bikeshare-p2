package br.ufal.ic.p2.bikeshare.excecao;

/**
 * Raiz de todos os erros de negocio do BikeShare.
 * <p>
 * Nao e verificada para que a {@code Facade} nao precise declarar listas de {@code throws};
 * a mensagem de cada erro e exatamente a esperada pelos testes de aceitacao.
 */
public abstract class BikeShareException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected BikeShareException(String mensagem) {
        super(mensagem);
    }

    protected BikeShareException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
