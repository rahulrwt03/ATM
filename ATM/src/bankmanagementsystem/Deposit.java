
package bankmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener{
    JTextField t1;
    JButton b1,b2;
    String pin;
    
    Deposit(String pin){
        this.pin = pin;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(0,0,900,900);
        add(l2);
        
        JLabel l1 = new JLabel("Enter the amount you want to deposit");
        l1.setFont(new Font("System",Font.BOLD,16));
        l1.setForeground(Color.WHITE);
        l1.setBounds(180,300,400,20);
        l2.add(l1);
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway",Font.BOLD,22));
        t1.setBounds(170,350,320,25);
        l2.add(t1);
        
        b1 = new JButton("Deposit");
        b1.setBounds(335,485,150,30);
        b1.addActionListener(this);
        l2.add(b1);
        
        b2 = new JButton("Back");
        b2.setBounds(335,520,150,30);
        b2.addActionListener(this);
        l2.add(b2);
        
        setSize(900,900);
        setLocation(300,0);
        setVisible(true);
    
    }
    public void actionPerformed(ActionEvent ae){
        try{
            String amount = t1.getText();
            java.util.Date utilDate = new java.util.Date();
java.sql.Date date = new java.sql.Date(utilDate.getTime());
        if (ae.getSource()== b1){
           if (t1.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit");
            }else{
                
                Conn c1 = new Conn();
                c1.s.executeUpdate("INSERT INTO bank VALUES('" + pin + "','" + date + "','Deposit','" + amount + "')");
                
                JOptionPane.showMessageDialog(null,"Rs"+"Deposited Successfully");
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
         }else if(ae.getSource()==b2){
            setVisible(false);
            new Transactions(pin).setVisible(true);
                 }
        }catch (Exception e){
             e.printStackTrace();
            }
    }
    public static void main(String args[]){
        new Deposit("").setVisible(true);
    }
}
