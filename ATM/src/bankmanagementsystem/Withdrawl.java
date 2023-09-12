
package bankmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.ResultSet;
public class Withdrawl extends JFrame implements ActionListener{
    JTextField t1;
    JButton b1,b2;
    String pin;
    JLabel l1,l2,l3,l4;
    
    Withdrawl(String pin){
        this.pin = pin;
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l2 = new JLabel(i3);
        l2.setBounds(0,0,900,900);
        add(l2);
        
        l1 = new JLabel("MAXIMUM WITHDRAWL IS RS.10,000");
        l1.setFont(new Font("System",Font.BOLD,16));
        l1.setForeground(Color.WHITE);
        l1.setBounds(180,300,400,20);
        l2.add(l1);
        
        l3 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l3.setFont(new Font("System",Font.BOLD,16));
        l3.setForeground(Color.WHITE);
        l3.setBounds(180,370,400,20);
        l2.add(l3);
        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway",Font.BOLD,22));
        t1.setBounds(180,410,320,25);
        l2.add(t1);
        
        b1 = new JButton("Withdrawl");
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
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to withdraw");
            }else{
                
                Conn c1 = new Conn();
                ResultSet rs = c1.s.executeQuery("select * from bank where pin = '"+pin+"'");
                int balance = 0;
                while(rs.next()){
                    if(rs.getString("mode").equals("Deposit")){
                        balance +=Integer.parseInt(rs.getString("amount"));
                    }else {
                        balance -=Integer.parseInt(rs.getString("amount"));
                    }
                }if (balance<Integer.parseInt(amount)){
                   JOptionPane.showMessageDialog(null,"Insufficient Balance"); 
                   return;
                }
               c1.s.executeUpdate("INSERT INTO bank VALUES('" + pin + "','" + date + "','Withdrawal','" + amount + "')");
                
                JOptionPane.showMessageDialog(null,"Rs"+"Debited Successfully");
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
         }else if(ae.getSource()==b2){
            setVisible(false);
            new Transactions(pin).setVisible(true);
                 }
        }catch (Exception e){
             e.printStackTrace();
             System.out.println("error:"+e);
            }
    }
    public static void main(String args[]){
        new Withdrawl("").setVisible(true);
    }
}
