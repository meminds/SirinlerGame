
public class Puan extends Oyuncu {

    public Puan(int OyuncuId, String OyuncuAdi, boolean OyuncuTur, int Skor, int x, int y) {
        super(OyuncuId, OyuncuAdi, OyuncuTur, Skor, x, y);
    }
    public Puan(){
        
    }

    @Override
    public int PuaniGoster(Oyuncu oyuncu) {
      
       return  oyuncu.PuaniGoster(oyuncu);
        
       
    }
    
    
}
