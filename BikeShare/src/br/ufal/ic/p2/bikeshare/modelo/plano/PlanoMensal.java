package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.math.BigDecimal;

/** Plano com mensalidade e franquia de minutos gratis por corrida. */
public class PlanoMensal extends Plano {

    private static final long serialVersionUID = 1L;

    private final BigDecimal mensalidadePadrao;

    public PlanoMensal(String nome, int franquiaMinutos, BigDecimal valorMinutoExcedente,
                       BigDecimal mensalidadePadrao) {
        super(nome, franquiaMinutos, valorMinutoExcedente);
        this.mensalidadePadrao = Validador.naoNegativo(mensalidadePadrao, ValorRecusado.MENSALIDADE_NEGATIVA);
    }

    @Override
    public Assinatura assinar(String mensalidadeInformada) {
        BigDecimal mensalidade = Validador.dinheiro(mensalidadeInformada,
                CampoObrigatorio.MENSALIDADE, ValorRecusado.MENSALIDADE_NAO_NUMERICA);
        return new Assinatura(this, Validador.naoNegativo(mensalidade, ValorRecusado.MENSALIDADE_NEGATIVA));
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
