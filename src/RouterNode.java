import io.jbotsim.core.Color;
import io.jbotsim.core.Link;
import io.jbotsim.core.Message;
import io.jbotsim.core.Node;
import io.jbotsim.ui.painting.JDirectedLinkPainter;

import java.util.ArrayList;
import java.util.List;

public class RouterNode extends Node {
    Node parent = null;
    List<Node> children = new ArrayList<>();
    private int hop = 0;
    Node sender;

    @Override
    public void onSelection() {
        parent = this;
        setIconSize(20);
        sendAll(new Message("1", "CONSTRUCTION"));
    }

    @Override
    public void onMessage(Message message) {
        sender = message.getSender();

        if (message.getFlag().equals("CONSTRUCTION")) {
            if (parent == null) {
                parent = message.getSender();
                getCommonLinkWith(parent).setWidth(4);
                send(parent, new Message("", "ADOPTION"));
                hop ++;
                sendAll(new Message(hop, "CONSTRUCTION"));

            }else{
                int t = (int) message.getContent();
                if(t<hop){
                    //Si un sommet à déjà un pére et que le nombre de saut (hop) est plus grand que content, on envoie ELIMINATION au pere
                    send(parent, new Message("", "ELIMINATION"));
                    getCommonLinkWith(parent).setWidth(1);

                    parent = message.getSender();

                    getCommonLinkWith(parent).setWidth(4);
                    hop ++;
                    send(parent, new Message("", "ADOPTION"));
                    sendAll(new Message(message));
                }
            }
        }else if (message.getFlag().equals("ADOPTION")) {
            children.add(message.getSender());
        }
        //On supprime notre ancien fils
        else if(message.getFlag().equals("ELIMINATION")){
            children.remove(message.getSender());
        }
        //Le sender à detecter un changement (une suppression), alors on envoie une MAJ à tous ses voisins
        else if(message.getFlag().equals("DELETE")){
            send(sender, new Message(hop, "UPDATE"));
        }
        // 1er cas : quand on supprime une arrete, on change de pere, et on prends le noeud le plus proche de la destination (hop le plus petit)
        else if (message.getFlag().equals("UPDATE")){
                if (hop >= (int) message.getContent()) {

                    if (getCommonLinkWith(parent) != null) {
                        getCommonLinkWith(parent).setWidth(1);
                    }

                    sendAll(new Message("","DELETE"));

                    parent = sender;
                    getCommonLinkWith(parent).setWidth(4);
                }
        }
    }

    @Override
    public void onLinkRemoved(Link link) {
        super.onLinkRemoved(link);
        sendAll(new Message("","DELETE"));
    }
}