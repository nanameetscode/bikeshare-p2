package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.util.Validador;

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
        this.nome = Validador.obrigatorio(nome, "Nome do plano nao pode ser nulo.");
        this.franquiaMinutos = validarFranquia(franquiaMinutos);
        this.valorMinuto = Validador.naoNegativo(valorMinuto,
                "Valor do minuto excedente deve ser nao-negativo.");
    }

    private static int validarFranquia(int franquiaMinutos) {
        if (franquiaMinutos < 0) {
            throw new ValorInvalidoException("Franquia deve ser nao-negativa.");
        }
        return franquiaMinutos;
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
