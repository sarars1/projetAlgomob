import io.jbotsim.core.Topology;
import io.jbotsim.gen.basic.TopologyGenerators;
import io.jbotsim.ui.JTopology;
import io.jbotsim.ui.JViewer;
import io.jbotsim.ui.painting.JDirectedLinkPainter;
import io.jbotsim.core.Topology;
import io.jbotsim.gen.basic.TopologyGenerators;
import io.jbotsim.ui.JTopology;
import io.jbotsim.ui.JViewer;

public class MainRouter {
    public final static int n = 5;
    public static void main(String[] args) {

        Topology tp = new Topology();
        tp.setDefaultNodeModel(RouterNode.class); //
        tp.setTimeUnit(500);
        TopologyGenerators.generateRing(tp, n);

        for (int i = 0; i < n; i++){
            RouterNode node = (RouterNode) tp.getNodes().get(i);
            node.setLocation(node.getX()+250, node.getY()+100); // set node position
            //node.parent = tp.getNodes().get((i+1) % n); // parent selection
            //node.setID((int) Math.floor(Math.random() * n)); // random ID in [0..n^2[
        }
        JTopology jtp = new JTopology(tp);
        jtp.addLinkPainter(new JParentLinkPainter()); // ajoute l'orientation
        new JViewer(jtp); // dessine la topologie
        tp.start(); // démarre l'aglorithme
//        tp.pause(); // mode pas-à-pas
    }
}