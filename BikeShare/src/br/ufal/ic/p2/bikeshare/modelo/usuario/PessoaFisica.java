package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;

/** Usuario identificado por CPF. */
public class PessoaFisica extends Usuario {

    public static final String TIPO = "pessoaFisica";
    private static final long serialVersionUID = 1L;

    private final String cpf;

    public PessoaFisica(String id, DadosUsuario dados, Assinatura assinatura, String cpf) {
        super(id, dados, assinatura);
        this.cpf = cpf;
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public String getCpf() {
        return cpf;
    }
}
