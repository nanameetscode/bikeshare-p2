package br.ufal.ic.p2.bikeshare.servico.aluguel;

import br.ufal.ic.p2.bikeshare.excecao.aluguel.AluguelNaoEncontradoException;
import br.ufal.ic.p2.bikeshare.excecao.validacao.CampoObrigatorio;
import br.ufal.ic.p2.bikeshare.modelo.aluguel.Aluguel;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;
import br.ufal.ic.p2.bikeshare.modelo.usuario.Usuario;
import br.ufal.ic.p2.bikeshare.servico.bicicleta.BicicletaService;
import br.ufal.ic.p2.bikeshare.servico.estacao.EstacaoService;
import br.ufal.ic.p2.bikeshare.servico.usuario.UsuarioService;
import br.ufal.ic.p2.bikeshare.sistema.Cadastro;
import br.ufal.ic.p2.bikeshare.sistema.Configuracao;
import br.ufal.ic.p2.bikeshare.sistema.EstadoSistema;
import br.ufal.ic.p2.bikeshare.util.Datas;

import java.time.LocalDateTime;

/** Retirada de bicicletas e consulta de alugueis. */
public class AluguelService {

    private final EstadoSistema estado;
    private final Configuracao configuracao;
    private final UsuarioService usuarios;
    private final EstacaoService estacoes;
    private final BicicletaService bicicletas;

    public AluguelService(EstadoSistema estado, Configuracao configuracao, UsuarioService usuarios,
                          EstacaoService estacoes, BicicletaService bicicletas) {
        this.estado = estado;
        this.configuracao = configuracao;
        this.usuarios = usuarios;
        this.estacoes = estacoes;
        this.bicicletas = bicicletas;
    }

    /**
     * Inicia um aluguel. Todas as pre-condicoes sao conferidas antes de qualquer alteracao.
     *
     * @return o identificador do aluguel ({@code A1}, {@code A2}...)
     */
    public String retirar(String idUsuario, String idEstacao, String idBicicleta, String data) {
        Usuario usuario = usuarios.buscar(idUsuario);
        Estacao estacao = estacoes.buscar(idEstacao);
        Bicicleta bicicleta = bicicletas.buscar(idBicicleta);
        LocalDateTime inicio = Datas.ler(data);
        validarRetirada(usuario, estacao, bicicleta, inicio);
        Aluguel aluguel = new Aluguel(alugueis().gerarId(), usuario.getId(),
                bicicleta.getId(), estacao.getId(), inicio);
        alugueis().adicionar(aluguel.getId(), aluguel);
        estacao.liberar(bicicleta);
        bicicleta.retirar();
        usuario.iniciarAluguel(aluguel.getId());
        return aluguel.getId();
    }

    public Aluguel buscar(String id) {
        return alugueis().exigir(id, CampoObrigatorio.ID_ALUGUEL,
                AluguelNaoEncontradoException::new);
    }

    public String getAtributo(String id, String atributo) {
        return AtributosAluguel.ler(buscar(id), atributo);
    }

    private void validarRetirada(Usuario usuario, Estacao estacao, Bicicleta bicicleta, LocalDateTime inicio) {
        usuario.exigirLiberadoParaRetirada();
        estacao.exigirPresenca(bicicleta);
        bicicleta.exigirDisponivel();
        bicicleta.exigirBateria(configuracao.getNivelMinimoBateria());
        usuario.autorizarRetirada(inicio);
    }

    private Cadastro<Aluguel> alugueis() {
        return estado.getAlugueis();
    }
}
