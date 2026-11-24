package br.ufal.ic.p2.bikeshare.sistema;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Parametros de tarifacao e limites de negocio (RNF-4). Ficam reunidos aqui, com os
 * valores padrao do enunciado, em vez de espalhados pelo codigo como numeros soltos.
 */
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 1L;

    private final BigDecimal tarifaMinutoAvulso = new BigDecimal("0.50");
    private final int franquiaMinutosAssinante = 60;
    private final BigDecimal tarifaExcedenteAssinante = new BigDecimal("0.30");
    private final BigDecimal mensalidadePadraoAssinante = new BigDecimal("30");

    public BigDecimal getTarifaMinutoAvulso() {
        return tarifaMinutoAvulso;
    }

    public int getFranquiaMinutosAssinante() {
        return franquiaMinutosAssinante;
    }

    public BigDecimal getTarifaExcedenteAssinante() {
        return tarifaExcedenteAssinante;
    }

    public BigDecimal getMensalidadePadraoAssinante() {
        return mensalidadePadraoAssinante;
    }
}
