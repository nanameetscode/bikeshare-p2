package br.ufal.ic.p2.bikeshare.excecao.pagamento;

/** Lancada ao consultar ou recarregar saldo de quem nao usa saldo. */
public class UsuarioNaoUsaSaldoException extends MetodoPagamentoIncompativelException {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoUsaSaldoException() {
        super("Usuario nao usa saldo pre-carregado.");
    }
}
