package br.ufal.ic.p2.bikeshare.modelo.pagamento;

/** Situacao inicial do usuario: nenhum metodo de pagamento cadastrado. */
public class SemMetodoPagamento extends MetodoPagamento {

    private static final long serialVersionUID = 1L;

    @Override
    public String getTipo() {
        return "nenhum";
    }
}
