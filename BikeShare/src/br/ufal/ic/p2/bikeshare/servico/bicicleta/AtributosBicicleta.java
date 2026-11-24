package br.ufal.ic.p2.bikeshare.servico.bicicleta;

import br.ufal.ic.p2.bikeshare.excecao.validacao.AtributoInexistenteException;
import br.ufal.ic.p2.bikeshare.modelo.bicicleta.Bicicleta;

final class AtributosBicicleta {

    private AtributosBicicleta() {
    }

    static String ler(Bicicleta bicicleta, String atributo) {
        return switch (atributo == null ? "" : atributo) {
            case "tipo" -> bicicleta.getTipo();
            case "estado" -> bicicleta.getEstado().getRotulo();
            case "estacao" -> bicicleta.getEstacao().orElse("nenhuma");
            case "nivelBateria" -> String.valueOf(bicicleta.getNivelBateria());
            default -> throw new AtributoInexistenteException();
        };
    }
}
