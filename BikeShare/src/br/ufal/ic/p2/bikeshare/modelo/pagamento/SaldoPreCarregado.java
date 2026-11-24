package br.ufal.ic.p2.bikeshare.modelo.pagamento;

/** Pagamento com saldo recarregado. O saldo fica no usuario, para nao se perder na troca de metodo. */
public class SaldoPreCarregado extends MetodoPagamento {

    public static final String TIPO = "saldoPreCarregado";
    private static final long serialVersionUID = 1L;

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public void exigirSaldoPreCarregado() {
    }
}
