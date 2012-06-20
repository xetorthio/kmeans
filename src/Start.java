import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class Start {
    public static void main(String[] args) throws IOException {
	CSVReader reader = new CSVReader(new FileReader("data.csv"));
	CSVWriter writer = new CSVWriter(new FileWriter("out.csv"));

	List<String[]> myEntries = reader.readAll();
	List<Punto> puntos = new ArrayList<Punto>();

	for (String[] strings : myEntries) {
	    Punto p = new Punto(strings);
	    puntos.add(p);
	}

	KMeans kmeans = new KMeans();
	for (int k = 1; k <= 5; k++) {
	    KMeansResultado resultado = kmeans.calcular(puntos, k);
	    writer.writeNext(new String[] { "------- Con k=" + k + " -------" });
	    for (Cluster cluster : resultado.getClusters()) {
		for (Punto punto : cluster.getPuntos()) {
		    writer.writeNext(new String[] {
			    String.valueOf(punto.getX()),
			    String.valueOf(punto.getY()) });
		}
		writer.writeNext(new String[] { "" });
		writer.writeNext(new String[] {
			String.valueOf(cluster.getCentroide().getX()),
			String.valueOf(cluster.getCentroide().getY()) });
	    }
	}
	writer.close();
    }
}
