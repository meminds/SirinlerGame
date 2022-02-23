
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class Dugum
{
    int x,y,dist;
    
    Dugum parent;
    Dugum(int x,int y,int dist, Dugum parent)
    {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.parent = parent;
    }
    public String toString()
    {
        return "{" + x + "," + y + "}";
    }
};

public class Karakter {
    private static final int M = 11;
    private static final int N = 13;
    
    private static final int row[] = { -1, 0, 0, 1 };
    private static final int col[] = { 0, -1, 1, 0 };
    
    private int ID;
    private String Name;
    // 0 Oyuncu - 1 Düşman 
    private int Type;
    private int x;
    private int y;
     ArrayList<Integer> Path_x = new ArrayList<Integer>();
     ArrayList<Integer> Path_y = new ArrayList<Integer>();
    
    
    public Karakter(){
        
    }
    
    public Karakter(int ID, String Name, int Type, int x, int y) {
        this.ID = ID;
        this.Name = Name;
        this.Type = Type;
        this.x = x;
        this.y = y;
    }
    
    private static boolean isValid(int mat[][], boolean visited[][], int row, int col)
    {
        return (row >= 0) && (row < M) && (col >= 0) && (col < N) && mat[row][col] == 1 
                && !visited[row][col];
    }
    private  void BFS (int mat[][], int i, int j, int x, int y)
    {
        boolean[][] visited = new boolean[M][N];
        Queue<Dugum> q = new ArrayDeque<>();
        visited[i][j] = true;
        q.add(new Dugum(i, j, 0, null));
        int min_dist = Integer.MAX_VALUE;
        Dugum dugum = null;
        while (!q.isEmpty())
        {
            dugum = q.poll();
            i = dugum.x;
            j = dugum.y;
            int dist = dugum.dist;
            if (i == x && j == y) {
                min_dist = dist;
                break;
            }
            for (int k = 0; k < 4; k++) {
                if (isValid(mat, visited, i + row[k], j + col[k])) {
                    visited[i + row[k]][j + col[k]] = true;
                    q.add(new Dugum(i + row[k], j + col[k], dist + 1, dugum));
                } 
            }
        }
        if (min_dist != Integer.MAX_VALUE) {
            //System.out.println("The shortest path from source to destination " + "has lenght " + min_dist);
            printPath(dugum);
        }else{
            //System.out.println("Destination can't be reached from source ");
        }
    }
    
    private  void printPath(Dugum dugum)
    {
        if (dugum == null) {
            return;
        }   
        Path_x.add(dugum.y);
        Path_y.add(dugum.x);
        printPath(dugum.parent);
        //System.out.println(dugum);
        
    }
    
    // En Kısa Yol Algoritması .
    public void EnKisaYol(int[][] mapss, int dusmanx, int dusmany, int oyuncux, int oyuncuy){
        
        BFS(mapss, dusmany, dusmanx, oyuncuy, oyuncux);

    }
    
    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    
    
    
    
    
    
}
