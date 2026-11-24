package br.ufal.ic.p2.bikeshare.excecao.validacao;

/** Valores informados com formato ou faixa errados, cada um com a mensagem exata dos testes. */
public enum ValorRecusado {
    EMAIL("Email invalido."),
    TIPO("Tipo invalido."),
    METODO_PAGAMENTO("Valor de metodo de pagamento invalido."),
    MENSALIDADE_NAO_NUMERICA("Mensalidade deve ser numerica."),
    MENSALIDADE_NEGATIVA("Mensalidade deve ser nao-negativa."),
    CAPACIDADE_NAO_NUMERICA("Capacidade deve ser numerica."),
    CAPACIDADE_NAO_POSITIVA("Capacidade deve ser positiva."),
    NIVEL_BATERIA_NAO_NUMERICO("Nivel de bateria deve ser numerico."),
    NIVEL_BATERIA_FORA_DA_FAIXA("Nivel de bateria deve estar entre 0 e 100."),
    VALOR_NAO_NUMERICO("Valor deve ser numerico."),
    VALOR_NAO_POSITIVO("Valor deve ser positivo."),
    VALOR_NEGATIVO("Valor nao pode ser negativo."),
    DATA("Data invalida."),
    VALIDADE_CARTAO("Validade do cartao invalida."),
    FRANQUIA_NEGATIVA("Franquia deve ser nao-negativa."),
    VALOR_MINUTO_NEGATIVO("Valor do minuto excedente deve ser nao-negativo.");

    private final String mensagem;

    ValorRecusado(String mensagem) {
        this.mensagem = mensagem;
    }

    public ValorInvalidoException erro() {
        return new ValorInvalidoException(this);
    }

    public String getMensagem() {
        return mensagem;
    }
}
