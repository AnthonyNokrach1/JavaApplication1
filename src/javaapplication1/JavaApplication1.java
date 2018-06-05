/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class JavaApplication1 {
        Connection con;
        Statement st;
        ResultSet rs;
    	JFrame f = new JFrame("User login");
	JLabel l = new JLabel("Username");
	JLabel l1 = new JLabel("Password");
	JTextField t = new JTextField(10);
	JPasswordField t1 = new JPasswordField(10);
	JButton b = new JButton("Login");
        
    public JavaApplication1(){
        frame();
        connect();
    }
    public void connect(){
        try{
        String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	Class.forName(driver);
			
        String db = "jdbc:odbc:db1";
	con = DriverManager.getConnection(db);
	st =  con.createStatement();
        }catch(Exception ex){
            
        }
    }
    public void frame(){
        f.setSize(600,400);
	f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	f.setVisible(true);
		
	JPanel p = new JPanel();
	p.add(l);p.add(t);p.add(l1);p.add(t1);p.add(b);
	f.add(p);
		
	b.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
				
	try {
				
	String user = t.getText().trim();
	String pass = t1.getPassword().toString();
				
	String sql = "select user,password from Password where user='"+user+"'and password='"+pass+"'";
	rs = st.executeQuery(sql);
				
	int count = 0;
	while(rs.next()) {
	count+=1;
        }
				
	if(count == 1) {
	JOptionPane.showMessageDialog(null, "User Found, Access Granted");
	}else if(count > 1) {
	JOptionPane.showMessageDialog(null, "Duplicate User, Access Denied");
	}else {
	JOptionPane.showMessageDialog(null, "User not Found");
        }
				
        } catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
	}
	}
        });
    }
    
    public static void main(String[] args) {
         JavaApplication1 jp = new JavaApplication1();
    }
    
}
