import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Start {
    public static void main(String[] args) throws IOException {
	CSVReader reader = new CSVReader(new FileReader("data.csv"));
	FileWriter writer = new FileWriter("out.csv");

	List<String[]> myEntries = reader.readAll();
	List<Punto> puntos = new ArrayList<Punto>();

	for (String[] strings : myEntries) {
	    Punto p = new Punto(strings);
	    puntos.add(p);
	}

	KMeans kmeans = new KMeans();
	for (int k = 1; k <= 5; k++) {
	    KMeansResultado resultado = kmeans.calcular(puntos, k);
	    writer.write("------- Con k=" + k + " ofv=" + resultado.getOfv()
		    + "-------\n");
	    int i = 0;
	    for (Cluster cluster : resultado.getClusters()) {
		i++;
		writer.write("-- Cluster " + i + " --\n");
		for (Punto punto : cluster.getPuntos()) {
		    writer.write(punto.toString() + "\n");
		}
		writer.write("\n");
		writer.write(cluster.getCentroide().toString());
		writer.write("\n\n");
	    }
	}
	writer.close();
    }
}