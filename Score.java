package quiz.application;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


class UpdatingData
{
    UpdatingData(String name, int score,int id)
    {
//        connection details for a PostgreSQL database
        String url ="jdbc:postgresql://localhost:5432/GUItest";
        String uname="postgres";
        String pass="2004";
        try
        {
    //            establishing a connection
             Connection con = DriverManager.getConnection(url,uname,pass);
    //       String inputValues = "insert into student values(" + sid + ", '" + sname + "' ," + marks + ")";
            String inputData = "insert into studentt values(?,?,?)";
    //        Statement is used for execution of sql querys.
            PreparedStatement pst = con.prepareStatement(inputData);
            pst.setInt(1,id); // column number and value 
            pst.setString(2,name); 
            pst.setInt(3,score); 
            pst.execute();
             con.close();
        }catch(Exception e)
         {
                    System.out.println(e);
         }
        
    }
}


public class Score extends JFrame implements ActionListener 
{

       JButton exit,submit;
    Score(String name, int score,int id) {
        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/score.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 200, 300, 250);
        add(image);
        
        JLabel heading = new JLabel("Thankyou " + name + " for playing Simple Minds");
        heading.setBounds(45, 30, 700, 30);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(heading);
        
        JLabel lblscore = new JLabel("Your score is " + score);
        lblscore.setBounds(350, 200, 300, 30);
        lblscore.setFont(new Font("Tahoma", Font.PLAIN, 26));
        add(lblscore);
        
         submit = new JButton("Play Again");
        submit.setBounds(380, 270, 120, 30);
        submit.setBackground(new Color(0, 0, 0));
//          submit.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        add(submit);
        
          exit = new JButton("Exit");
        exit.setBounds(520, 270, 120, 30);
        exit.setBackground(new Color(30, 144, 255));
          exit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        exit.setForeground(Color.WHITE);
        exit.addActionListener(this);
        add(exit);
        
        new UpdatingData( name,score,id);
        
        setVisible(true);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == exit)
        {
            System.exit(0);
        }else if(ae.getSource() == submit)
        {
             setVisible(false);
             new Login();
        }      
    }

    public static void main(String[] args) 
    {
        new Score("User", 0,0);
    }
}
