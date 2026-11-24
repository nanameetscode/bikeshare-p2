package br.ufal.ic.p2.bikeshare.servico.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;
import br.ufal.ic.p2.bikeshare.modelo.estacao.Estacao;

import java.util.Optional;

final class AtributosBicicleta {

    private AtributosBicicleta() {
    }

    static String ler(Bicicleta bicicleta, String atributo, Optional<Estacao> estacao) {
        return switch (atributo == null ? "" : atributo) {
            case "tipo" -> bicicleta.getTipo();
            case "estado" -> bicicleta.getEstado().getRotulo();
            case "estacao" -> estacao.map(Estacao::getId).orElse("nenhuma");
            case "nivelBateria" -> String.valueOf(bicicleta.getNivelBateria());
            default -> throw new AtributoInexistenteException();
        };
    }
}
