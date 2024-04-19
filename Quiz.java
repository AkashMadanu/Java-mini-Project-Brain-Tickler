package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Quiz extends JFrame implements ActionListener {
    
    String questions[][] = new String[10][5]; // total of 10 questions (2d array)(1 questoin + 4 options = 5)
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1]; // to store the 10 user answers
    
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions; // to choose only one option
    JButton next, submit, lifeline;
    
    public static int timer = 15;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0; // score count of the user
    
    String name; // declaring name for later use out of constructor 
    int id;
    Quiz(String name, int id) {
        this.name = name;
        this.id = id;
        setBounds(100, 0, 1200, 800);
            getContentPane().setBackground(new Color(230, 243, 255));
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quiz.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1200, 392);
        add(image);
        
//        setting the question numbers (location)
        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        add(qno);
        
//        setting the question data location
        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        add(question);
        
        
//        Questions and Options
        questions[0][0] = "What is a group of cats called? ";
        questions[0][1] = "A clowder";
        questions[0][2] = "A purrliament";
        questions[0][3] = "A meowtropolis";
        questions[0][4] = "A fur-nado";

        questions[1][0] = "How do you make a tissue dance?";
        questions[1][1] = "Put on some music";
        questions[1][2] = "Add some salsa sauce";
        questions[1][3] = "Tickle it";
        questions[1][4] = "Tell it a joke";

        questions[2][0] = "What do you call fake spaghetti?";
        questions[2][1] = "Impasta";
        questions[2][2] = "Spagh-phony";
        questions[2][3] = "Fauxghetti";
        questions[2][4] = "Counterfeit-carbs";

        questions[3][0] = "What did one ocean say to the other ocean?";
        questions[3][1] = "Nothing, they just waved";
        questions[3][2] = "You're making waves!";
        questions[3][3] = "I'm feeling a bit salty today";
        questions[3][4] = "Let's have a tide race!";

        questions[4][0] = "How does a penguin build its house?";
        questions[4][1] = "Igloos it together";
        questions[4][2] = "Glues it with fish";
        questions[4][3] = "Ice-olates itself";
        questions[4][4] = "By flapping its wings until it's built";

        questions[5][0] = "Why don't scientists trust atoms?";
        questions[5][1] = "They're too small to see";
        questions[5][2] = "They make up everything";
        questions[5][3] = "They're always splitting";
        questions[5][4] = "They have too many electrons";

        questions[6][0] = "What do you call a snowman with a six-pack?";
        questions[6][1] = "Frosty the Abs-man";
        questions[6][2] = " Iceberg Hercules";
        questions[6][3] = "The Abominable Coreman";
        questions[6][4] = "Mr. Freeze and Fit";

        questions[7][0] = "Why did the tomato turn red?";
        questions[7][1] = "Because it saw the salad dressing";
        questions[7][2] = "Because it was embarrassed";
        questions[7][3] = "Because it was blushing";
        questions[7][4] = "Because it was sun-kissed";

        questions[8][0] = "What did one hat say to the other hat?";
        questions[8][1] = "You stay here, I'll go on ahead";
        questions[8][2] = "You're looking cap-tivating!";
        questions[8][3] = "I've got you covered";
        questions[8][4] = "Let's stay ahead in style";

        questions[9][0] = "Why couldn't the bicycle stand up by itself?";
        questions[9][1] = " It was too tired";
        questions[9][2] = "It was two-tired";
        questions[9][3] = "It needed a break";
        questions[9][4] = "It lost its balance";
        
        answers[0][1] = "A clowder";
        answers[1][1] = "Put on some music";
        answers[2][1] = "Impasta";
        answers[3][1] = "Nothing, they just waved";
        answers[4][1] = "Igloos it together";
        answers[5][1] = "They make up everything";
        answers[6][1] = "Frosty the Abs-man";
        answers[7][1] = "Because it saw the salad dressing";
        answers[8][1] = "You stay here, I'll go on ahead";
        answers[9][1] = "It was two-tired";
        
        opt1 = new JRadioButton();
        opt1.setBounds(170, 520, 300, 30);
        opt1.setBackground(new Color(230, 243, 255));
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt1);
        
        opt2 = new JRadioButton();
        opt2.setBounds(170, 560, 300, 30);
        opt2.setBackground(new Color(230, 243, 255));
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt2);
        
        opt3 = new JRadioButton();
        opt3.setBounds(170, 600, 300, 30);
        opt3.setBackground(new Color(230, 243, 255));
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt3);
        
        opt4 = new JRadioButton();
        opt4.setBounds(170, 640, 300, 30);
        opt4.setBackground(new Color(230, 243, 255));
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(opt4);
        
        
//        Grouping of options
        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);
        
//        Next button
        next = new JButton("Next");
        next.setBounds(900, 580, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
         next.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        next.setBackground(new Color(30, 144, 254));
        
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);
        
//        50-50 Lifeline button
        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(900, 630, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 22));
      lifeline.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        lifeline.setBackground(new Color(30, 144, 254));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);
        add(lifeline);
        
//       Submit button
        submit = new JButton("Submit");
        submit.setBounds(900, 680, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        submit.setBackground(new Color(30, 144, 254));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        add(submit);
        
        start(count);
        
        setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next)  // if user clicked next
        {
            repaint(); // to request the repainting of the graphical components. eg: updating the timer display
//            During 50-50 lifeline we diable options to make sure all options are enabled for the next question
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            
            
            ans_given = 1;
            if (groupoptions.getSelection() == null) // if no options selected
            {
               useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }
            
            if (count == 8) //last question
            {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            
            count++; // incrementing the question counter
            start(count);
        } else if (ae.getSource() == lifeline) //lifeline button
        {
            if (count == 2 || count == 4 || count == 6 || count == 8 || count == 9) {
                opt2.setEnabled(false);
                opt3.setEnabled(false);
            } else {
                opt1.setEnabled(false);
                opt4.setEnabled(false);
            }
            lifeline.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
        lifeline.setBackground(new Color(217, 217, 217));
        lifeline.setForeground(Color.WHITE);
            lifeline.setEnabled(false); //one use only
            
        } else if (ae.getSource() == submit) 
        {
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand(); // returns the sring and stores
            }

            for (int i = 0; i < useranswers.length; i++) {
//                Score calculation
                if (useranswers[i][0].equals(answers[i][1])) {
                    score += 10;
                } else {
                    score += 0;
                }
            }
            setVisible(false);
            new Score(name, score,id);
        }
    }
    
    /*
    the graphical representation of the quiz, including the countdown 
    timer, user interactions, and transitions between questions.
    */
//    contious invocation of paint method
    public void paint(Graphics g) {
        super.paint(g);
        
        String time = "Time left - " + timer + " seconds"; // 15
        g.setColor(Color.WHITE);
        g.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        if (timer > 0) { 
            g.drawString(time, 900, 70); // displays time at location
        } else {
            g.drawString("Times up!!", 900, 70); // if time is up 
        }
        
        timer--; // decrementing time
        
        try {
            Thread.sleep(1000); //pausing execution for 1sec
            repaint(); // to update the timer every second
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (ans_given == 1) // If the user has given an answer
        {
            ans_given = 0;// Reset the answer flag
            timer = 15;
        } else if (timer < 0) { // automatically changes to next question 
            timer = 15;
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            
            if (count == 8) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            if (count == 9) { // submit button
                if (groupoptions.getSelection() == null) {
                   useranswers[count][0] = "";
                } else {
                    useranswers[count][0] = groupoptions.getSelection().getActionCommand();
                }
                
                for (int i = 0; i < useranswers.length; i++) {
                    if (useranswers[i][0].equals(answers[i][1])) {
                        score += 10;
                    } else {
                        score += 0;
                    }
                }
                setVisible(false);
                new Score(name, score,id);
            } else { // next button (if user selects nothing and time is up)
                if (groupoptions.getSelection() == null) {
                   useranswers[count][0] = "";
                } else {
                    useranswers[count][0] = groupoptions.getSelection().getActionCommand();
                }
                count++; // 0 // 1
                start(count);
            }
        }
        
    }
    
    public void start(int count) 
    {
        qno.setText("" + (count + 1) + ". "); //setting question number
        question.setText(questions[count][0]); // setting question
        opt1.setText(questions[count][1]); // displaying option
        opt1.setActionCommand(questions[count][1]); // to identify the selected option
        
        opt2.setText(questions[count][2]);
        opt2.setActionCommand(questions[count][2]);
        
        opt3.setText(questions[count][3]);
        opt3.setActionCommand(questions[count][3]);
        
        opt4.setText(questions[count][4]);
        opt4.setActionCommand(questions[count][4]);
        
        groupoptions.clearSelection();
    }
    
    public static void main(String[] args) 
    {
        new Quiz("User",0);
    }
}
