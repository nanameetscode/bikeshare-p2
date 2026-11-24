package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.excecao.usuario.TipoUsuarioIncompativelException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.UsuarioComAluguelException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.UsuarioInadimplenteException;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.SemMetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Ciclista cadastrado. Guarda o que e comum a pessoa fisica e juridica;
 * o documento fica em cada subclasse.
 */
public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String NAO_EH_FISICA = "Usuario nao eh pessoa fisica.";
    private static final String NAO_EH_JURIDICA = "Usuario nao eh pessoa juridica.";

    private final String id;
    private String nome;
    private String email;
    private String senha;
    private Assinatura assinatura;
    private MetodoPagamento metodoPagamento = new SemMetodoPagamento();
    private EstadoUsuario estado = EstadoUsuario.ATIVO;
    private BigDecimal saldo = BigDecimal.ZERO;
    private BigDecimal totalPendencias = BigDecimal.ZERO;
    private String aluguelAtivo;

    protected Usuario(String id, DadosUsuario dados, Assinatura assinatura) {
        this.id = id;
        this.nome = validarNome(dados.nome());
        this.email = Validador.email(dados.email());
        this.senha = validarSenha(dados.senha());
        this.assinatura = assinatura;
    }

    public static String validarNome(String nome) {
        return Validador.obrigatorio(nome, "Nome nao pode ser nulo.");
    }

    public static String validarSenha(String senha) {
        return Validador.obrigatorio(senha, "Senha nao pode ser nula.");
    }

    public abstract String getTipo();

    public String getCpf() {
        throw new TipoUsuarioIncompativelException(NAO_EH_FISICA);
    }

    public String getCnpj() {
        throw new TipoUsuarioIncompativelException(NAO_EH_JURIDICA);
    }

    public String getRazaoSocial() {
        throw new TipoUsuarioIncompativelException(NAO_EH_JURIDICA);
    }

    public boolean temEmail(String outroEmail) {
        return email.equalsIgnoreCase(outroEmail);
    }

    public void alterarNome(String novoNome) {
        nome = validarNome(novoNome);
    }

    public void alterarEmail(String novoEmail) {
        email = Validador.email(novoEmail);
    }

    public void alterarSenha(String novaSenha) {
        senha = validarSenha(novaSenha);
    }

    public void alterarAssinatura(Assinatura novaAssinatura) {
        assinatura = novaAssinatura;
    }

    public void alterarMetodoPagamento(MetodoPagamento novoMetodo) {
        metodoPagamento = novoMetodo;
    }

    public void recarregarSaldo(BigDecimal valor) {
        metodoPagamento.exigirSaldoPreCarregado();
        saldo = saldo.add(valor);
    }

    public void exigirLiberadoParaRetirada() {
        if (estado == EstadoUsuario.INADIMPLENTE) {
            throw new UsuarioInadimplenteException();
        }
        if (aluguelAtivo != null) {
            throw new UsuarioComAluguelException("Usuario ja possui um aluguel em andamento.");
        }
    }

    public void autorizarRetirada() {
        metodoPagamento.autorizarRetirada(saldo);
    }

    public void iniciarAluguel(String idAluguel) {
        aluguelAtivo = idAluguel;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public BigDecimal getSaldo() {
        metodoPagamento.exigirSaldoPreCarregado();
        return saldo;
    }

    public BigDecimal getTotalPendencias() {
        return totalPendencias;
    }

    public Optional<String> getAluguelAtivo() {
        return Optional.ofNullable(aluguelAtivo);
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }
}
