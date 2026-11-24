package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import java.io.Serializable;

/** Forma como o usuario paga as corridas. Cada subclasse expoe so os dados que possui. */
public abstract class MetodoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Nome do metodo no formato dos testes (ex.: {@code pix}). */
    public abstract String getTipo();
}
