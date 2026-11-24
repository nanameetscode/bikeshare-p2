package br.ufal.ic.p2.bikeshare.sistema;

import br.ufal.ic.p2.bikeshare.excecao.validacao.ValorInvalidoException;
import br.ufal.ic.p2.bikeshare.util.Validador;

import java.math.BigDecimal;
import java.util.Properties;

/**
 * Parametros de tarifacao e limites de negocio (RNF-4). Os valores padrao sao os do
 * enunciado e podem ser trocados pelo arquivo {@code bikeshare.properties}. Limites
 * (bateria, carencia, tempo maximo, adicional, multa) valem a partir da proxima execucao;
 * os valores dos planos padrao entram quando o sistema e zerado, porque quem ja
 * contratou um plano fica com as condicoes do contrato.
 */
public class Configuracao {

    private final BigDecimal tarifaMinutoAvulso;
    private final int franquiaMinutosAssinante;
    private final BigDecimal tarifaExcedenteAssinante;
    private final BigDecimal mensalidadePadraoAssinante;
    private final int nivelMinimoBateria;
    private final BigDecimal adicionalMinutoEletrica;
    private final int minutosCarencia;
    private final int minutosMaximoAluguel;
    private final BigDecimal valorPenalidadeAtraso;

    public Configuracao() {
        this(new Properties());
    }

    public Configuracao(Properties valores) {
        tarifaMinutoAvulso = decimal(valores, "tarifaMinutoAvulso", "0.50");
        franquiaMinutosAssinante = inteiro(valores, "franquiaMinutosAssinante", "60");
        tarifaExcedenteAssinante = decimal(valores, "tarifaExcedenteAssinante", "0.30");
        mensalidadePadraoAssinante = decimal(valores, "mensalidadePadraoAssinante", "30");
        nivelMinimoBateria = inteiro(valores, "nivelMinimoBateria", "15");
        adicionalMinutoEletrica = decimal(valores, "adicionalMinutoEletrica", "0.20");
        minutosCarencia = inteiro(valores, "minutosCarencia", "2");
        minutosMaximoAluguel = inteiro(valores, "minutosMaximoAluguel", "1440");
        valorPenalidadeAtraso = decimal(valores, "valorPenalidadeAtraso", "10");
    }

    /** Tarifas podem ter mais de duas casas; o valor cobrado e que e arredondado para centavos. */
    private static BigDecimal decimal(Properties valores, String chave, String padrao) {
        String erro = erro(chave);
        BigDecimal valor = Validador.decimal(valores.getProperty(chave, padrao), erro, erro);
        return Validador.naoNegativo(valor, erro);
    }

    private static int inteiro(Properties valores, String chave, String padrao) {
        String erro = erro(chave);
        int valor = Validador.inteiro(valores.getProperty(chave, padrao), erro, erro);
        if (valor < 0) {
            throw new ValorInvalidoException(erro);
        }
        return valor;
    }

    private static String erro(String chave) {
        return "Configuracao invalida: " + chave + ".";
    }

    public BigDecimal getTarifaMinutoAvulso() {
        return tarifaMinutoAvulso;
    }

    public int getFranquiaMinutosAssinante() {
        return franquiaMinutosAssinante;
    }

    public BigDecimal getTarifaExcedenteAssinante() {
        return tarifaExcedenteAssinante;
    }

    public BigDecimal getMensalidadePadraoAssinante() {
        return mensalidadePadraoAssinante;
    }

    public int getNivelMinimoBateria() {
        return nivelMinimoBateria;
    }

    public BigDecimal getAdicionalMinutoEletrica() {
        return adicionalMinutoEletrica;
    }

    public int getMinutosCarencia() {
        return minutosCarencia;
    }

    public int getMinutosMaximoAluguel() {
        return minutosMaximoAluguel;
    }

    public BigDecimal getValorPenalidadeAtraso() {
        return valorPenalidadeAtraso;
    }
}
