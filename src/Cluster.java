import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<Punto> puntos = new ArrayList<Punto>();
    private Punto centroide;
    private boolean termino = false;

    public Punto getCentroide() {
	return centroide;
    }

    public void setCentroide(Punto centroide) {
	this.centroide = centroide;
    }

    public List<Punto> getPuntos() {
	return puntos;
    }

    public boolean isTermino() {
	return termino;
    }

    public void setTermino(boolean termino) {
	this.termino = termino;
    }

    public void limpiarPuntos() {
	puntos.clear();
    }

    @Override
    public String toString() {
	return centroide.toString();
    }
}
