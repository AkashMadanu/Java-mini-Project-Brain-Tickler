
package quiz.application;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import java.util.logging.*;
import javax.swing.table.DefaultTableModel;


class DBTable extends JFrame implements  ActionListener 
{
    JButton playAndBeatAbove,search;
    DBTable(ResultSet rs) throws SQLException 
    {
         getContentPane().setBackground(new Color(230, 243, 255));
        setSize(400,500);
        JPanel p = new JPanel();
        p.setLayout(null);
        add(p);
        
        JLabel l = new JLabel("Players Database!");
        l.setBounds(120,20,150,30);
        p.add(l);
        
       DefaultTableModel  model = new DefaultTableModel();
       model.setColumnIdentifiers(new String[]{"ID","Name","Score"});

       while(rs.next())
       {
           String[] row = {
               String.valueOf(rs.getInt("sid")),
               rs.getString("sname"),
               String.valueOf(rs.getInt("score"))
           };
           model.addRow(row);
       }
        JTable t = new JTable(model);
        
        JScrollPane sp = new JScrollPane(t);
        sp.setBounds(5,60,370,300);
        p.add(sp);
        
          playAndBeatAbove = new JButton("Play ");
        playAndBeatAbove.setBounds(50, 400, 100, 25);
        playAndBeatAbove.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        playAndBeatAbove.setBackground(new Color(30, 144, 254));
        playAndBeatAbove.setForeground(Color.WHITE);
        playAndBeatAbove.addActionListener(this);
        p.add(playAndBeatAbove);
        
          search = new JButton("Search Player");
        search.setBounds(250, 400, 100, 25);
        search.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
        search.setBackground(new Color(30, 144, 254));
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        p.add(search);
        
       
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() ==playAndBeatAbove )
        {
            new Login();
        }
        
        if(e.getSource() == search)
        {
            new Search();
        }
    }
    
}

class Search extends JFrame implements ActionListener
{
    JButton searchbtn,clearbtn;
    JTextField stf,idtf,nametf,scoretf;
    
    Search()
    {
       
        setSize(600,600);
        JPanel p = new JPanel();
          p.setBackground(new Color(0,0,0));
        p.setLayout(null);
        add(p);
        
        JLabel sl = new JLabel("Search ID:");
        sl.setBounds(20,100,80,30);
         sl.setForeground(Color.WHITE);
         
        p.add(sl);
        
         stf = new JTextField();
        stf.setBounds(140,100,100,30);
        p.add(stf);
        
      
          
        searchbtn = new JButton("Search");
        searchbtn.setBounds(370, 100, 100, 25);
        searchbtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
        searchbtn.setBackground(new Color(30, 144, 254));
        searchbtn.setForeground(Color.WHITE);
        searchbtn.addActionListener(this);
        p.add(searchbtn);
        
        clearbtn = new JButton("Clear");
        clearbtn.setBounds(370, 350, 100, 25);
        clearbtn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3, true));
        clearbtn.setBackground(new Color(30, 144, 254));
        clearbtn.setForeground(Color.WHITE);
        clearbtn.addActionListener(this);
        p.add(clearbtn);
        
         JLabel idl = new JLabel("ID:");
        idl.setBounds(250,200,80,30);
         idl.setForeground(Color.WHITE);
        p.add(idl);
        
         idtf = new JTextField();
        idtf.setBounds(370,200,100,30);
        p.add(idtf);
        
        JLabel namel = new JLabel("Name:");
        namel.setBounds(250,250,80,30);
         namel.setForeground(Color.WHITE);
        p.add(namel);
        
         nametf = new JTextField();
        nametf.setBounds(370,250,100,30);
        p.add(nametf);
        
        JLabel scorel = new JLabel("Score:");
        scorel.setBounds(250,300,80,30);
          scorel.setForeground(Color.WHITE);
        p.add(scorel);
        
         scoretf = new JTextField();
        scoretf.setBounds(370,300,100,30);
        p.add(scoretf);
        
        
        setVisible(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
    }
    
    
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == searchbtn)
        {
                        System.out.println("Search Clicked");
                        if(stf.getText().isEmpty())
                        {
                            JOptionPane.showMessageDialog(this,"Please Enter ID to Search");
                        }else
                        {
                            String url = "jdbc:postgresql://localhost:5432/GUItest";
                            String uname = "postgres";
                            String pass = "2004";

                            Connection con;
                        try {
                                con = DriverManager.getConnection(url, uname, pass);
                                System.out.println("Connection Established!");

                                int sid = Integer.parseInt(stf.getText());
                                String combinedQuery = "SELECT sid, sname, score FROM studentt WHERE sid = ?";

                                PreparedStatement ps = con.prepareStatement(combinedQuery);
                                ps.setInt(1, sid); // Set the parameter value for sid

                                ResultSet rs = ps.executeQuery();

                                if (rs.next()) {
                                    idtf.setText(String.valueOf(rs.getInt("sid")));
                                    nametf.setText(rs.getString("sname"));
                                    scoretf.setText(String.valueOf(rs.getInt("score")));
                                } else {
                                    // Handle the case where no student found with the given ID
                                    JOptionPane.showMessageDialog(this,"No Player Found on id " + sid );
                                }

                                rs.close();
                                con.close();
                            } catch (SQLException ex) {
                                Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                            }


                        }
        }
        if(e.getSource() == clearbtn)
        {
            stf.setText("");
            idtf.setText("");
            nametf.setText("");
            scoretf.setText("");

        }
        {
            
        }
    }
}

// below code is only for trial purpose
public class PreviousPlayersData {
     public static void main(String[] args)  throws Exception
    {
//       new Search();
        String url = "jdbc:postgresql://localhost:5432/GUItest";
        String uname ="postgres";
        String pass="2004";
        
        String query = "select * from studentt";
        Connection con = DriverManager.getConnection(url,uname,pass);

        
        Statement st= con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        new DBTable(rs);
        con.close();

    }
}



