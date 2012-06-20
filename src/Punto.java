public class Punto {
    private double x;
    private double y;

    public Punto(String[] strings) {
	super();
	this.x = Double.parseDouble(strings[0]);
	this.y = Double.parseDouble(strings[1]);
    }

    public Punto(Double x, Double y) {
	this.x = x;
	this.y = y;
    }

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }

    @Override
    public String toString() {
	return "(" + x + ", " + y + ")";
    }

    public Double distanciaEuclideana(Punto destino) {
	return Math.sqrt(Math.pow(destino.x - this.x, 2)
		+ Math.pow(destino.y - this.y, 2));
    }
}