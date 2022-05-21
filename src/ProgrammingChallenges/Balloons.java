package ProgrammingChallenges;

import java.util.*;

public class Balloons {
    //simple class to store the coordinates + the height of the cell
    static class Coord {
        int i, j, h;
        public Coord(int i, int j, int h) {
            this.i = i;
            this.j = j;
            this.h = h;
        }
    }

    //max priority queue to store heights that will be processed in bfs
    public static Queue<Coord> pq = new PriorityQueue<>((c1,c2) -> {
        if(c1.h == c2.h) return 0;
        return (c1.h < c2.h) ? 1 : -1;
    });

    //length and width of the tent
    public static int w,l;
    //array for heights
    public static int[][] heights;
    //array for coordinates and values of the cells
    public static Coord[][] coords;
    //array to keep track of the cells we have visited(added in the pq)
    public static boolean[][] vis;
    //change in i
    int di[] = {0,1,0,-1};
    //change in j
    int dj[] = {-1,0,1,0};

    public static void main(String[] args) {
        String input = "4 5\n" +
                "1 2 3 2\n" +
                "2 3 2 3\n" +
                "2 4 2 2\n" +
                "3 2 5 2\n" +
                "2 3 2 1";
        Balloons b = new Balloons();
        System.out.println(b.solve(input));
    }

    private int solve(String input) {
        Scanner sc = new Scanner(input);
        w = sc.nextInt();
        l = sc.nextInt();

        coords = new Coord[l][w];
        heights = new  int[l][w];
        vis = new boolean[l][w];

        //iterate over the given input. Initialise coords and heights arrays.
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < w; j++) {
                int h = sc.nextInt();
                coords[i][j] = new Coord(i, j, h);
                heights[i][j] = h;

                //if this is edge/border, put in pq and mark as visited
                if(i == 0 || i == l-1 || j == 0 || j == w-1) {
                    vis[i][j] = true;
                    pq.add(coords[i][j]);
                }
            }
        }

        //perform bfs on each coordinate in the pq
        while(!pq.isEmpty()) bfs(pq.poll());

        //calculate the area
        int area = 0;
        for(int i = 0; i < l; i++) {
            for(int j = 0; j < w; j++) {
                area += coords[i][j].h - heights[i][j];
            }
        }
        return area;
    }

   //check if the position is valid (within array bounds)
   public static boolean valid(int x, int y) { return (x >= 0 && y >= 0 && x < l && y < w);}

   //bfs based on the given coordinate
   public void bfs(Coord curr){
        //queue for bfs
        Queue<Coord> queue1 = new LinkedList<>();
        queue1.add(curr);

        while(!queue1.isEmpty()) {
            Coord t = queue1.remove();
            //take the i,j of the coordinate being processed
            int i = t.i;
            int j = t.j;
            //the height of the current coordinate (current max height)
            heights[i][j] = curr.h;

            //check all neighbours (all possible coordinates based on di and dj values)
            for(int k = 0; k < 4; k++) {
                int newI = i + di[k];
                int newJ = j + dj[k];

                //if the coordinate has already been visited or is just invalid, skip
                if(!valid(newI, newJ) || vis[newI][newJ]) continue;
                //mark current coordinate as visited
                vis[newI][newJ] = true;

                //if the height of the processed coordinate is ge current max height, store it for further bfs
                if(coords[newI][newJ].h >= curr.h) queue1.add(new Coord(newI,newJ, coords[newI][newJ].h));
                //otherwise (smaller) put it into the pq
                else pq.add(new Coord(newI,newJ, coords[newI][newJ].h));
            }
        }
   }
}
