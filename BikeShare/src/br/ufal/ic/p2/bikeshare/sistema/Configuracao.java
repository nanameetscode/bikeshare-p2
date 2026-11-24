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
    private final int nivelMinimoBateria = 15;
    private final BigDecimal adicionalMinutoEletrica = new BigDecimal("0.20");
    private final int minutosCarencia = 2;
    private final int minutosMaximoAluguel = 24 * 60;
    private final BigDecimal valorPenalidadeAtraso = new BigDecimal("10");

    public BigDecimal getAdicionalMinutoEletrica() {
        return adicionalMinutoEletrica;
    }

    public int getMinutosCarencia() {
        return minutosCarencia;
    }

    public int getMinutosMaximoAluguel() {
        return minutosMaximoAluguel;
    }

    public BigDecimal getValorPenalidadeAtraso() {
        return valorPenalidadeAtraso;
    }

    public int getNivelMinimoBateria() {
        return nivelMinimoBateria;
    }

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
