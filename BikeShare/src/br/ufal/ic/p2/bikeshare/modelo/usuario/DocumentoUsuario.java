package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.excecao.BikeShareException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.TipoUsuarioIncompativelException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;

import java.util.Arrays;

/** Documentos que so existem em um tipo de usuario e o erro de quem nao e desse tipo. */
public enum DocumentoUsuario {
    CPF("cpf", "Usuario nao eh pessoa fisica."),
    CNPJ("cnpj", "Usuario nao eh pessoa juridica."),
    RAZAO_SOCIAL("razaoSocial", "Usuario nao eh pessoa juridica.");

    private final String atributo;
    private final String erro;

    DocumentoUsuario(String atributo, String erro) {
        this.atributo = atributo;
        this.erro = erro;
    }

    public static BikeShareException erroPara(String atributo) {
        return Arrays.stream(values())
                .filter(documento -> documento.atributo.equals(atributo))
                .findFirst()
                .<BikeShareException>map(documento -> new TipoUsuarioIncompativelException(documento.erro))
                .orElseGet(AtributoInexistenteException::new);
    }

    public String getAtributo() {
        return atributo;
    }
}
