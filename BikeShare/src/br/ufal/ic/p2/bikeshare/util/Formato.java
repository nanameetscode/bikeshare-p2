package br.ufal.ic.p2.bikeshare.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Arredondamento e formatacao de dinheiro no padrao esperado pelos testes de aceitacao. */
public final class Formato {

    private static final int CASAS_DECIMAIS = 2;

    private Formato() {
    }

    /** Arredonda para centavos; todo valor em dinheiro guardado no sistema passa por aqui. */
    public static BigDecimal centavos(BigDecimal valor) {
        return valor.setScale(CASAS_DECIMAIS, RoundingMode.HALF_UP);
    }

    /** Dinheiro com duas casas e virgula decimal, ex.: {@code 120,50}. */
    public static String dinheiro(BigDecimal valor) {
        return centavos(valor).toPlainString().replace('.', ',');
    }
}
