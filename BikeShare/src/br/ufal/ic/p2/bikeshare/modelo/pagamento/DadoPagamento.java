package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;
import br.ufal.ic.p2.bikeshare.excecao.pagamento.MetodoPagamentoIncompativelException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;

import java.util.Arrays;

/** Dados que so existem em um metodo de pagamento e o erro de quem nao usa esse metodo. */
public enum DadoPagamento {
    NUMERO_CARTAO("numeroCartao", "Usuario nao usa cartao."),
    VALIDADE_CARTAO("validadeCartao", "Usuario nao usa cartao."),
    CHAVE_PIX("chavePix", "Usuario nao usa pix.");

    private final String atributo;
    private final String erro;

    DadoPagamento(String atributo, String erro) {
        this.atributo = atributo;
        this.erro = erro;
    }

    public static BikeShareException erroPara(String atributo) {
        return Arrays.stream(values())
                .filter(dado -> dado.atributo.equals(atributo))
                .findFirst()
                .<BikeShareException>map(dado -> new MetodoPagamentoIncompativelException(dado.erro))
                .orElseGet(AtributoInexistenteException::new);
    }

    public String getAtributo() {
        return atributo;
    }
}
