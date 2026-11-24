package br.ufal.ic.p2.bikeshare.servico.usuario;

import br.ufal.ic.p2.bikeshare.excecao.usuario.EmailJaCadastradoException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.UsuarioNaoEncontradoException;
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

/** Cadastro e consulta de usuarios. Todos os dados sao validados antes de qualquer alteracao. */
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
    public String criarPessoaFisica(DadosUsuario dados, String tipo, String cpf,
                                    String plano, String mensalidade) {
        validarDados(dados);
        exigirTipo(tipo, PessoaFisica.TIPO);
        String documento = Validador.obrigatorio(cpf, "CPF nao pode ser nulo.");
        Assinatura assinatura = contratar(plano, mensalidade);
        return registrar(new PessoaFisica(usuarios().gerarId(), dados, assinatura, documento));
    }

    /** Cadastra uma pessoa juridica; mesmas regras de {@link #criarPessoaFisica}. */
    public String criarPessoaJuridica(DadosUsuario dados, String tipo, String cnpj, String razaoSocial,
                                      String plano, String mensalidade) {
        validarDados(dados);
        exigirTipo(tipo, PessoaJuridica.TIPO);
        String documento = Validador.obrigatorio(cnpj, "CNPJ nao pode ser nulo.");
        String razao = Validador.obrigatorio(razaoSocial, "Razao social nao pode ser nula.");
        Assinatura assinatura = contratar(plano, mensalidade);
        return registrar(new PessoaJuridica(usuarios().gerarId(), dados, assinatura, documento, razao));
    }

    public Usuario buscar(String id) {
        Validador.obrigatorio(id, "Identificacao do usuario nao pode ser nula.");
        return usuarios().buscar(id).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    public String getAtributo(String id, String atributo) {
        return AtributosUsuario.ler(buscar(id), atributo);
    }

    /** Busca o identificador do usuario pelo email, sem diferenciar maiusculas de minusculas. */
    public String buscarIdPorEmail(String email) {
        Validador.obrigatorio(email, "Email nao pode ser nulo.");
        return buscarPorEmail(email).map(Usuario::getId).orElseThrow(UsuarioNaoEncontradoException::new);
    }

    private Optional<Usuario> buscarPorEmail(String email) {
        return usuarios().valores().stream().filter(u -> u.temEmail(email)).findFirst();
    }

    void exigirEmailLivre(String email, Usuario dono) {
        if (buscarPorEmail(email).filter(u -> u != dono).isPresent()) {
            throw new EmailJaCadastradoException();
        }
    }

    private void validarDados(DadosUsuario dados) {
        Usuario.validarNome(dados.nome());
        exigirEmailLivre(Validador.email(dados.email()), null);
        Usuario.validarSenha(dados.senha());
    }

    private static void exigirTipo(String informado, String esperado) {
        Validador.exigirIgual(informado, esperado, "Tipo invalido.");
    }

    private Assinatura contratar(String plano, String mensalidade) {
        CatalogoPlanos planos = estado.getPlanos();
        if (plano == null) {
            return planos.getPlanoPadrao().assinaturaPadrao();
        }
        return planos.buscar(plano).assinar(mensalidade);
    }

    private String registrar(Usuario usuario) {
        usuarios().adicionar(usuario.getId(), usuario);
        return usuario.getId();
    }

    private Cadastro<Usuario> usuarios() {
        return estado.getUsuarios();
    }
}
