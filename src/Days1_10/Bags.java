package Days1_10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Bags {
    public static void main(String[] args) throws FileNotFoundException {
        //First part
        //Map<String, ArrayList<String>> bagMap = createMap("bags.txt");
        //for(String k : bagMap.keySet()) System.out.println(k + ":" + bagMap.get(k));
        //int count = find((HashMap) bagMap);
        //System.out.println(count);

        //Second part
        HashMap<String, ArrayList<String>> bagMap = createMapWithNumbers("bags.txt");
        System.out.println("total map size: " + bagMap.size());

        BagTreeNode root = createBagTree(bagMap);
        int size = root.size();
        System.out.println("total tree size: " + size);

        int num = countBagsWithRoot(root) - 1;
        System.out.println(num);

        String s = new String("😢😥😥");
        System.out.print(s);
    }

    /**
     * Part 2
     */
    public static int countBagsWithRoot(BagTreeNode root) {
        if(root.getChildren().isEmpty()) {
            return 1;
        }

        int count = 0;
        for(BagTreeNode child : root.getChildren().keySet()) {
            count += root.getChildren().get(child) * countBagsWithRoot(child);
        }

        return ++count;
    }

    /**
     * Note: this is not at all very effective method,
     * since it creates a new BagtreeNode each time even if it has the same name and same children
     */
    public static BagTreeNode createBagTree(HashMap<String, ArrayList<String>> bagMap) {
        //create a new tree with "shiny gold as the root and the bags it contains as its children"
        ArrayList<String> c = bagMap.get("shiny gold"); //the bags it contains
        HashMap<BagTreeNode, Integer> children = new HashMap<>();
        for(String s : c) children.put(new BagTreeNode(s.substring(2)), Integer.parseInt(s.substring(0,1)));
        BagTreeNode root = new BagTreeNode("shiny gold");
        root.setChildren(children);

        //bagQueue with tree nodes waiting to get their children
        Queue<BagTreeNode> bagQueue = new LinkedList<>(children.keySet());

        while(!bagQueue.isEmpty()) {
            BagTreeNode node = bagQueue.remove();
            String name = node.getName();
            ArrayList<String> childrenBags = bagMap.get(name);
            HashMap<BagTreeNode, Integer> currChildren = new HashMap<>();
            for(String bag : childrenBags) {
                BagTreeNode newNode = new BagTreeNode(bag.substring(2));
                currChildren.put(newNode, Integer.parseInt(bag.substring(0, 1)));
                bagQueue.add(newNode);
            }
            node.setChildren(currChildren);
        }

        //System.out.println(root.toString());
        return root;
    }

    public static BagTreeNode findBagTreeNode(BagTreeNode root, String name) {
        if(root.getName().equals(name)) return root;
        if(root.getChildren().isEmpty()) return null;

        BagTreeNode current = root;
        for(BagTreeNode node : root.getChildren().keySet()) {
            current = findBagTreeNode(node, name);
            if(current == null) continue;
            if(current.getName().equals(name)) return current;
        }
        return null;
    }



    public static HashMap<String, ArrayList<String>> createMapWithNumbers(String input) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(input));
        HashMap<String, ArrayList<String>> bags = new HashMap<>();

        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" contain ");
            String bag = line[0].substring(0, line[0].indexOf("bags") - 1);
            ArrayList<String> bagList = new ArrayList<>();

            if(line[1].contains("no other bags")) bags.put(bag, bagList);
            else {
                String[] otherBags = line[1].split(", ");

                for (String b : otherBags) {
                    bagList.add(b.substring(0, b.indexOf("bag") - 1));
                }
                if (!bags.containsKey(bag)) bags.put(bag, bagList);
                else {
                    bags.get(bag).addAll(bagList);
                    bags.put(bag, bags.get(bag));
                }
            }
        }
        for(String k : bags.keySet()) System.out.println(k + ":" + bags.get(k));

        return bags;
    }



    /**
     * Part 1
     */
    public static int find(HashMap<String, ArrayList<String>> bagMap) {
        List<String> bagList = new ArrayList<>();

        //bags that directly contain shiny gold bag
        for(String k : bagMap.keySet()) {
            if(bagMap.get(k).contains("shiny gold")) bagList.add(k);
        }
        //System.out.println(bagList);

        for(int i = 0; i < bagMap.size(); i++) {
            for(String bag : bagMap.keySet()) {
                if(!bagList.contains(bag)) {
                    boolean connects = false;
                    for(String bagValue : bagMap.get(bag)) {
                        if(bagList.contains(bagValue)) connects = true;
                    }
                    if(connects) bagList.add(bag);
                }
            }
        }
        System.out.println(bagList);
        return bagList.size();
    }


    public static HashMap<String, ArrayList<String>> createMap(String input) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(input));
        HashMap<String, ArrayList<String>> bags = new HashMap<>();

        while(sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" contain ");
            String bag = line[0].substring(0, line[0].indexOf("bags") - 1);
            ArrayList<String> bagList = new ArrayList<>();

            if(line[1].contains("no other bags")) bags.put(bag, bagList);
            else {
                String[] otherBags = line[1].split(", ");

                for (String b : otherBags) {
                    bagList.add(b.substring(2, b.indexOf("bag") - 1));
                }
                if (!bags.containsKey(bag)) bags.put(bag, bagList);
                else {
                    bags.get(bag).addAll(bagList);
                    bags.put(bag, bags.get(bag));
                }
            }
        }
        //System.out.println(bags);
        return bags;
    }

    //Sample inputs
    /*String input = "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags\n" +
                "faded blue bags contain no other bags.\n" +
                "dotted black bags contain no other bags.\n" +
                "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.\n" +
                "dark olive bags contain 3 faded blue bags, 4 dotted black bags.";*/
        /*String input = "shiny gold bags contain 2 dark red bags.\n" +
                "dark red bags contain 2 dark orange bags.\n" +
                "dark orange bags contain 2 dark yellow bags.\n" +
                "dark yellow bags contain 2 dark green bags.\n" +
                "dark green bags contain 2 dark blue bags.\n" +
                "dark blue bags contain 2 dark violet bags.\n" +
                "dark violet bags contain no other bags.";*/
}
