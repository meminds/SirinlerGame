
public class Lokasyon {
    
    private int x ;
    private int y ;
    private int dolu;

  

    public Lokasyon(int x, int y, int dolu) {
        this.x = x;
        this.y = y;
        this.dolu = dolu;
    }
    
 
    
    public Lokasyon() {
    }
    
    public Lokasyon(int x, int y) {
        this.x = x;
        this.y = y;
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
    
      public int getDolu() {
        return dolu;
    }

    public void setDolu(int dolu) {
        this.dolu = dolu;
    }
    
}
