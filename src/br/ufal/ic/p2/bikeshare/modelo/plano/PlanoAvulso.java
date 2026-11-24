package br.ufal.ic.p2.bikeshare.modelo.plano;

import br.ufal.ic.p2.bikeshare.excecao.plano.PlanoSemMensalidadeException;

import java.math.BigDecimal;

/** Plano sem mensalidade e sem franquia: paga cada minuto da corrida. */
public class PlanoAvulso extends Plano {

    private static final long serialVersionUID = 1L;

    public PlanoAvulso(String nome, BigDecimal valorMinuto) {
        super(nome, 0, valorMinuto);
    }

    @Override
    public Assinatura assinar(String mensalidadeInformada) {
        if (mensalidadeInformada != null && !mensalidadeInformada.isBlank()) {
            throw new PlanoSemMensalidadeException();
        }
        return assinaturaPadrao();
    }

    @Override
    public Assinatura assinaturaPadrao() {
        return new Assinatura(this, BigDecimal.ZERO);
    }

    @Override
    protected BigDecimal exigirMensalidade(BigDecimal mensalidadeContratada) {
        throw new PlanoSemMensalidadeException();
    }
}
