package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.UsuarioNaoEhPessoaFisicaException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.UsuarioNaoEhPessoaJuridicaException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;

import java.util.Arrays;
import java.util.function.Supplier;

/** Documentos que so existem em um tipo de usuario e o erro de quem nao e desse tipo. */
public enum DocumentoUsuario {
    CPF("cpf", UsuarioNaoEhPessoaFisicaException::new),
    CNPJ("cnpj", UsuarioNaoEhPessoaJuridicaException::new),
    RAZAO_SOCIAL("razaoSocial", UsuarioNaoEhPessoaJuridicaException::new);

    private final String atributo;
    private final Supplier<BikeShareException> erro;

    DocumentoUsuario(String atributo, Supplier<BikeShareException> erro) {
        this.atributo = atributo;
        this.erro = erro;
    }

    public static BikeShareException erroPara(String atributo) {
        return Arrays.stream(values())
                .filter(documento -> documento.atributo.equals(atributo))
                .findFirst()
                .map(documento -> documento.erro.get())
                .orElseGet(AtributoInexistenteException::new);
    }

    public String getAtributo() {
        return atributo;
    }
}
