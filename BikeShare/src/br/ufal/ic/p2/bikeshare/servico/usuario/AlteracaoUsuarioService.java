package br.ufal.ic.p2.bikeshare.servico.usuario;

import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoNaoAlteravelException;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.FabricaMetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.plano.Plano;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.sistema.EstadoSistema;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.util.function.Supplier;

/** Alteracao dos dados, do plano e do metodo de pagamento de um usuario. */
public class AlteracaoUsuarioService {

    private static final String METODO_PAGAMENTO = "metodoPagamento";

    private final EstadoSistema estado;
    private final UsuarioService usuarios;

    public AlteracaoUsuarioService(EstadoSistema estado, UsuarioService usuarios) {
        this.estado = estado;
        this.usuarios = usuarios;
    }

    public void alterar(String id, String atributo, String valor) {
        Usuario usuario = usuarios.buscar(id);
        switch (atributo == null ? "" : atributo) {
            case "nome" -> usuario.alterarNome(valor);
            case "email" -> alterarEmail(usuario, valor);
            case "senha" -> usuario.alterarSenha(valor);
            case "plano" -> alterarPlano(usuario, valor);
            case "saldo" -> recarregarSaldo(usuario, valor);
            case METODO_PAGAMENTO -> usuario.alterarMetodoPagamento(
                    FabricaMetodoPagamento.saldoPreCarregado(valor));
            default -> throw new AtributoNaoAlteravelException();
        }
    }

    public void alterarParaPix(String id, String atributo, String tipo, String chave) {
        trocarMetodo(id, atributo, () -> FabricaMetodoPagamento.pix(tipo, chave));
    }

    public void alterarParaCartao(String id, String atributo, String tipo, String numero, String validade) {
        trocarMetodo(id, atributo, () -> FabricaMetodoPagamento.cartao(tipo, numero, validade));
    }

    private void trocarMetodo(String id, String atributo, Supplier<MetodoPagamento> novo) {
        Usuario usuario = usuarios.buscar(id);
        if (!METODO_PAGAMENTO.equals(atributo)) {
            throw new AtributoNaoAlteravelException();
        }
        usuario.alterarMetodoPagamento(novo.get());
    }

    private void alterarEmail(Usuario usuario, String email) {
        usuarios.exigirEmailLivre(Validador.email(email), usuario);
        usuario.alterarEmail(email);
    }

    private void alterarPlano(Usuario usuario, String nomePlano) {
        Plano novo = estado.getPlanos().buscar(nomePlano);
        if (!novo.getNome().equals(usuario.getAssinatura().getPlano().getNome())) {
            usuario.alterarAssinatura(novo.assinaturaPadrao());
        }
    }

    private static void recarregarSaldo(Usuario usuario, String valor) {
        usuario.getMetodoPagamento().exigirSaldoPreCarregado();
        usuario.recarregarSaldo(Validador.valorPositivo(valor));
    }
}
