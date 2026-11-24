package br.ufal.ic.p2.bikeshare.modelo.bicicleta;

public class BicicletaConvencional extends Bicicleta {

    private static final long serialVersionUID = 1L;

    public BicicletaConvencional(String id) {
        super(id);
    }

    @Override
    public String getTipo() {
        return TipoBicicleta.CONVENCIONAL.getRotulo();
    }
}
