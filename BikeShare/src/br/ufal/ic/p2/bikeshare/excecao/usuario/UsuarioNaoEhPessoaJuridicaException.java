package br.ufal.ic.p2.bikeshare.excecao.usuario;

/** Lancada ao pedir CNPJ ou razao social de uma pessoa fisica. */
public class UsuarioNaoEhPessoaJuridicaException extends TipoUsuarioIncompativelException {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoEhPessoaJuridicaException() {
        super("Usuario nao eh pessoa juridica.");
    }
}
