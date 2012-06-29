import java.util.ArrayList;
import java.util.Arrays;
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

	    Float[] d = new Float[c.getPuntos().get(0).getGrado()];
	    Arrays.fill(d, 0f);
	    for (Punto p : c.getPuntos()) {
		for (int i = 0; i < p.getGrado(); i++) {
		    d[i] += (p.get(i) / c.getPuntos().size());
		}
	    }

	    Punto nuevoCentroide = new Punto(d);

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

	List<Float> maximos = new ArrayList<Float>();
	List<Float> minimos = new ArrayList<Float>();
	// me fijo máximo y mínimo de cada dimensión

	for (int i = 0; i < puntos.get(0).getGrado(); i++) {
	    Float min = Float.POSITIVE_INFINITY, max = Float.NEGATIVE_INFINITY;

	    for (Punto punto : puntos) {
		min = min > punto.get(i) ? punto.get(0) : min;
		max = max < punto.get(i) ? punto.get(i) : max;
	    }

	    maximos.add(max);
	    minimos.add(min);
	}

	Random random = new Random();

	for (int i = 0; i < k; i++) {
	    Float[] data = new Float[puntos.get(0).getGrado()];
	    Arrays.fill(data, 0f);
	    for (int d = 0; d < puntos.get(0).getGrado(); d++) {
		data[d] = random.nextFloat()
			* (maximos.get(d) - minimos.get(d)) + minimos.get(d);
	    }

	    Cluster c = new Cluster();
	    Punto centroide = new Punto(data);
	    c.setCentroide(centroide);
	    centroides.add(c);
	}

	return centroides;
    }
}
