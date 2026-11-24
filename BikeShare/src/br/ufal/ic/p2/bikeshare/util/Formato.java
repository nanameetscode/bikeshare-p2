package br.ufal.ic.p2.bikeshare.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Formatacao de valores no padrao esperado pelos testes de aceitacao. */
public final class Formato {

    private Formato() {
    }

    /** Dinheiro com duas casas e virgula decimal, ex.: {@code 120,50}. */
    public static String dinheiro(BigDecimal valor) {
        return valor.setScale(2, RoundingMode.HALF_UP).toPlainString().replace('.', ',');
    }
}
