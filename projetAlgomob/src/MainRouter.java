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
        tp.setDefaultNodeModel(RouterNode.class);
        tp.setTimeUnit(500);

        JTopology jtp = new JTopology(tp);
        jtp.addLinkPainter(new JParentLinkPainter()); // Parent-child arrows
        new JViewer(jtp); 
        tp.start(); 
 //       tp.pause(); // single step mode
    }
}
