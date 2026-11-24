package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

public enum EstadoBicicleta {
    DISPONIVEL("disponivel"),
    EM_USO("emUso"),
    EM_MANUTENCAO("emManutencao");

    private final String rotulo;

    EstadoBicicleta(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}
