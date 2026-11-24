package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;

/** Usuario identificado por CNPJ e razao social. */
public class PessoaJuridica extends Usuario {

    public static final String TIPO = "pessoaJuridica";
    private static final long serialVersionUID = 1L;

    private final String cnpj;
    private final String razaoSocial;

    public PessoaJuridica(String id, DadosUsuario dados, Assinatura assinatura,
                          String cnpj, String razaoSocial) {
        super(id, dados, assinatura);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String getRazaoSocial() {
        return razaoSocial;
    }
}
