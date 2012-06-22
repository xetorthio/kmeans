public class Punto {
    private float x;
    private float y;

    public Punto(String[] strings) {
	super();
	this.x = Float.parseFloat(strings[0]);
	this.y = Float.parseFloat(strings[1]);
    }

    public Punto(Float x, Float y) {
	this.x = x;
	this.y = y;
    }

    public float getX() {
	return x;
    }

    public float getY() {
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

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(x);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(y);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	Punto other = (Punto) obj;
	if (this.x == other.x && this.y == other.y) {
	    return true;
	} else {
	    return false;
	}
    }
}