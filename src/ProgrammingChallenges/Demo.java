package ProgrammingChallenges;

public class Demo {
    public static void main(String[] args) {
        int[] rootValue = new int[]{22};
        MultiwaySearchTree[] rootChildren = new MultiwaySearchTree[2];
        rootChildren[0] = new MultiwaySearchTree(new int[]{5,10},
                new MultiwaySearchTree[]{new MultiwaySearchTree(new int[]{3, 4}, new MultiwaySearchTree[3]),
                        new MultiwaySearchTree(new int[]{6, 8}, new MultiwaySearchTree[3]),
                        new MultiwaySearchTree(new int[]{14},
                                new MultiwaySearchTree[]{new MultiwaySearchTree(new int[]{11, 13}, new MultiwaySearchTree[3]), new MultiwaySearchTree(new int[]{17}, new MultiwaySearchTree[2])})
                });
        rootChildren[1] = new MultiwaySearchTree(new int[]{25},
                new MultiwaySearchTree[]{new MultiwaySearchTree(new int[]{23,24}, new MultiwaySearchTree[3]),
                new MultiwaySearchTree(new int[]{27}, new MultiwaySearchTree[2])});
        MultiwaySearchTree tree = new MultiwaySearchTree(rootValue, rootChildren);

        //test
        System.out.println(satisfiesCondition4(tree));
    }

    public static boolean satisfiesCondition4(MultiwaySearchTree tree) {
        return inorder(tree, new int[]{Integer.MIN_VALUE});
    }

    private static boolean inorder(MultiwaySearchTree tree, int[] prev) {
        boolean valid = true;
        if(tree.getChildren().length - tree.getKeys().length != 1) return false;
        if(isLeaf(tree)) {
            for(int key : tree.getKeys()) {
                if(key <= prev[0]) return false;
                prev[0] = key;
            }
        } else {
            //if(countChildren(tree) - tree.getKeys().length != 1) return false;

            int pos = 0;
            MultiwaySearchTree curr = tree.getChildren()[pos++];
            valid = inorder(curr, prev);
            if(!valid) return false;

            for(int key : tree.getKeys()) {
                if(key <= prev[0]) return false;
                prev[0] = key;

                curr = tree.getChildren()[pos++];
                valid = inorder(curr, prev);
                if(!valid) return false;
            }
        }
        return valid;
    }

    private static int countChildren(MultiwaySearchTree tree) {
        int count = 0;
        for(MultiwaySearchTree t : tree.getChildren()) if(t != null) count++;
        return count;
    }

    private static boolean isLeaf(MultiwaySearchTree tree) {
        int nulls = 0;
        for(MultiwaySearchTree t : tree.getChildren()) {
            if(t == null) nulls++;
        }
        return nulls == tree.getChildren().length;
    }
}
