package br.ufal.ic.p2.bikeshare.excecao.usuario;

/** Lancada ao pedir o CPF de uma pessoa juridica. */
public class UsuarioNaoEhPessoaFisicaException extends TipoUsuarioIncompativelException {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoEhPessoaFisicaException() {
        super("Usuario nao eh pessoa fisica.");
    }
}
