package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.excecao.usuario.UsuarioInadimplenteException;

/** Situacao financeira do usuario, que libera ou bloqueia novas retiradas. */
public enum EstadoUsuario {

    ATIVO("ativo"),

    INADIMPLENTE("inadimplente") {
        @Override
        public void exigirAdimplente() {
            throw new UsuarioInadimplenteException();
        }
    };

    private final String rotulo;

    EstadoUsuario(String rotulo) {
        this.rotulo = rotulo;
    }

    public void exigirAdimplente() {
    }

    public String getRotulo() {
        return rotulo;
    }
}
