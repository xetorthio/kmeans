import java.util.ArrayList;
import java.util.List;

public class KMeansResultado {
    private List<Cluster> clusters = new ArrayList<Cluster>();
    private Double ofv;

    public KMeansResultado(List<Cluster> clusters, Double ofv) {
	super();
	this.ofv = ofv;
	this.clusters = clusters;
    }

    public List<Cluster> getClusters() {
	return clusters;
    }

    public Double getOfv() {
	return ofv;
    }
}
