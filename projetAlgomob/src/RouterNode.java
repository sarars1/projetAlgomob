import io.jbotsim.core.Color;
import io.jbotsim.core.Link;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouterNode extends Node {
    Node parent = null;
    List<Node> children = new ArrayList<>();
    int hop = 0;
    boolean racine = false;
    
    // Change size depending on the graph used and its size
    int graph_size = 17;


    // A destination node has been selected, we send a message to our neighbors and begin the routing/construction
    @Override
    public void onSelection() {
        parent = this;
        setIconSize(20);
        setColor(Color.green);
        racine = true;
    }

    @Override
    public void onClock(){
        List<Message> msgs = getMailbox();
        // If we are not the destination node and have received messages, we update our state
        if(!racine){
            Node new_parent = null;
            int new_hop = graph_size;

            // We look for the smaller distance we received
            for (Message m : msgs) {
                if (getOutLinkTo(m.getSender()) != null && m.getFlag().equals("UPDATE")) {
                    int h = Integer.parseInt(String.valueOf(m.getContent()));
                    if (h < new_hop) {
                        new_parent = m.getSender();
                        new_hop = h;
                    }
                }
            }
            // If a node is disconnected from the destination node, it will become pink and the console will print it
            if(new_hop == graph_size ){
                setColor(Color.pink);
                send(parent, new Message("", "ABANDON"));
                parent = null;
            }

            // We update our distance to the destination node and our parent if it isn't the closest to the destination node
            else{
                setColor(null);
                hop = new_hop + 1;
                if(parent != new_parent){
                    send(parent, new Message("", "ABANDON"));
                    send(new_parent, new Message("", "ADOPTION"));
                    parent = new_parent;
                    setColor(Color.red);
                }
                sendAll(new Message(hop, "UPDATE"));
            }
        }
        // If we are the destination node, we just send a message with our hop
        else{
            sendAll(new Message(hop, "UPDATE"));
            setColor(Color.green);
        }
    }

    @Override
    public void onMessage(Message message) {
        switch (message.getFlag()) {
            // A node (sender) becomes our child
            case "ADOPTION":
                getCommonLinkWith(message.getSender()).setWidth(4);
                children.add(message.getSender());
                break;

            // A node (sender) is no longer our child
            case "ABANDON":
                getCommonLinkWith(message.getSender()).setWidth(1);
                children.remove(message.getSender());
                break;

        }
    }

    @Override
    public void onLinkRemoved(Link link){
        super.onLinkRemoved(link);
        setColor(Color.orange);
    }

    @Override
    public void onLinkAdded(Link link){
        super.onLinkAdded(link);
        setColor(Color.orange);
    }
    @Override
    public String toString() {
        return "ID= "+ getID() + " hop= " + hop ;
    }
}

