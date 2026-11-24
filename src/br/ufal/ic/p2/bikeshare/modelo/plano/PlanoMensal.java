package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.math.BigDecimal;

/** Plano com mensalidade e franquia de minutos gratis por corrida. */
public class PlanoMensal extends Plano {

    private static final long serialVersionUID = 1L;

    private final BigDecimal mensalidadePadrao;

    public PlanoMensal(String nome, int franquiaMinutos, BigDecimal valorMinutoExcedente,
                       BigDecimal mensalidadePadrao) {
        super(nome, franquiaMinutos, valorMinutoExcedente);
        this.mensalidadePadrao = mensalidadePadrao;
    }

    @Override
    public Assinatura assinar(String mensalidadeInformada) {
        BigDecimal mensalidade = Validador.decimal(mensalidadeInformada,
                "Mensalidade nao pode ser nula.", "Mensalidade deve ser numerica.");
        if (mensalidade.signum() < 0) {
            throw new ValorInvalidoException("Mensalidade deve ser nao-negativa.");
        }
        return new Assinatura(this, mensalidade);
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
