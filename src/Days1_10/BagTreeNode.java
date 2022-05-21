package Days1_10;

import java.util.HashMap;

public class BagTreeNode implements Comparable{
    String name;
    HashMap<BagTreeNode, Integer> children;

    public BagTreeNode(String name) {
        this.name = name;
        this.children = new HashMap<>();
    }

    public HashMap<BagTreeNode, Integer> getChildren() {
        return this.children;
    }

    public String getName() {return this.name;}

    public void setChildren(HashMap<BagTreeNode, Integer> newChildren) {
        this.children = newChildren;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.name);
        if(this.children.size() > 0) sb.append("\n");

        for(BagTreeNode node : this.children.keySet()) sb.append("<" + this.children.get(node) + node.toString()  + ">\n");

        if(this.children.size() > 0) sb.append("\n");
        return sb.toString();
    }

    public int size() {
        if(this.children.size() == 0) return 1;

        int count = 0;
        for(BagTreeNode node : this.children.keySet()) {
            count += node.size();
        }
        return ++count;
    }

    @Override
    public int compareTo(Object o) {
        BagTreeNode other = (BagTreeNode) o;
        return Integer.compare(this.getChildren().size(), other.getChildren().size());
    }
}
