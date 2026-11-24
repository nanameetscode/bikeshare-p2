package br.ufal.ic.p2.bikeshare.sistema;

import br.ufal.ic.p2.bikeshare.persistencia.ArquivoConfiguracao;
import br.ufal.ic.p2.bikeshare.persistencia.ArquivoEstado;
import br.ufal.ic.p2.bikeshare.persistencia.Serializador;
import br.ufal.ic.p2.bikeshare.servico.aluguel.AluguelService;
import br.ufal.ic.p2.bikeshare.servico.aluguel.DevolucaoService;
import br.ufal.ic.p2.bikeshare.servico.bicicleta.BicicletaService;
import br.ufal.ic.p2.bikeshare.servico.estacao.EstacaoService;
import br.ufal.ic.p2.bikeshare.servico.usuario.AlteracaoUsuarioService;
import br.ufal.ic.p2.bikeshare.servico.usuario.UsuarioService;

import java.nio.file.Path;
import java.util.function.Supplier;

/**
 * Coordenador do BikeShare. Carrega o estado salvo, oferece os servicos de cada assunto
 * e executa toda operacao que altera o estado por um unico ponto: se ela falhar no meio,
 * o estado anterior e restaurado por completo antes de o erro seguir para a {@code Facade}.
 */
public class Sistema {

    private static final Path ARQUIVO_PADRAO = Path.of("bikeshare_data.ser");
    private static final Path ARQUIVO_CONFIGURACAO = Path.of("bikeshare.properties");

    private final ArquivoEstado arquivo = new ArquivoEstado(ARQUIVO_PADRAO);
    private final Configuracao configuracao;
    private final EstadoSistema estado;
    private final UsuarioService usuarios;
    private final AlteracaoUsuarioService alteracoes;
    private final EstacaoService estacoes;
    private final BicicletaService bicicletas;
    private final AluguelService alugueis;
    private final DevolucaoService devolucoes;

    public Sistema() {
        configuracao = new Configuracao(ArquivoConfiguracao.carregar(ARQUIVO_CONFIGURACAO));
        estado = arquivo.carregar().orElseGet(() -> new EstadoSistema(configuracao));
        usuarios = new UsuarioService(estado);
        alteracoes = new AlteracaoUsuarioService(estado, usuarios);
        estacoes = new EstacaoService(estado, configuracao);
        bicicletas = new BicicletaService(estado, estacoes);
        alugueis = new AluguelService(estado, configuracao, usuarios, estacoes, bicicletas);
        devolucoes = new DevolucaoService(configuracao, alugueis, usuarios, estacoes, bicicletas);
    }

    /** Executa uma operacao que altera o estado e devolve o seu resultado. */
    public <T> T executar(Supplier<T> operacao) {
        EstadoSistema antes = Serializador.copiar(estado, EstadoSistema.class);
        try {
            return operacao.get();
        } catch (RuntimeException erro) {
            estado.restaurar(antes);
            throw erro;
        }
    }

    /** Mesmo que {@link #executar(Supplier)}, para operacoes sem retorno. */
    public void executarAcao(Runnable operacao) {
        executar(() -> {
            operacao.run();
            return null;
        });
    }

    public void zerar() {
        executarAcao(() -> estado.zerar(configuracao));
    }

    public void encerrar() {
        arquivo.salvar(estado);
    }

    public UsuarioService usuarios() {
        return usuarios;
    }

    public AlteracaoUsuarioService alteracoes() {
        return alteracoes;
    }

    public EstacaoService estacoes() {
        return estacoes;
    }

    public BicicletaService bicicletas() {
        return bicicletas;
    }

    public AluguelService alugueis() {
        return alugueis;
    }

    public DevolucaoService devolucoes() {
        return devolucoes;
    }
}
