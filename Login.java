package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;



public class Login extends JFrame implements ActionListener{
 
    JButton rules, back,previousPlayers;
    JTextField tfname,tfid;
    
    Login() {
        getContentPane().setBackground(new Color(230, 243, 255));
        setLayout(null);
        // Quiz Icon 
  
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(5, 0, 600, 500);
        add(image);
        
        
        
//        Heading
        JLabel heading = new JLabel("Brain Tickler");
        heading.setBounds(750, 60, 300, 45);
        heading.setFont(new Font("Times New Roman", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
        
        
//        Name label
        JLabel name = new JLabel("Enter your Name");
        name.setBounds(735, 150, 300, 20);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        name.setForeground(new Color(0, 55, 117));
        add(name);
        
//        Text Field to eneter name
        tfname = new JTextField();
        tfname.setBounds(735, 200, 300, 25);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfname);
 // ID TF       
    JLabel id = new JLabel("Enter your ID");
        id.setBounds(735, 250, 300, 20);
        id.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        id.setForeground(new Color(0, 55, 117));
        add(id);
        
   // Text Field to enter ID
        tfid = new JTextField();
        tfid.setBounds(735, 300, 300, 25);
        tfid.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfid);
        
        rules = new JButton("Rules");
        rules.setBounds(735, 350, 120, 25);
        rules.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        rules.setBackground(new Color(30, 144, 254));
        rules.setForeground(Color.WHITE);
        rules.addActionListener(this);
        add(rules);
        
        back = new JButton("Exit");
        back.setBounds(915, 350, 120, 25);
        back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.BLACK);
        back.addActionListener(this);
        add(back);
        
        previousPlayers = new JButton("View Previous Players");
        previousPlayers.setBounds(795, 400, 200, 25);
        previousPlayers.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        previousPlayers.setBackground(new Color(30, 144, 254));
        previousPlayers.setForeground(Color.WHITE);
        previousPlayers.addActionListener(this);
        
        add(previousPlayers);
        
        setSize(1200, 535);
       
        setLocation(200, 150);
        setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {
             if (tfname.getText().isEmpty() || tfid.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Name and ID before proceeding!");
            } else {
                // If fields are not empty, proceed to the next screen
                String name = tfname.getText();
                int id = Integer.parseInt(tfid.getText());
                setVisible(false);
                new Rules(name, id);
            }
           
        } else if (ae.getSource() == back) {
              int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (response == JOptionPane.YES_OPTION) {
                setVisible(false);
                dispose(); // Dispose of the frame and exit the application
            }
        }
        if(ae.getSource()== previousPlayers )
        {
            
            String url = "jdbc:postgresql://localhost:5432/GUItest";
            String uname ="postgres";
            String pass="2004";

            String query = "select * from studentt";
            try
            {
//                Connecting to db
                Connection con = DriverManager.getConnection(url,uname,pass);
                System.out.println("Connection Established!");

                Statement st= con.createStatement();
                ResultSet rs = st.executeQuery(query);
                DBTable p1= new DBTable(rs); 
                 con.close();
            }catch(Exception e)
            {
                System.out.println(e);
            }


                setVisible(false);
                System.out.println("Player Button Clicked!");

        }
    }
    
    public static void main(String[] args) {
      new Login();
    }
}



