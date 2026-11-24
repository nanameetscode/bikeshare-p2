package br.ufal.ic.p2.bikeshare.excecao.sistema;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;

/** Lancada quando um parametro do arquivo de configuracao nao e um numero valido e nao negativo. */
public class ConfiguracaoInvalidaException extends BikeShareException {

    private static final long serialVersionUID = 1L;

    public ConfiguracaoInvalidaException(String chave) {
        super("Configuracao invalida: " + chave + ".");
    }
}
