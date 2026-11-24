package br.ufal.ic.p2.bikeshare.servico.aluguel;

import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;
import br.ufal.ic.p2.bikeshare.modelo.aluguel.Aluguel;
import br.ufal.ic.p2.bikeshare.util.Datas;
import br.ufal.ic.p2.bikeshare.util.Formato;

final class AtributosAluguel {

    private AtributosAluguel() {
    }

    static String ler(Aluguel aluguel, String atributo) {
        return switch (atributo == null ? "" : atributo) {
            case "estado" -> aluguel.getEstado().getRotulo();
            case "usuario" -> aluguel.getUsuario();
            case "bicicleta" -> aluguel.getBicicleta();
            case "estacaoOrigem" -> aluguel.getEstacaoOrigem();
            case "inicio" -> Datas.formatar(aluguel.getInicio());
            case "fim" -> Datas.formatar(aluguel.getFim());
            case "estacaoDestino" -> aluguel.getEstacaoDestino();
            case "valorCobrado" -> Formato.dinheiro(aluguel.getValorCobrado());
            default -> throw new AtributoInexistenteException();
        };
    }
}
