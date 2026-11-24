package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;
import br.ufal.ic.p2.bikeshare.util.Validador;

/** Cria o metodo de pagamento, conferindo se o nome informado combina com os dados recebidos. */
public final class FabricaMetodoPagamento {

    private FabricaMetodoPagamento() {
    }

    /** Devolve a carteira do proprio usuario, que guarda o saldo ja recarregado. */
    public static MetodoPagamento saldoPreCarregado(String tipo, SaldoPreCarregado carteira) {
        exigirTipo(tipo, SaldoPreCarregado.TIPO);
        return carteira;
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
        Validador.exigirIgual(informado, esperado, ValorRecusado.METODO_PAGAMENTO);
    }
}
