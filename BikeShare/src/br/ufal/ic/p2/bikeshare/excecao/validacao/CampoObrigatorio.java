package br.ufal.ic.p2.bikeshare.excecao.validacao;

/** Campos que nao podem ficar vazios, cada um com a mensagem exata dos testes. */
public enum CampoObrigatorio {
    ID_USUARIO("Identificacao do usuario nao pode ser nula."),
    ID_ESTACAO("Identificacao da estacao nao pode ser nula."),
    ID_BICICLETA("Identificacao da bicicleta nao pode ser nula."),
    ID_ALUGUEL("Identificacao do aluguel nao pode ser nula."),
    NOME("Nome nao pode ser nulo."),
    EMAIL("Email nao pode ser nulo."),
    SENHA("Senha nao pode ser nula."),
    CPF("CPF nao pode ser nulo."),
    CNPJ("CNPJ nao pode ser nulo."),
    RAZAO_SOCIAL("Razao social nao pode ser nula."),
    MENSALIDADE("Mensalidade nao pode ser nula."),
    ENDERECO("Endereco nao pode ser nulo."),
    CAPACIDADE("Capacidade nao pode ser nula."),
    NIVEL_BATERIA("Nivel de bateria nao pode ser nulo."),
    DATA("Data nao pode ser nula."),
    NUMERO_CARTAO("Numero do cartao nao pode ser nulo."),
    VALIDADE_CARTAO("Validade do cartao nao pode ser nula."),
    CHAVE_PIX("Chave pix nao pode ser nula."),
    VALOR("Valor nao pode ser nulo."),
    NOME_PLANO("Nome do plano nao pode ser nulo.");

    private final String mensagem;

    CampoObrigatorio(String mensagem) {
        this.mensagem = mensagem;
    }

    public CampoNuloException erro() {
        return new CampoNuloException(this);
    }

    public String getMensagem() {
        return mensagem;
    }
}
