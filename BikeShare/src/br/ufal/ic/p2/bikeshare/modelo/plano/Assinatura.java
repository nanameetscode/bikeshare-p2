package br.ufal.ic.p2.bikeshare.modelo.plano;

import java.io.Serializable;
import java.math.BigDecimal;

/** Plano contratado por um usuario, com a mensalidade combinada no momento da contratacao. */
public class Assinatura implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Plano plano;
    private final BigDecimal mensalidade;

    Assinatura(Plano plano, BigDecimal mensalidade) {
        this.plano = plano;
        this.mensalidade = mensalidade;
    }

    public Plano getPlano() {
        return plano;
    }

    public BigDecimal getMensalidade() {
        return plano.exigirMensalidade(mensalidade);
    }
}
