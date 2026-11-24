package br.ufal.ic.p2.bikeshare.modelo.pagamento;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;
import br.ufal.ic.p2.bikeshare.excecao.pagamento.UsuarioNaoUsaCartaoException;
import br.ufal.ic.p2.bikeshare.excecao.pagamento.UsuarioNaoUsaPixException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;

import java.util.Arrays;
import java.util.function.Supplier;

/** Dados que so existem em um metodo de pagamento e o erro de quem nao usa esse metodo. */
public enum DadoPagamento {
    NUMERO_CARTAO("numeroCartao", UsuarioNaoUsaCartaoException::new),
    VALIDADE_CARTAO("validadeCartao", UsuarioNaoUsaCartaoException::new),
    CHAVE_PIX("chavePix", UsuarioNaoUsaPixException::new);

    private final String atributo;
    private final Supplier<BikeShareException> erro;

    DadoPagamento(String atributo, Supplier<BikeShareException> erro) {
        this.atributo = atributo;
        this.erro = erro;
    }

    public static BikeShareException erroPara(String atributo) {
        return Arrays.stream(values())
                .filter(dado -> dado.atributo.equals(atributo))
                .findFirst()
                .map(dado -> dado.erro.get())
                .orElseGet(AtributoInexistenteException::new);
    }

    public String getAtributo() {
        return atributo;
    }
}
