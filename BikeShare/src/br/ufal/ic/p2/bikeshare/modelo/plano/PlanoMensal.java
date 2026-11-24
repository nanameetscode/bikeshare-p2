package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.util.Validador;

import java.math.BigDecimal;

/** Plano com mensalidade e franquia de minutos gratis por corrida. */
public class PlanoMensal extends Plano {

    private static final long serialVersionUID = 1L;
    private static final String MENSALIDADE_NEGATIVA = "Mensalidade deve ser nao-negativa.";

    private final BigDecimal mensalidadePadrao;

    public PlanoMensal(String nome, int franquiaMinutos, BigDecimal valorMinutoExcedente,
                       BigDecimal mensalidadePadrao) {
        super(nome, franquiaMinutos, valorMinutoExcedente);
        this.mensalidadePadrao = Validador.naoNegativo(mensalidadePadrao, MENSALIDADE_NEGATIVA);
    }

    @Override
    public Assinatura assinar(String mensalidadeInformada) {
        BigDecimal mensalidade = Validador.dinheiro(mensalidadeInformada,
                "Mensalidade nao pode ser nula.", "Mensalidade deve ser numerica.");
        return new Assinatura(this, Validador.naoNegativo(mensalidade, MENSALIDADE_NEGATIVA));
    }

    @Override
    public Assinatura assinaturaPadrao() {
        return new Assinatura(this, mensalidadePadrao);
    }

    @Override
    protected BigDecimal exigirMensalidade(BigDecimal mensalidadeContratada) {
        return mensalidadeContratada;
    }
}
