
package bankmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PinChange extends JFrame implements ActionListener{
     
    JPasswordField t1,t2;
    JButton b1,b2;
    String pin;
    
    PinChange(String pin){
        this.pin = pin;
        
        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(0,0,900,900);
        add(l2);
        
        JLabel l1 = new JLabel("CHANGE YOUR PIN");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System",Font.BOLD, 16));
        l1.setBounds(250,280,500,35);
        l2.add(l1);
        
        JLabel l4 = new JLabel("NEW PIN");
        l4.setForeground(Color.WHITE);
        l4.setFont(new Font("System",Font.BOLD, 16));
        l4.setBounds(165,320,180,25);
        l2.add(l4);
        
        t1 = new JPasswordField();
        t1.setFont(new Font("Railway",Font.BOLD,25));
        t1.setBounds(330,320,180,25);
        l2.add(t1);
        
        JLabel l5 = new JLabel("Re-Enetr NEW PIN");
        l5.setForeground(Color.WHITE);
        l5.setFont(new Font("System",Font.BOLD, 16));
        l5.setBounds(165,360,180,25);
        l2.add(l5);
        
        t2 = new JPasswordField();
        t2.setFont(new Font("Railway",Font.BOLD,25));
        t2.setBounds(330,360,180,25);
        l2.add(t2);
        
        b1 = new JButton("CHANGE");
        b1.setBounds(355,485,150,30);
        b1.addActionListener(this);
        l2.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(355,520,150,30);
        b2.addActionListener(this);
        l2.add(b2);

        
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    
    }
    
    public void actionPerformed (ActionEvent ae){
        try{
            String npin = t1.getText();
            String rpin = t2.getText();
            if(!pin.equals(rpin)){
                JOptionPane.showMessageDialog(null,"Entered Pin does not match");
            }
            if (ae.getSource()==b1){
                if(t1.getText().equals("")){
                   JOptionPane.showMessageDialog(null,"Enter New Pin"); 
                }
                if(t2.getText().equals("")){
                   JOptionPane.showMessageDialog(null,"Re-Enter New Pin"); 
                    }
        
        Conn c1 = new Conn();
        String q1 = "update bank set pin = '"+pin+"' where pin = '"+pin+"' ";
        String q2 = "update login set pin = '"+pin+"' where pin = '"+pin+"' ";
        String q3 = "update signup3 set pin = '"+pin+"' where pin = '"+pin+"' ";
        c1.s.executeUpdate(q1);
        c1.s.executeUpdate(q2);
        c1.s.executeUpdate(q3);
        
        JOptionPane.showMessageDialog(null, "PIN changed successfully");
        setVisible(false);
        new Transactions(rpin).setVisible(true);
            }else if(ae.getSource()==b2){
                new Transactions(pin).setVisible(true);
                setVisible(false);
            }   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]){
    new PinChange("").setVisible(true);
    }
    
}
