package br.ufal.ic.p2.bikeshare.modelo.plano;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Modalidade de tarifacao. Toda corrida custa {@code (minutos - franquia) * valorMinuto},
 * nunca negativo; as subclasses decidem se o plano cobra mensalidade.
 */
public abstract class Plano implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nome;
    private final int franquiaMinutos;
    private final BigDecimal valorMinuto;

    protected Plano(String nome, int franquiaMinutos, BigDecimal valorMinuto) {
        this.nome = nome;
        this.franquiaMinutos = franquiaMinutos;
        this.valorMinuto = valorMinuto;
    }

    /** Contrata o plano com a mensalidade informada pelo usuario (pode ser vazia). */
    public abstract Assinatura assinar(String mensalidadeInformada);

    /** Contrata o plano com a mensalidade padrao, usada quando o usuario troca de plano. */
    public abstract Assinatura assinaturaPadrao();

    /** Devolve a mensalidade contratada ou recusa, se o plano nao tiver mensalidade. */
    protected abstract BigDecimal exigirMensalidade(BigDecimal mensalidadeContratada);

    public BigDecimal calcularTarifa(long minutos) {
        long excedente = Math.max(0, minutos - franquiaMinutos);
        return valorMinuto.multiply(BigDecimal.valueOf(excedente));
    }

    public String getNome() {
        return nome;
    }
}
