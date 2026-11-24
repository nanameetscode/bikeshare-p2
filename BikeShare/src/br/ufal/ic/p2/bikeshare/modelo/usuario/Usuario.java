package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.excecao.usuario.TipoUsuarioIncompativelException;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.MetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.pagamento.SemMetodoPagamento;
import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;

import java.io.Serializable;

/**
 * Ciclista cadastrado. Concentra os dados comuns a pessoa fisica e juridica;
 * o documento de identificacao fica em cada subclasse, que sobrescreve so o que possui.
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

    protected Usuario(String id, DadosUsuario dados, Assinatura assinatura) {
        this.id = id;
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.assinatura = assinatura;
    }

    /** Rotulo do tipo no formato dos testes ({@code pessoaFisica} ou {@code pessoaJuridica}). */
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

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
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
