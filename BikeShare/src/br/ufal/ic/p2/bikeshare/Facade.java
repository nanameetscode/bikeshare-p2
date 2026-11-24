package br.ufal.ic.p2.bikeshare;

import br.ufal.ic.p2.bikeshare.excecao.sistema.OperacaoNaoImplementadaException;
import br.ufal.ic.p2.bikeshare.sistema.Sistema;

/**
 * Ponto de entrada usado pelos testes de aceitacao para acessar o BikeShare.
 * Cada metodo apenas encaminha o pedido ao {@link Sistema}: operacoes que alteram
 * o estado passam por {@code sistema.executar} ou {@code executarAcao}; consultas vao direto ao servico.
 */
public class Facade {

    private final Sistema sistema = new Sistema();

    // =====================================================================
    // US1 - Cadastro de usuario
    // =====================================================================

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpfOuCnpj) {
        return criarUsuario(nome, email, senha, tipo, cpfOuCnpj, null, null);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj, String razaoSocial) {
        return criarUsuario(nome, email, senha, tipo, cnpj, razaoSocial, null, null);
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cpf,
                                String plano, String mensalidade) {
        return sistema.executar(() -> sistema.usuarios().criarPessoaFisica(
                nome, email, senha, tipo, cpf, plano, mensalidade));
    }

    public String criarUsuario(String nome, String email, String senha, String tipo, String cnpj,
                                String razaoSocial, String plano, String mensalidade) {
        return sistema.executar(() -> sistema.usuarios().criarPessoaJuridica(
                nome, email, senha, tipo, cnpj, razaoSocial, plano, mensalidade));
    }

    // =====================================================================
    // US2 - Consulta de usuario
    // =====================================================================

    public String getAtributoUsuario(String usuario, String atributo) {
        return sistema.usuarios().getAtributo(usuario, atributo);
    }

    public String getUsuarioPorEmail(String email) {
        return sistema.usuarios().buscarIdPorEmail(email);
    }

    // =====================================================================
    // US3 - Alteracao de usuario (inclui metodo de pagamento)
    // =====================================================================

    public void alterarUsuario(String usuario, String atributo, String valor) {
        sistema.executarAcao(() -> sistema.alteracoes().alterar(usuario, atributo, valor));
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String chave) {
        sistema.executarAcao(() -> sistema.alteracoes().alterarParaPix(usuario, atributo, valor1, chave));
    }

    public void alterarUsuario(String usuario, String atributo, String valor1, String numero, String validade) {
        sistema.executarAcao(() -> sistema.alteracoes().alterarParaCartao(usuario, atributo, valor1, numero, validade));
    }

    // =====================================================================
    // US4 - Remocao de usuario
    // =====================================================================

    public void removerUsuario(String usuario) {
        sistema.executarAcao(() -> sistema.usuarios().remover(usuario));
    }

    // =====================================================================
    // US5 - Cadastro de estacao
    // =====================================================================

    public String criarEstacao(String nome, String endereco, String capacidade) {
        return sistema.executar(() -> sistema.estacoes().criar(nome, endereco, capacidade));
    }

    // =====================================================================
    // US6 - Gestao de bicicletas na estacao
    // =====================================================================

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo) {
        adicionarBicicleta(estacao, bicicleta, tipo, null);
    }

    public void adicionarBicicleta(String estacao, String bicicleta, String tipo, String nivelBateria) {
        sistema.executarAcao(() -> sistema.bicicletas().adicionar(estacao, bicicleta, tipo, nivelBateria));
    }

    public void removerBicicleta(String estacao, String bicicleta) {
        sistema.executarAcao(() -> sistema.bicicletas().remover(estacao, bicicleta));
    }

    public String getAtributoBicicleta(String bicicleta, String atributo) {
        return sistema.bicicletas().getAtributo(bicicleta, atributo);
    }

    // =====================================================================
    // US7 - Consulta de disponibilidade
    // =====================================================================

    public String getBicicletasDisponiveis(String estacao) {
        return sistema.estacoes().getBicicletasDisponiveis(estacao);
    }

    public String getVagasDisponiveis(String estacao) {
        return sistema.estacoes().getVagasDisponiveis(estacao);
    }

    public String getEstadoBicicleta(String bicicleta) {
        return sistema.bicicletas().getEstado(bicicleta);
    }

    // =====================================================================
    // US8 - Retirada de bicicleta
    // =====================================================================

    public String retirarBicicleta(String usuario, String estacao, String bicicleta, String data) {
        return sistema.executar(() -> sistema.alugueis().retirar(usuario, estacao, bicicleta, data));
    }

    // =====================================================================
    // US9 - Devolucao de bicicleta
    // =====================================================================

    public void devolverBicicleta(String aluguel, String estacao, String data) {
        sistema.executarAcao(() -> sistema.devolucoes().devolver(aluguel, estacao, data));
    }

    // =====================================================================
    // US10 - Consulta de historico de alugueis
    // =====================================================================

    public String getHistoricoAlugueis(String usuario) {
        throw new OperacaoNaoImplementadaException();
    }

    public String getHistoricoAlugueis(String usuario, String atributo) {
        throw new OperacaoNaoImplementadaException();
    }

    public String getAtributoAluguel(String aluguel, String atributo) {
        return sistema.alugueis().getAtributo(aluguel, atributo);
    }

    // =====================================================================
    // US11/US12 - Planos e tarifacao / criacao de novos planos
    // =====================================================================

    public String criarPlano(String nome, String franquiaMinutos, String valorMinutoExcedente, String mensalidade) {
        throw new OperacaoNaoImplementadaException();
    }

    // =====================================================================
    // US13 - Regras de bloqueio / lancamento manual de penalidade
    // =====================================================================

    public void lancaPenalidade(String usuario, String tipo, String valor, String motivo) {
        throw new OperacaoNaoImplementadaException();
    }

    // =====================================================================
    // US14 - Lancamento e conclusao de manutencao
    // =====================================================================

    public void lancaDefeito(String bicicleta, String descricao) {
        throw new OperacaoNaoImplementadaException();
    }

    public void concluiManutencao(String bicicleta) {
        throw new OperacaoNaoImplementadaException();
    }

    // =====================================================================
    // US15 - Relatorios
    // =====================================================================

    public String getTotalFaturado(String dataInicial, String dataFinal) {
        throw new OperacaoNaoImplementadaException();
    }

    // =====================================================================
    // US16 - Undo/redo
    // =====================================================================

    public void undo() {
        throw new OperacaoNaoImplementadaException();
    }

    public void redo() {
        throw new OperacaoNaoImplementadaException();
    }

    // =====================================================================
    // Ciclo de vida do sistema
    // =====================================================================

    /** Apaga todos os cadastros e reinicia os contadores de identificadores. */
    public void zerarSistema() {
        sistema.zerar();
    }

    /** Grava o estado atual em disco; a proxima execucao continua dele. */
    public void encerrarSistema() {
        sistema.encerrar();
    }
}
