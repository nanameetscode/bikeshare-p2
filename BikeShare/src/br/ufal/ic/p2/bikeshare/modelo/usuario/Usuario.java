package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.excecao.aluguel.EstadoAluguelInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.plano.PlanoInvalidoException;
import br.ufal.ic.p2.bikeshare.excecao.usuario.UsuarioComAluguelException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.FabricaMetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.SaldoPreCarregado;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.SemMetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Ciclista cadastrado. Guarda o que e comum a pessoa fisica e juridica e recusa
 * qualquer dado invalido; o documento de identificacao fica em cada subclasse.
 */
public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String id;
    private final SaldoPreCarregado carteira = new SaldoPreCarregado();
    private String nome;
    private String email;
    private String senha;
    private Assinatura assinatura;
    private MetodoPagamento metodoPagamento = new SemMetodoPagamento();
    private EstadoUsuario estado = EstadoUsuario.ATIVO;
    private BigDecimal totalPendencias = BigDecimal.ZERO;
    private String aluguelAtivo;

    protected Usuario(String id, DadosUsuario dados, Assinatura assinatura) {
        this.id = Validador.obrigatorio(id, "Identificacao do usuario nao pode ser nula.");
        this.nome = Validador.obrigatorio(dados.nome(), "Nome nao pode ser nulo.");
        this.email = Validador.email(dados.email());
        this.senha = Validador.obrigatorio(dados.senha(), "Senha nao pode ser nula.");
        this.assinatura = exigirAssinatura(assinatura);
    }

    private static Assinatura exigirAssinatura(Assinatura assinatura) {
        if (assinatura == null) {
            throw new PlanoInvalidoException();
        }
        return assinatura;
    }

    public abstract String getTipo();

    /** Documento proprio do tipo de usuario (cpf, cnpj ou razao social). */
    public String getDocumento(String atributo) {
        throw DocumentoUsuario.erroPara(atributo);
    }

    public boolean temEmail(String outroEmail) {
        return email.equalsIgnoreCase(outroEmail);
    }

    public void alterarNome(String novoNome) {
        nome = Validador.obrigatorio(novoNome, "Nome nao pode ser nulo.");
    }

    public void alterarEmail(String novoEmail) {
        email = Validador.email(novoEmail);
    }

    public void alterarSenha(String novaSenha) {
        senha = Validador.obrigatorio(novaSenha, "Senha nao pode ser nula.");
    }

    public void alterarAssinatura(Assinatura novaAssinatura) {
        assinatura = exigirAssinatura(novaAssinatura);
    }

    public void alterarMetodoPagamento(MetodoPagamento novoMetodo) {
        if (novoMetodo == null) {
            throw new ValorInvalidoException("Valor de metodo de pagamento invalido.");
        }
        metodoPagamento = novoMetodo;
    }

    public void usarSaldoPreCarregado(String tipo) {
        alterarMetodoPagamento(FabricaMetodoPagamento.saldoPreCarregado(tipo, carteira));
    }

    public void recarregarSaldo(BigDecimal valor) {
        metodoPagamento.recarregar(valor);
    }

    public void exigirSemAluguel(String mensagem) {
        if (aluguelAtivo != null) {
            throw new UsuarioComAluguelException(mensagem);
        }
    }

    public void exigirLiberadoParaRetirada() {
        estado.exigirAdimplente();
        exigirSemAluguel("Usuario ja possui um aluguel em andamento.");
    }

    public void autorizarRetirada(LocalDateTime data) {
        metodoPagamento.autorizarRetirada(data);
    }

    public void iniciarAluguel(String idAluguel) {
        exigirLiberadoParaRetirada();
        aluguelAtivo = Validador.obrigatorio(idAluguel, "Identificacao do aluguel nao pode ser nula.");
    }

    public void encerrarAluguel() {
        if (aluguelAtivo == null) {
            throw new EstadoAluguelInvalidoException("Aluguel nao esta em andamento.");
        }
        aluguelAtivo = null;
    }

    public void pagar(BigDecimal valor) {
        metodoPagamento.cobrar(Validador.naoNegativo(valor, "Valor nao pode ser negativo."));
    }

    public void registrarPenalidade(BigDecimal valor) {
        if (valor.signum() <= 0) {
            throw new ValorInvalidoException("Valor deve ser positivo.");
        }
        totalPendencias = totalPendencias.add(valor);
        estado = EstadoUsuario.INADIMPLENTE;
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
