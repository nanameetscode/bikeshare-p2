package br.ufal.ic.p2.bikeshare.modelo.aluguel;

import br.ufal.ic.p2.bikeshare.excecao.aluguel.EstadoAluguelInvalidoException;

/** Situacao de um aluguel. So o aluguel em andamento pode ser devolvido. */
public enum EstadoAluguel {

    EM_ANDAMENTO("emAndamento") {
        @Override
        public void exigirEmAndamento() {
        }

        @Override
        public void exigirEncerrado() {
            throw new EstadoAluguelInvalidoException("Aluguel ainda esta em andamento.");
        }
    },
    FINALIZADO("finalizado"),
    CANCELADO("cancelado"),
    EXPIRADO("expirado");

    private final String rotulo;

    EstadoAluguel(String rotulo) {
        this.rotulo = rotulo;
    }

    public void exigirEmAndamento() {
        throw new EstadoAluguelInvalidoException("Aluguel nao esta em andamento.");
    }

    public void exigirEncerrado() {
    }

    public String getRotulo() {
        return rotulo;
    }
}
