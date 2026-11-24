package br.ufal.ic.p2.bikeshare.sistema;

import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.modelo.plano.CatalogoPlanos;
import br.ufal.ic.p2.bikeshare.modelo.plano.PlanoAvulso;
import br.ufal.ic.p2.bikeshare.modelo.plano.PlanoMensal;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;

import java.io.Serializable;

/**
 * Todo o estado do sistema em um unico objeto: e o que se grava em disco ao encerrar
 * e o que se copia para restaurar o sistema quando uma operacao falha.
 */
public class EstadoSistema implements Serializable {

    private static final long serialVersionUID = 1L;

    private Configuracao configuracao;
    private CatalogoPlanos planos;
    private Cadastro<Usuario> usuarios;
    private Cadastro<Estacao> estacoes;

    public EstadoSistema() {
        zerar();
    }

    /** Volta ao estado inicial: sem cadastros, contadores zerados e so os planos padrao. */
    public final void zerar() {
        configuracao = new Configuracao();
        planos = criarPlanosPadrao(configuracao);
        usuarios = new Cadastro<>("U");
        estacoes = new Cadastro<>("E");
    }

    /** Substitui o conteudo atual pelo de outro estado (usado ao restaurar uma copia). */
    public void restaurar(EstadoSistema outro) {
        configuracao = outro.configuracao;
        planos = outro.planos;
        usuarios = outro.usuarios;
        estacoes = outro.estacoes;
    }

    private static CatalogoPlanos criarPlanosPadrao(Configuracao cfg) {
        CatalogoPlanos catalogo = new CatalogoPlanos(
                new PlanoAvulso("avulso", cfg.getTarifaMinutoAvulso()));
        catalogo.adicionar(new PlanoMensal("assinante", cfg.getFranquiaMinutosAssinante(),
                cfg.getTarifaExcedenteAssinante(), cfg.getMensalidadePadraoAssinante()));
        return catalogo;
    }

    public Configuracao getConfiguracao() {
        return configuracao;
    }

    public CatalogoPlanos getPlanos() {
        return planos;
    }

    public Cadastro<Usuario> getUsuarios() {
        return usuarios;
    }

    public Cadastro<Estacao> getEstacoes() {
        return estacoes;
    }
}
