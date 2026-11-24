package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.bicicleta.OperacaoBicicletaInvalidaException;

/** Situacao da bicicleta. So a bicicleta disponivel pode ser retirada ou removida. */
public enum EstadoBicicleta {

    DISPONIVEL("disponivel") {
        @Override
        public boolean estaDisponivel() {
            return true;
        }
    },
    EM_USO("emUso"),
    EM_MANUTENCAO("emManutencao");

    private final String rotulo;

    EstadoBicicleta(String rotulo) {
        this.rotulo = rotulo;
    }

    public boolean estaDisponivel() {
        return false;
    }

    public void exigirDisponivel() {
        if (!estaDisponivel()) {
            throw new OperacaoBicicletaInvalidaException("Bicicleta nao esta disponivel.");
        }
    }

    public String getRotulo() {
        return rotulo;
    }
}
