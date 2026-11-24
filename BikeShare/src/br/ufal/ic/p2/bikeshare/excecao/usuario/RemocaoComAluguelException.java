package br.ufal.ic.p2.bikeshare.excecao.usuario;

/** Lancada ao remover usuario que esta com bicicleta. */
public class RemocaoComAluguelException extends UsuarioComAluguelException {

    private static final long serialVersionUID = 1L;

    public RemocaoComAluguelException() {
        super("Nao e possivel remover usuario com aluguel em andamento.");
    }
}
