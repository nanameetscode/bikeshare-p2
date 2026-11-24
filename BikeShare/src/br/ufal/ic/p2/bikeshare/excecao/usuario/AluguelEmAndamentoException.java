package br.ufal.ic.p2.bikeshare.excecao.usuario;

/** Lancada quando o usuario tenta um segundo aluguel simultaneo. */
public class AluguelEmAndamentoException extends UsuarioComAluguelException {

    private static final long serialVersionUID = 1L;

    public AluguelEmAndamentoException() {
        super("Usuario ja possui um aluguel em andamento.");
    }
}
