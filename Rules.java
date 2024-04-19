package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener{

    String name;
    int id;
    JButton start, back;
    
    Rules(String name,int id) {
        this.name = name;
        this.id = id;
//        Setting bg to white
        getContentPane().setBackground(new Color(230, 243, 255));
        setLayout(null);
        
//        welcome label with name
        JLabel heading = new JLabel("Welcome " + name + " to Brain Tickler");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        add(heading);
//       Rules displaying using label
        JLabel rules = new JLabel();
        rules.setBounds(20, 90, 700, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
            "<html>"+ 
                "1. You are trained to be a programmer and not a story teller, answer point to point" + "<br><br>" +
                "2. Do not unnecessarily smile at the person sitting next to you, they may also not know the answer" + "<br><br>" +
                "3. You may have lot of options in life but here all the questions are compulsory" + "<br><br>" +
                "4. Crying is allowed but please do so quietly." + "<br><br>" +
                "5. Only a fool asks and a wise answers (Be wise, not otherwise)" + "<br><br>" +
                "6. Do not get nervous if your friend is answering more questions, may be he/she is doing Jai Mata Di" + "<br><br>" +
                "7. Brace yourself, this paper is not for the faint hearted" + "<br><br>" +
                "8. May you know more than what John Snow knows, Good Luck" + "<br><br>" +
            "<html>"
        );
        add(rules);
     
//        Back button
        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        back.setBackground(new Color(0, 0, 0));
         back.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);
        
//        Start button
        start = new JButton("Start");
        start.setBounds(400, 500, 100, 30);
        start.setBackground(new Color(0, 0, 0));
         start.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5, true));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        add(start);
        
        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
//    Action Listener implementation
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name,id);
        } else {
            setVisible(false);
            new Login();
        }
    }
    
    public static void main(String[] args) {
        new Rules("User",0); // not mandatory to use this (only used for testing purpose
    }
}
