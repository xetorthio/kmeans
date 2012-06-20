import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    public KMeansResultado calcular(List<Punto> puntos, Integer k) {
	List<Punto> centroides = elegirCentroides(puntos, k);
	List<Cluster> clusters = new ArrayList<Cluster>();

	while (!finalizo(clusters)) {
	    // recorrer todos los puntos y verificar distancia respecto de cada
	    // centroide.
	    // asignar punto al centroide con menor distancia

	    // por cada cluster, recalcular el centroide.
	}

	Double ofv = calcularFuncionObjetivo(clusters);

	return new KMeansResultado(clusters, ofv);
    }

    private Double calcularFuncionObjetivo(List<Cluster> clusters) {
	Double ofv = 0d;
	for (Cluster cluster : clusters) {
	    for (Punto punto : cluster.getPuntos()) {
		ofv += punto.distanciaEuclideana(cluster.getCentroide());
	    }
	}

	return ofv;
    }

    private boolean finalizo(List<Cluster> clusters) {
	for (Cluster cluster : clusters) {
	    if (!cluster.isTermino()) {
		return false;
	    }
	}
	return true;
    }

    private List<Punto> elegirCentroides(List<Punto> puntos, Integer k) {
	List<Punto> centroides = new ArrayList<Punto>();

	// me fijo máximo y mínimo Y
	// me fijo máximo y mínimo X

	Double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY, minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;

	for (Punto punto : puntos) {
	    minX = minX > punto.getX() ? punto.getX() : minX;
	    maxX = maxX < punto.getX() ? punto.getX() : maxX;
	    minY = minY > punto.getY() ? punto.getY() : minY;
	    maxY = maxY < punto.getY() ? punto.getY() : maxY;
	}

	Random random = new Random();

	for (int i = 0; i < k; i++) {
	    Double x = random.nextDouble() * (maxX - minX) + minX;
	    Double y = random.nextDouble() * (maxY - minY) + minY;

	    centroides.add(new Punto(x, y));
	}

	return centroides;
    }
}
