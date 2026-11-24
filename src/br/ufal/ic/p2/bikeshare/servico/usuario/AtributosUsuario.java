package br.ufal.ic.p2.bikeshare.servico.usuario;

import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.util.Formato;

/**
 * Traduz o nome de atributo usado pelos testes para o dado do usuario, ja formatado.
 * Os dados que so existem em um subtipo (cpf, cnpj...) sao resolvidos por polimorfismo.
 */
final class AtributosUsuario {

    private AtributosUsuario() {
    }

    static String ler(Usuario usuario, String atributo) {
        return switch (atributo == null ? "" : atributo) {
            case "nome" -> usuario.getNome();
            case "email" -> usuario.getEmail();
            case "tipo" -> usuario.getTipo();
            case "estado" -> usuario.getEstado().getRotulo();
            case "cpf" -> usuario.getCpf();
            case "cnpj" -> usuario.getCnpj();
            case "razaoSocial" -> usuario.getRazaoSocial();
            case "plano" -> usuario.getAssinatura().getPlano().getNome();
            case "mensalidade" -> Formato.dinheiro(usuario.getAssinatura().getMensalidade());
            case "metodoPagamento" -> usuario.getMetodoPagamento().getTipo();
            default -> throw new AtributoInexistenteException();
        };
    }
}
