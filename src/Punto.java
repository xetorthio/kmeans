import java.util.ArrayList;
import java.util.List;

public class Punto {
    private Float[] data;

    public Punto(String[] strings) {
	super();
	List<Float> puntos = new ArrayList<Float>();
	for (String string : strings) {
	    puntos.add(Float.parseFloat(string));
	}
	this.data = puntos.toArray(new Float[strings.length]);
    }

    public Punto(Float[] data) {
	this.data = data;
    }

    public float get(int dimension) {
	return data[dimension];
    }

    public int getGrado() {
	return data.length;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(data[0]);
	for (int i = 1; i < data.length; i++) {
	    sb.append(", ");
	    sb.append(data[i]);
	}
	return sb.toString();
    }

    public Double distanciaEuclideana(Punto destino) {
	Double d = 0d;
	for (int i = 0; i < data.length; i++) {
	    d += Math.pow(data[i] - destino.get(i), 2);
	}
	return Math.sqrt(d);
    }

    @Override
    public boolean equals(Object obj) {
	Punto other = (Punto) obj;
	for (int i = 0; i < data.length; i++) {
	    if (data[i] != other.get(i)) {
		return false;
	    }
	}
	return true;
    }
}