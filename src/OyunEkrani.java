
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;


class ChoosePage implements ActionListener{
         JFrame frame = new JFrame();
         JButton gozlukluButton = new JButton("Gözlüklü Şirin");
         JButton tembelButton = new JButton("Tembel Şirin");

    public ChoosePage() {
        gozlukluButton.setBounds(30, 160, 200, 200);
       gozlukluButton.setFocusable(false);
       gozlukluButton.addActionListener(this);
       tembelButton.setBounds(250, 160, 200, 200);
       tembelButton.setFocusable(false);
       tembelButton.addActionListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
 
        frame.add(gozlukluButton);
        frame.add(tembelButton);
               
        frame.setVisible(true);
    }
    
         
         
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==gozlukluButton){
         frame.dispose();
        OyunEkrani ekran=new OyunEkrani("Şirinler Oyunu-Gözlüklü");
        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(780,660);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Oyun oyun = null;
             try {
                 oyun = new Oyun();
             } catch (IOException ex) {
                 Logger.getLogger(ChoosePage.class.getName()).log(Level.SEVERE, null, ex);
             }
        //klavyeden input alabilmek için alttakiler bu sırada olmalı.
        oyun.requestFocus();
        //KeyListener classını oyunda implemente ettiğimiz için oyun objesiyle çağırıp içine oyun objesini verdik
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
       
        ekran.setVisible(true);
      }  
        
        if(e.getSource()==tembelButton){
         frame.dispose();
        OyunEkrani ekran=new OyunEkrani("Şirinler Oyunu-Tembel");
        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(780,660);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Oyuniki oyun = null;
             try {
                 oyun = new Oyuniki();
             } catch (IOException ex) {
                 Logger.getLogger(ChoosePage.class.getName()).log(Level.SEVERE, null, ex);
             }
        //klavyeden input alabilmek için alttakiler bu sırada olmalı.
        oyun.requestFocus();
        //KeyListener classını oyunda implemente ettiğimiz için oyun objesiyle çağırıp içine oyun objesini verdik
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
       
        ekran.setVisible(true);
      }  
        
        
        
    }
    
    
    
    
}


public  class OyunEkrani extends JFrame  {
    
      public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
      
       public static void main(String[] args) throws IOException {
        
       ChoosePage choose = new ChoosePage();
           
           /*
        OyunEkrani ekran=new OyunEkrani("Şirinler Oyunu");
        
        
       
        ekran.setResizable(false);
        ekran.setFocusable(false);
        
        ekran.setSize(780,660);
        
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*
        Oyun oyun = new Oyun();
        //klavyeden input alabilmek için alttakiler bu sırada olmalı.
        oyun.requestFocus();
        //KeyListener classını oyunda implemente ettiğimiz için oyun objesiyle çağırıp içine oyun objesini verdik
        oyun.addKeyListener(oyun);
        oyun.setFocusable(true);
        
        oyun.setFocusTraversalKeysEnabled(false);
        ekran.add(oyun);
       
        ekran.setVisible(true);*
        */
     
    }

  
    
}
