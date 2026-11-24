package br.ufal.ic.p2.bikeshare.sistema;

import br.ufal.ic.p2.bikeshare.modelo.aluguel.Aluguel;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
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

    private CatalogoPlanos planos;
    private Cadastro<Usuario> usuarios;
    private Cadastro<Estacao> estacoes;
    private Cadastro<Bicicleta> bicicletas;
    private Cadastro<Aluguel> alugueis;

    public EstadoSistema(Configuracao configuracao) {
        zerar(configuracao);
    }

    /** Volta ao estado inicial: sem cadastros, contadores zerados e so os planos padrao. */
    public final void zerar(Configuracao configuracao) {
        planos = criarPlanosPadrao(configuracao);
        usuarios = new Cadastro<>("U");
        estacoes = new Cadastro<>("E");
        bicicletas = new Cadastro<>("B");
        alugueis = new Cadastro<>("A");
    }

    /** Substitui o conteudo atual pelo de uma copia; todo campo novo tem que entrar aqui. */
    public void restaurar(EstadoSistema outro) {
        planos = outro.planos;
        usuarios = outro.usuarios;
        estacoes = outro.estacoes;
        bicicletas = outro.bicicletas;
        alugueis = outro.alugueis;
    }

    private static CatalogoPlanos criarPlanosPadrao(Configuracao cfg) {
        CatalogoPlanos catalogo = new CatalogoPlanos(
                new PlanoAvulso("avulso", cfg.getTarifaMinutoAvulso()));
        catalogo.adicionar(new PlanoMensal("assinante", cfg.getFranquiaMinutosAssinante(),
                cfg.getTarifaExcedenteAssinante(), cfg.getMensalidadePadraoAssinante()));
        return catalogo;
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

    public Cadastro<Bicicleta> getBicicletas() {
        return bicicletas;
    }

    public Cadastro<Aluguel> getAlugueis() {
        return alugueis;
    }
}
