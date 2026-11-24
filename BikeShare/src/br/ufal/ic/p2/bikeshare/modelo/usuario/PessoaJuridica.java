package br.ufal.ic.p2.bikeshare.modelo.usuario;

import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.modelo.plano.Assinatura;
import br.ufal.ic.p2.bikeshare.util.Validador;

/** Usuario identificado por CNPJ e razao social. */
public class PessoaJuridica extends Usuario {

    public static final String TIPO = "pessoaJuridica";
    private static final long serialVersionUID = 1L;

    private final String cnpj;
    private final String razaoSocial;

    public PessoaJuridica(String id, DadosUsuario dados, Assinatura assinatura,
                          String cnpj, String razaoSocial) {
        super(id, dados, assinatura);
        this.cnpj = Validador.obrigatorio(cnpj, CampoObrigatorio.CNPJ);
        this.razaoSocial = Validador.obrigatorio(razaoSocial, CampoObrigatorio.RAZAO_SOCIAL);
    }

    @Override
    public String getTipo() {
        return TIPO;
    }

    @Override
    public String getDocumento(String atributo) {
        if (DocumentoUsuario.CNPJ.getAtributo().equals(atributo)) {
            return cnpj;
        }
        if (DocumentoUsuario.RAZAO_SOCIAL.getAtributo().equals(atributo)) {
            return razaoSocial;
        }
        return super.getDocumento(atributo);
    }
}
