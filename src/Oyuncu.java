
public class Oyuncu extends Karakter {
    
    private int OyuncuId;
    private String OyuncuAdi;
    private boolean OyuncuTur;
    private int Skor=20;

    public Oyuncu(){
        super(1, "null", 0, 6*60, 5*60);
    }
    public Oyuncu(int OyuncuId, String OyuncuAdi, boolean OyuncuTur, int Skor, int x, int y) {
        super(1, OyuncuAdi, 0, 6*60, 5*60);
        this.OyuncuId = OyuncuId;
        this.OyuncuAdi = OyuncuAdi;
        this.OyuncuTur = OyuncuTur;
        this.Skor = Skor;
    }

    public int getOyuncuId() {
        return OyuncuId;
    }

    public void setOyuncuId(int OyuncuId) {
        this.OyuncuId = OyuncuId;
    }

    public String getOyuncuAdi() {
        return OyuncuAdi;
    }

    public void setOyuncuAdi(String OyuncuAdi) {
        this.OyuncuAdi = OyuncuAdi;
    }

    public boolean isOyuncuTur() {
        return OyuncuTur;
    }

    public void setOyuncuTur(boolean OyuncuTur) {
        this.OyuncuTur = OyuncuTur;
    }

    public int PuaniGoster(Oyuncu oyuncu) {
        return oyuncu.Skor;
    }
    public int getSkor(){
        return Skor;
    }
    public void setSkor(int Skor) {
        this.Skor = Skor;
    }

   
    

    
    
    
    
    
    
}
