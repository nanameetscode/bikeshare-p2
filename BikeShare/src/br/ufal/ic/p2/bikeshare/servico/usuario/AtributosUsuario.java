package br.ufal.ic.p2.bikeshare.servico.usuario;

import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.util.Formato;

/**
 * Traduz o nome de atributo usado pelos testes para o dado do usuario, ja formatado.
 * Os dados que so existem em um subtipo (cpf, cnpj, numero do cartao...) sao
 * resolvidos por polimorfismo no proprio usuario ou no metodo de pagamento.
 */
final class AtributosUsuario {

    private AtributosUsuario() {
    }

    static String ler(Usuario usuario, String atributo) {
        String nome = atributo == null ? "" : atributo;
        return switch (nome) {
            case "nome" -> usuario.getNome();
            case "email" -> usuario.getEmail();
            case "senha" -> usuario.getSenha();
            case "tipo" -> usuario.getTipo();
            case "estado" -> usuario.getEstado().getRotulo();
            case "cpf", "cnpj", "razaoSocial" -> usuario.getDocumento(nome);
            case "aluguelAtivo" -> usuario.getAluguelAtivo().orElse("nenhum");
            default -> lerDadoFinanceiro(usuario, nome);
        };
    }

    private static String lerDadoFinanceiro(Usuario usuario, String atributo) {
        return switch (atributo) {
            case "plano" -> usuario.getAssinatura().getPlano().getNome();
            case "mensalidade" -> Formato.dinheiro(usuario.getAssinatura().getMensalidade());
            case "totalPendencias" -> Formato.dinheiro(usuario.getTotalPendencias());
            case "metodoPagamento" -> usuario.getMetodoPagamento().getTipo();
            case "numeroCartao", "validadeCartao", "chavePix" -> usuario.getMetodoPagamento().getDado(atributo);
            case "saldo" -> Formato.dinheiro(usuario.getMetodoPagamento().getSaldo());
            default -> throw new AtributoInexistenteException();
        };
    }
}
