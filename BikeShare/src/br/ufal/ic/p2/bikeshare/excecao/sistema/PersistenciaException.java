package br.ufal.ic.p2.bikeshare.excecao.sistema;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Falha ao ler ou gravar arquivos do sistema; guarda a causa original. */
public abstract class PersistenciaException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    protected PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
