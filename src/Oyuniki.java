
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Oyuniki extends JPanel implements KeyListener , ActionListener {
        Timer time = new Timer(10,this);
        Karakter karakter = new Oyuncu();
        Oyuncu puan = new Puan();
       
     

        private int atilan_adim=0;
        private float gecen_sure=0;
        private BufferedImage img;
        private BufferedImage imgBack;
        private BufferedImage goldImage;
        private BufferedImage mantarImage;
        private BufferedImage imgWhite;
        private BufferedImage gargamelimg;
        private BufferedImage azmanimg;
        private BufferedImage blueimg;
        int gargamelDefaultX;
        int gargamelDefaultY;
        int azmanDefaultX;
        int azmanDefaultY;
        Dusman gargamel;
        Dusman azman;
        char kapi;
        private int karakterXdir=60;
        private int karakterYdir=60;
        
        private ArrayList<Lokasyon> lokasyonlar = new ArrayList<Lokasyon>();
        private ArrayList<Altın> altınlar = new ArrayList<Altın>();
        private ArrayList<Mantar> mantarlar = new ArrayList<Mantar>();
         String[] line = new String[3];        // Secilecek dusman karakter dizisinin ve oyuna girecegi kapinin degerini tutar
        String[] maps = new String[11];       // 0-1 degerleri strin icerisinde dosayadan cekmek icin kullandik
        int[][] mapss = new int[11][13];     // harita.txt dosyasÄ±ndaki 0-1 degerleri bu dizinin icinde tutuyoruz
        
        
        
        public Oyuniki() throws IOException{
            //okuma yap
           
     
            dosyadanOku();
            
              for(int i = 0 ; i < 13; i++){
                  for(int j = 0 ; j <11 ; j++){
                      lokasyonlar.add(new Lokasyon(i*60,j*60,mapss[j][i]));
                      
                  }
            }
            
        try {
            img= ImageIO.read(new FileImageInputStream(new File("tembelsirin.jpg")));
            imgBack= ImageIO.read(new FileImageInputStream(new File("grey.jpg")));
            imgWhite= ImageIO.read(new FileImageInputStream(new File("whiteBack.png")));
            goldImage= ImageIO.read(new FileImageInputStream(new File("gold.png")));
            mantarImage= ImageIO.read(new FileImageInputStream(new File("mantar.png")));
            azmanimg= ImageIO.read(new FileImageInputStream(new File("azman.png")));
            gargamelimg= ImageIO.read(new FileImageInputStream(new File("gargamel.jpg")));
            blueimg= ImageIO.read(new FileImageInputStream(new File("blue.jpeg")));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        setBackground(Color.WHITE);
        time.start();
            
        }
        
      
        
    public void randomGold(){
        
   
        Random random = new Random();
        int xMax = 13;
        int yMax = 11;
       
        if(((gecen_sure/1000.0)%3)==0){
          if(!altınlar.isEmpty())  {
              altınlar.clear();
              return;
          }
          while(altınlar.size()<5){
             int xRandom=random.nextInt(xMax);
             int yRandom=random.nextInt(yMax);
             for(Lokasyon lokasyon:lokasyonlar){
                 if(lokasyon.getX()==xRandom*60 && lokasyon.getY()==yRandom*60){
                     if(lokasyon.getDolu()==0){
                         continue;
                     }
                     else{
                         altınlar.add(new Altın(xRandom,yRandom));
                     }
                     
                 }
                 
             }
             
             
          }   
          
             
             
                  
             
           }
  }    
    
    
    public void randomMantar(){
          
           Random random = new Random();
        int xMax = 13;
        int yMax = 11;
       
        if(((gecen_sure/1000.0)%5)==0){
          if(!mantarlar.isEmpty())  {
              mantarlar.clear();
              return;
          }
            while(mantarlar.size()<1){
             int xRandom=random.nextInt(xMax);
             int yRandom=random.nextInt(yMax);
             for(Lokasyon lokasyon:lokasyonlar){
                 if(lokasyon.getX()==xRandom*60 && lokasyon.getY()==yRandom*60){
                     if(lokasyon.getDolu()==0){
                         continue;
                     }
                     else{
                           mantarlar.add(new Mantar(xRandom,yRandom));
                     }
                 }
             }
             
            }
            
             
             
           }
          
     }
    
    public void kontrolEt(){
        
      
        if(karakter.getX()==12*60 && karakter.getY()==7*60){
              time.stop();
            String message= "Kazandınız\n"+
                            "Puanınız"+ puan.getSkor()+
                            "Geçen Süre "+gecen_sure/1000.0 + " saniye";
            JOptionPane.showMessageDialog(this, message);
            
            System.exit(0);
        }
        for(Altın altın: altınlar){
            if(altın.getX()*60==karakter.getX() && altın.getY()*60==karakter.getY()){
                puan.setSkor(puan.getSkor()+altın.getTaneDegeri());
                altınlar.remove(altın);
                
            }
            
        }
        
         for(Mantar mantar: mantarlar){
            if(mantar.getX()*60==karakter.getX() && mantar.getY()*60==karakter.getY()){
                puan.setSkor(puan.getSkor()+mantar.getTaneDegeri());
                mantarlar.remove(mantar);
            }
        }
        if(azman!=null){
            if(karakter.getX()==azman.getX() && karakter.getY()==azman.getY()){
                puan.setSkor(puan.getSkor()-5);
                azman.setX(azmanDefaultX);
                azman.setY(azmanDefaultY);
            }
        } 
        if(gargamel!=null){
            if(karakter.getX()==gargamel.getX() && karakter.getY()==gargamel.getY()){
                puan.setSkor(puan.getSkor()-15);
                gargamel.setX(gargamelDefaultX);
                gargamel.setY(gargamelDefaultY);
                
            }
        } 
         
         if(puan.getSkor()<0){
               time.stop();
            String message= "Kaybettiniz\n"+
                            "Puanınız "+ puan.getSkor()+
                            "Geçen Süre "+gecen_sure/1000.0 + " saniye";
            JOptionPane.showMessageDialog(this, message);
            
            System.exit(0);
           
         }
        
    }
    
    public void dosyadanOku() throws FileNotFoundException, IOException{
        
        File f = new File("harita.txt");        
        FileReader harita = new FileReader(f);
        BufferedReader br = new BufferedReader(harita);
        
       
        
        //****************************************************
        
           int n=0;
        line[n] = br.readLine();
        while(line[n].charAt(0) != '0' && line[n].charAt(0) != '1')
        {
            line[n] = line[n].toUpperCase();
            kapi = line[n].charAt(line[n].length()-1);
            //char kapi = line[n].substring(line[n].length()-1, line[n].length());
            if (line[n].contains("GARGAMEL")) {
                if (kapi=='A') {
                    gargamel = new Gargamel(3*60, 0*60);
                    gargamelDefaultX=3*60;
                    gargamelDefaultY=0*60;
                 
                } else if (kapi == 'B'){
                    gargamel = new Gargamel(10*60, 0*60);
                     gargamelDefaultX=10*60;
                    gargamelDefaultY=0*60;
           
                } else if (kapi == 'C'){
                    gargamel = new Gargamel(0*60, 5*60);
                     gargamelDefaultX=0*60;
                    gargamelDefaultY=5*60;
               
                } else if (kapi == 'D'){
                    gargamel = new Gargamel(3*60, 10*60);
                     gargamelDefaultX=3*60;
                    gargamelDefaultY=10*60;
               
                }
            }else{
                if (kapi=='A') {
                    azman = new Azman(3*60, 0*60);
                    azmanDefaultX=3*60;
                    azmanDefaultY=0*60;
                   
                } else if (kapi == 'B'){
                    azman = new Azman(10*60, 0*60);
                    azmanDefaultX=10*60;
                    azmanDefaultY=0*60;
                  
                } else if (kapi == 'C'){
                    azman = new Azman(0*60, 5*60);
                    azmanDefaultX=0*60;
                    azmanDefaultY=5*60;
                    
                } else if (kapi == 'D'){
                    azman = new Azman(3*60, 10*60);
                    azmanDefaultX=3*60;
                    azmanDefaultY=10*60;
                    
                }
            }
            n++;
            line[n] = br.readLine();
        }
        maps[0] = line[n];
        for (int i = 1; i < 11; i++) {     // 0-1 degerler olan satirlari cektik
            maps[i] = br.readLine();
        }
        
        for (int i = 0; i < 11; i++) {
            maps[i] = maps[i].replaceAll("\\s","");       // 0 ve 1 lerin arasindaki bosluklari kaldirdik
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 13; j++) {                    // string iÃ§inde olan 0-1 degerleri iki boyutlu int dizisinin icine atmak icin kullandÄ±k
                if (maps[i].charAt(j) == '0') {
                    mapss[i][j] = 0;
                }else{
                    mapss[i][j] = 1;
                }
            }
        }
        br.close();

      
      
            
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
         gecen_sure+=5;
         kontrolEt();
        
         // Lokasyon çizimi 
        
       for(Lokasyon lokasyon:lokasyonlar){
           if(lokasyon.getDolu()==0){
               g.drawImage(imgBack, lokasyon.getX(), lokasyon.getY(), this);
           }  
           else{
               g.drawImage(imgWhite, lokasyon.getX(), lokasyon.getY(), this);
           }
       }
        if(gargamel!=null){
             for (int i = 1; i < gargamel.Path_x.size()-1; i++) {
            g.drawImage(blueimg, gargamel.Path_x.get(i)*60, gargamel.Path_y.get(i)*60, this);
        }
      }
       
      if(azman!=null){
           for (int i = 1; i < azman.Path_x.size()-1; i++) {
            g.drawImage(blueimg, azman.Path_x.get(i)*60, azman.Path_y.get(i)*60, this);
        }
      }
         if (azman != null ) {
            g.drawImage(azmanimg, azman.getX(), azman.getY(),img.getWidth(),img.getHeight(), this);
          // azman.EnKisaYol(mapss, azman.getX()/60, azman.getY()/60, karakter.getX()/60, karakter.getY()/60);
        } 
        if (gargamel != null) {
            g.drawImage(gargamelimg, gargamel.getX(), gargamel.getY(),img.getWidth(),img.getHeight(), this);
         //   gargamel.EnKisaYol(mapss, gargamel.getX()/60, gargamel.getY()/60, karakter.getX()/60, karakter.getY()/60);
        }
       
        g.drawImage(img, karakter.getX(), karakter.getY(),img.getWidth(),img.getHeight(), this);
        for(Altın altın:altınlar){
            g.drawImage(goldImage, altın.getX()*60, altın.getY()*60, this);
        }
        for(Mantar mantar:mantarlar){
            g.drawImage(mantarImage, mantar.getX()*60, mantar.getY()*60, this);
        }
        
        
        
            
      
        
    
        
        
        
    }
    
    
        
    
    @Override
    public void repaint() {
        super.repaint();
    }
    
   
        
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
     
        if(pressed == KeyEvent.VK_RIGHT){
            for(Lokasyon lokasyon : lokasyonlar){
                if(lokasyon.getX()==karakter.getX()+karakterXdir && lokasyon.getY()==karakter.getY()){
                    if(lokasyon.getDolu()==1){
                 
                        karakter.setX(karakter.getX()+karakterXdir);
                      
                      if(gargamel!=null){
                              int kontrollerX=gargamel.getX();
                              int kontrollerY=gargamel.getY();
                               gargamel.setX(gargamel.Path_x.get(gargamel.Path_x.size()-3)*60);
                               gargamel.setY(gargamel.Path_y.get(gargamel.Path_y.size()-3)*60);
                             if(kontrollerX==gargamel.getX()&& kontrollerY==gargamel.getY()){
                             puan.setSkor(puan.getSkor()-15);
                             gargamel.setX(gargamelDefaultX);
                             gargamel.setY(gargamelDefaultY);
                        }
                        gargamel.Path_x.clear();
                        gargamel.Path_y.clear();
                       }
                    if(azman!=null){
                           
                         int azmanKontrollerX=azman.getX();
                        int azmanKontrollerY=azman.getY();
                       
                        azman.setX(azman.Path_x.get(azman.Path_x.size()-2)*60);
                        azman.setY(azman.Path_y.get(azman.Path_y.size()-2)*60);
                           if(azmanKontrollerX==azman.getX()&& azmanKontrollerY==azman.getY()){
                             puan.setSkor(puan.getSkor()-15);
                             azman.setX(azmanDefaultX);
                             azman.setY(azmanDefaultY);
                        }
                       azman.Path_x.clear();
                        azman.Path_y.clear();
                       }
                        
                   
                        return;
                    }
                   
                }
            }
        }
        if(pressed == KeyEvent.VK_LEFT){
             for(Lokasyon lokasyon : lokasyonlar){
                if(lokasyon.getX()==karakter.getX()-karakterXdir && lokasyon.getY()==karakter.getY()){
                    if(lokasyon.getDolu()==1){
                      
                        karakter.setX(karakter.getX()-karakterXdir);
                               
                      if(gargamel!=null){
                              int kontrollerX=gargamel.getX();
                              int kontrollerY=gargamel.getY();
                               gargamel.setX(gargamel.Path_x.get(gargamel.Path_x.size()-3)*60);
                               gargamel.setY(gargamel.Path_y.get(gargamel.Path_y.size()-3)*60);
                             if(kontrollerX==gargamel.getX()&& kontrollerY==gargamel.getY()){
                             puan.setSkor(puan.getSkor()-15);
                             gargamel.setX(gargamelDefaultX);
                             gargamel.setY(gargamelDefaultY);
                        }
                       gargamel.Path_x.clear();
                        gargamel.Path_y.clear();
                       }
                    if(azman!=null){
                           
                         int azmanKontrollerX=azman.getX();
                        int azmanKontrollerY=azman.getY();
                       
                        azman.setX(azman.Path_x.get(azman.Path_x.size()-2)*60);
                        azman.setY(azman.Path_y.get(azman.Path_y.size()-2)*60);
                           if(azmanKontrollerX==azman.getX()&& azmanKontrollerY==azman.getY()){
                             puan.setSkor(puan.getSkor()-15);
                             azman.setX(azmanDefaultX);
                             azman.setY(azmanDefaultY);
                        }
                        azman.Path_x.clear();
                        azman.Path_y.clear();
                       }
                        
                        return;
                    }
                    
                }
            }
            
        }
        if(pressed == KeyEvent.VK_DOWN){
             for(Lokasyon lokasyon : lokasyonlar){
                if(lokasyon.getX()==karakter.getX() && lokasyon.getY()==karakter.getY()+karakterYdir){
                    if(lokasyon.getDolu()==1){
                      
                        karakter.setY(karakter.getY()+karakterYdir);
                          
                      if(gargamel!=null){
                              int kontrollerX=gargamel.getX();
                              int kontrollerY=gargamel.getY();
                               gargamel.setX(gargamel.Path_x.get(gargamel.Path_x.size()-3)*60);
                               gargamel.setY(gargamel.Path_y.get(gargamel.Path_y.size()-3)*60);
                             if(kontrollerX==gargamel.getX()&& kontrollerY==gargamel.getY()){
                             puan.setSkor(puan.getSkor()-15);
                             gargamel.setX(gargamelDefaultX);
                             gargamel.setY(gargamelDefaultY);
                        }
                       gargamel.Path_x.clear();
                        gargamel.Path_y.clear();
                       }
                    if(azman!=null){
                           
                         int azmanKontrollerX=azman.getX();
                        int azmanKontrollerY=azman.getY();
                       
                        azman.setX(azman.Path_x.get(azman.Path_x.size()-2)*60);
                        azman.setY(azman.Path_y.get(azman.Path_y.size()-2)*60);
                           if(azmanKontrollerX==azman.getX()&& azmanKontrollerY==azman.getY()){
                             puan.setSkor(puan.getSkor()-15);
                             azman.setX(azmanDefaultX);
                             azman.setY(azmanDefaultY);
                        }
                        azman.Path_x.clear();
                        azman.Path_y.clear();
                       }
                 
                        return;
                    }
                    
                }
            }
            
            
        }
        if(pressed==KeyEvent.VK_UP){
            for(Lokasyon lokasyon : lokasyonlar){
                if(lokasyon.getX()==karakter.getX() && lokasyon.getY()==karakter.getY()-karakterYdir){
                    if(lokasyon.getDolu()==1){
                      
                       karakter.setY(karakter.getY()-karakterYdir);
                       
                           
                      if(gargamel!=null){
                              int kontrollerX=gargamel.getX();
                              int kontrollerY=gargamel.getY();
                               gargamel.setX(gargamel.Path_x.get(gargamel.Path_x.size()-3)*60);
                               gargamel.setY(gargamel.Path_y.get(gargamel.Path_y.size()-3)*60);
                             if(kontrollerX==gargamel.getX()&& kontrollerY==gargamel.getY()){
                             puan.setSkor(puan.getSkor()-15);
                             gargamel.setX(gargamelDefaultX);
                             gargamel.setY(gargamelDefaultY);
                        }
                      gargamel.Path_x.clear();
                       gargamel.Path_y.clear();
                       }
                    if(azman!=null){
                           
                         int azmanKontrollerX=azman.getX();
                        int azmanKontrollerY=azman.getY();
                       
                        azman.setX(azman.Path_x.get(azman.Path_x.size()-2)*60);
                        azman.setY(azman.Path_y.get(azman.Path_y.size()-2)*60);
                           if(azmanKontrollerX==azman.getX()&& azmanKontrollerY==azman.getY()){
                             puan.setSkor(puan.getSkor()-15);
                             azman.setX(azmanDefaultX);
                             azman.setY(azmanDefaultY);
                        }
                       azman.Path_x.clear();
                       azman.Path_y.clear();
                       }
                    
                        return;
                    }
                    
                }
            }
           
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //geçen süre hesaplayıp 5in katı olduğunda altın random oluştur
         if(azman!=null){
              // azman.Path_x.clear();
             //  azman.Path_y.clear();
              azman.EnKisaYol(mapss, azman.getX()/60, azman.getY()/60, karakter.getX()/60, karakter.getY()/60);
         }
         if(gargamel!=null){
           //  gargamel.Path_x.clear();
            //  gargamel.Path_y.clear();
              gargamel.EnKisaYol(mapss, gargamel.getX()/60, gargamel.getY()/60, karakter.getX()/60, karakter.getY()/60);
         }
       
        randomMantar();
        randomGold();
       
        repaint();
    }
    
}
