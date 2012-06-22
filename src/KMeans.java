import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    public KMeansResultado calcular(List<Punto> puntos, Integer k) {
	List<Cluster> clusters = elegirCentroides(puntos, k);

	while (!finalizo(clusters)) {
	    prepararClusters(clusters);
	    asignarPuntos(puntos, clusters);
	    recalcularCentroides(clusters);
	}

	Double ofv = calcularFuncionObjetivo(clusters);

	return new KMeansResultado(clusters, ofv);
    }

    private void recalcularCentroides(List<Cluster> clusters) {
	for (Cluster c : clusters) {
	    if (c.getPuntos().isEmpty()) {
		c.setTermino(true);
		continue;
	    }

	    Float nuevoX = 0f;
	    Float nuevoY = 0f;
	    for (Punto p : c.getPuntos()) {
		nuevoX += p.getX();
		nuevoY += p.getY();
	    }

	    Punto nuevoCentroide = new Punto(nuevoX / c.getPuntos().size(),
		    nuevoY / c.getPuntos().size());

	    if (nuevoCentroide.equals(c.getCentroide())) {
		c.setTermino(true);
	    } else {
		c.setCentroide(nuevoCentroide);
	    }
	}
    }

    private void asignarPuntos(List<Punto> puntos, List<Cluster> clusters) {
	for (Punto punto : puntos) {
	    Cluster masCercano = clusters.get(0);
	    Double distanciaMinima = Double.MAX_VALUE;
	    for (Cluster cluster : clusters) {
		Double distancia = punto.distanciaEuclideana(cluster
			.getCentroide());
		if (distanciaMinima > distancia) {
		    distanciaMinima = distancia;
		    masCercano = cluster;
		}
	    }
	    masCercano.getPuntos().add(punto);
	}
    }

    private void prepararClusters(List<Cluster> clusters) {
	for (Cluster c : clusters) {
	    c.limpiarPuntos();
	}
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

    private List<Cluster> elegirCentroides(List<Punto> puntos, Integer k) {
	List<Cluster> centroides = new ArrayList<Cluster>();

	// me fijo máximo y mínimo Y
	// me fijo máximo y mínimo X

	Float minX = Float.POSITIVE_INFINITY, maxX = Float.NEGATIVE_INFINITY, minY = Float.POSITIVE_INFINITY, maxY = Float.NEGATIVE_INFINITY;

	for (Punto punto : puntos) {
	    minX = minX > punto.getX() ? punto.getX() : minX;
	    maxX = maxX < punto.getX() ? punto.getX() : maxX;
	    minY = minY > punto.getY() ? punto.getY() : minY;
	    maxY = maxY < punto.getY() ? punto.getY() : maxY;
	}

	Random random = new Random();

	for (int i = 0; i < k; i++) {
	    Float x = random.nextFloat() * (maxX - minX) + minX;
	    Float y = random.nextFloat() * (maxY - minY) + minY;

	    Cluster c = new Cluster();
	    Punto centroide = new Punto(x, y);
	    c.setCentroide(centroide);
	    centroides.add(c);
	}

	return centroides;
    }
}
