package br.ufal.ic.p2.bikeshare.servico.usuario;

import br.ufal.ic.p2.bikeshare.excecao.usuario.EmailJaCadastradoException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.RemocaoComAluguelException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.UsuarioNaoEncontradoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorRecusado;
import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;
import br.ufal.ic.p2.bikeshare.modelo.plano.CatalogoPlanos;
import br.ufal.ic.p2.bikeshare.modelo.usuario.DadosUsuario;
import br.ufal.ic.p2.bikeshare.modelo.usuario.PessoaFisica;
import br.ufal.ic.p2.bikeshare.modelo.usuario.PessoaJuridica;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.sistema.Cadastro;
import br.ufal.ic.p2.bikeshare.sistema.EstadoSistema;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.util.Optional;

/**
 * Cadastro e consulta de usuarios. Os dados sao validados pelo proprio usuario;
 * aqui so se confere o que depende dos demais cadastros, como o email repetido.
 */
public class UsuarioService {

    private final EstadoSistema estado;

    public UsuarioService(EstadoSistema estado) {
        this.estado = estado;
    }

    /**
     * Cadastra uma pessoa fisica. Com {@code plano} nulo o usuario recebe o plano padrao.
     *
     * @return o identificador gerado ({@code U1}, {@code U2}...)
     */
    public String criarPessoaFisica(String nome, String email, String senha, String tipo, String cpf,
                                    String plano, String mensalidade) {
        Validador.exigirIgual(tipo, PessoaFisica.TIPO, ValorRecusado.TIPO);
        Assinatura assinatura = contratar(plano, mensalidade);
        return registrar(new PessoaFisica(usuarios().gerarId(),
                new DadosUsuario(nome, email, senha), assinatura, cpf));
    }

    /** Cadastra uma pessoa juridica; mesmas regras de {@link #criarPessoaFisica}. */
    public String criarPessoaJuridica(String nome, String email, String senha, String tipo, String cnpj,
                                      String razaoSocial, String plano, String mensalidade) {
        Validador.exigirIgual(tipo, PessoaJuridica.TIPO, ValorRecusado.TIPO);
        Assinatura assinatura = contratar(plano, mensalidade);
        return registrar(new PessoaJuridica(usuarios().gerarId(),
                new DadosUsuario(nome, email, senha), assinatura, cnpj, razaoSocial));
    }

    public Usuario buscar(String id) {
        return usuarios().exigir(id, CampoObrigatorio.ID_USUARIO,
                UsuarioNaoEncontradoException::new);
    }

    public void remover(String id) {
        buscar(id).exigirSemAluguel(RemocaoComAluguelException::new);
        usuarios().remover(id);
    }

    public String getAtributo(String id, String atributo) {
        return AtributosUsuario.ler(buscar(id), atributo);
    }

    /** Busca o identificador do usuario pelo email, sem diferenciar maiusculas de minusculas. */
    public String buscarIdPorEmail(String email) {
        Validador.obrigatorio(email, CampoObrigatorio.EMAIL);
        return buscarPorEmail(email).map(Usuario::getId).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    /** Recusa o email do usuario se outro usuario ja o usa. */
    void exigirEmailUnico(Usuario usuario) {
        if (usuarios().valores().stream().anyMatch(outro -> outro != usuario && outro.temEmail(usuario.getEmail()))) {
            throw new EmailJaCadastradoException();
        }
    }

    private Optional<Usuario> buscarPorEmail(String email) {
        return usuarios().valores().stream().filter(u -> u.temEmail(email)).findFirst();
    }

    private Assinatura contratar(String plano, String mensalidade) {
        CatalogoPlanos planos = estado.getPlanos();
        if (plano == null) {
            return planos.getPlanoPadrao().assinaturaPadrao();
        }
        return planos.buscar(plano).assinar(mensalidade);
    }

    private String registrar(Usuario usuario) {
        exigirEmailUnico(usuario);
        usuarios().adicionar(usuario.getId(), usuario);
        return usuario.getId();
    }

    private Cadastro<Usuario> usuarios() {
        return estado.getUsuarios();
    }
}
