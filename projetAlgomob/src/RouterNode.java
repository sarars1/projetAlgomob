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
    int hop = 1;
    boolean constructed = false;


    // A destination node has been selected, we send a message to our neighbors and begin the routing/construction
    @Override
    public void onSelection() {
        parent = this;
        setIconSize(20);
        sendAll(new Message(""+hop, "CONSTRUCTION"));
        setColor(Color.yellow);
        constructed = true;
    }

    @Override
    public void onMessage(Message message) {
        Node sender = message.getSender();

        int h = 0;
        if(!message.getContent().equals("")){
            h = Integer.parseInt(String.valueOf(message.getContent()));
        }

        switch (message.getFlag()) {

            // Construction of the sub-graph - every node has a parent and is at the closest distance possible
            // from the destination node
            case "CONSTRUCTION":
                // If we don't have a parent, the sender becomes our parent, and our hop it's their hop + 1
                if (parent == null) {
                    parent = message.getSender();
                    send(parent, new Message("", "ADOPTION"));
                    hop = h + 1;
                    sendAll(new Message(hop, "CONSTRUCTION"));
                    constructed = true;
                } else {
                    // If we have a parent but the sender has smaller hop than them, if so the sender becomes our new parent, our hop becomes their hop + 1
                    // t+1 = hop of the sender node + 1 (for us)
                    if (h + 1 < hop) {

                        send(parent, new Message("", "ABANDON"));
                        parent = message.getSender();

                        hop = h + 1;
                        send(parent, new Message("", "ADOPTION"));
                        sendAll(new Message(message));
                    }

                }
                break;

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

            // A link was added or removed
            case "MAYBE LIAR":
                send(sender, new Message(hop, "UPDATE"));
                break;

            // We receive hop from our neighbors, we see if it is equal or smaller than ours,
            // in that case the neighbor becomes our parent
            case "UPDATE":
                // If the neighbor's hop is smaller than ours he becomes our parent
                if (hop > h) {
                    send(parent, new Message("", "ABANDON"));
                    hop = h + 1;
                    parent = sender;
                    send(sender, new Message("", "ADOPTION"));

                    for(Node child : children){
                        send(child, new Message(hop, "RECEIVED"));
                    }
                }

                // If the neighbor's hop is equal to ours, we check to see if there's a neighbor than sent us a
                // message with a smaller hop. -> We give priority to smaller hops
                else if (hop == h) {

                    send(parent, new Message("", "ABANDON"));
                    Node new_parent = sender;
                    int new_hop = hop;

                    // We get the list of messages received to check if there's a smaller than the hop we received
                    List<Message> msgs = getMailbox();
                    for (Message m : msgs) {
                        if (getOutLinkTo(m.getSender()) != null && m.getFlag().equals("UPDATE")) {
                            if ((int) m.getContent() < h) {
                                new_parent = m.getSender();
                                new_hop = (int) m.getContent();
                            }
                        }
                    }
                    hop = new_hop + 1;
                    send(new_parent, new Message("", "ADOPTION"));
                    parent = new_parent;

                    for (Node child : children) {
                        send(child, new Message(hop, "RECEIVED"));
                    }
                }
                    // CAS OÙ LES HOPS DES VOISINS SONT PLUS GRANDES QUE LE NOTRE
//                }else if(hop<h && getCommonLinkWith(parent) == null){
//                    send(parent, new Message("", "ABANDON"));
//
//                    List<Node> neighbors = getNeighbors();
//                    Random rand = new Random();
//                    Node randomNode = neighbors.get(rand.nextInt(neighbors.size()));
//                    parent = randomNode;
//                    send(parent, new Message("", "ADOPTION"));
//                    hop = h+1;
//
//                }
                setColor(null);
                break;

            case "RECEIVED":
//
                // If our father is further from the destination than us, we might need to change our father
                if(h+1>hop){
                    sendAll(new Message("","MAYBE LIAR"));
                    setColor(Color.red);
                }
                // If our father is closer to the destination node, we have to update our distance as well
                else if(hop-h>1){
                    hop = h + 1;
                    for(Node child : children){
                        send(child, new Message(hop, "RECEIVED"));
                    }
                }
        }
    }

    // We alert our neighbors a link has been removed
    @Override
    public void onLinkRemoved(Link link) {
        super.onLinkRemoved(link);
        sendAll(new Message("","MAYBE LIAR"));
        setColor(Color.red);
    }

    // We alert our neighbors a link has been added
    @Override
    public void onLinkAdded(Link link){
        super.onLinkAdded(link);
        if(constructed){
            sendAll(new Message("", "MAYBE LIAR"));
            setColor(Color.red);
        }
    }

    @Override
    public String toString() {
        return "ID= "+ getID() + " hop= " + hop ;
    }
}


