package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.util.Validador;

/** Cria o metodo de pagamento, conferindo se o nome informado combina com os dados recebidos. */
public final class FabricaMetodoPagamento {

    private FabricaMetodoPagamento() {
    }

    public static MetodoPagamento saldoPreCarregado(String tipo) {
        exigirTipo(tipo, SaldoPreCarregado.TIPO);
        return new SaldoPreCarregado();
    }

    public static MetodoPagamento pix(String tipo, String chave) {
        exigirTipo(tipo, Pix.TIPO);
        return new Pix(chave);
    }

    public static MetodoPagamento cartao(String tipo, String numero, String validade) {
        exigirTipo(tipo, Cartao.TIPO);
        return new Cartao(numero, validade);
    }

    private static void exigirTipo(String informado, String esperado) {
        Validador.exigirIgual(informado, esperado, "Valor de metodo de pagamento invalido.");
    }
}
