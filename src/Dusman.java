
public class Dusman extends Karakter {
    
    private int DusmanId;
    private String DusmanAdi;
    private int DusmanTur;

    public Dusman() {
     
    }

    public Dusman(int DusmanId, int DusmanTur, int ID, String Name, int Type, int x, int y) {
        super(ID, Name, Type, x, y);
        this.DusmanId = DusmanId;
        this.DusmanTur = DusmanTur;
    }

    public int getDusmanId() {
        return DusmanId;
    }

    public void setDusmanId(int DusmanId) {
        this.DusmanId = DusmanId;
    }

    public String getDusmanAdi() {
        return DusmanAdi;
    }

    public void setDusmanAdi(String DusmanAdi) {
        this.DusmanAdi = DusmanAdi;
    }

    public int getDusmanTur() {
        return DusmanTur;
    }

    public void setDusmanTur(int DusmanTur) {
        this.DusmanTur = DusmanTur;
    }
    
    
    
    
    
    
}
