package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.util.Validador;

/** Pagamento por chave pix. */
public class Pix extends MetodoPagamento {

    public static final String TIPO = "pix";
    private static final long serialVersionUID = 1L;

    private final String chave;

    public Pix(String chave) {
        this.chave = Validador.obrigatorio(chave, "Chave pix nao pode ser nula.");
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public String getChavePix() {
        return chave;
    }
}
