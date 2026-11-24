package br.ufal.ic.p2.bikeshare.modelo.usuario;

/** Situacao financeira do usuario, que libera ou bloqueia novas retiradas. */
public enum EstadoUsuario {
    ATIVO("ativo"),
    INADIMPLENTE("inadimplente");

    private final String rotulo;

    EstadoUsuario(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}
