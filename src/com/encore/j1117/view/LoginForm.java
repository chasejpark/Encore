package com.encore.j1117.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame 
{
    public JTextField tf_id;
    public JPasswordField tf_pass;
    public JButton bt_login,bt_join;
	JLabel la_id, la_pass;
	
  public LoginForm()
	{		
		setTitle("Login Form");
		
		tf_id = new JTextField();			  
		tf_pass = new JPasswordField();
		
		bt_login = new JButton("로그인");
		bt_join = new JButton("신규가입");
	
	    la_id = new JLabel("I  D");
		la_pass = new JLabel("Password");
		
		tf_id.setBounds(80,30,100,25);
		tf_pass.setBounds(80,65,100,25);
		bt_login.setBounds(90,110,80,25);
		bt_join.setBounds(190,30,90,25);
		la_id.setBounds(8,30,80,25);
		la_pass.setBounds(8,65,90,25);
		
		setLayout(null);
		add(tf_id);
		add(tf_pass);
		add(bt_login);
		add(bt_join);
		add(la_id);
		add(la_pass);
		
		setBounds(400,300,300,180);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}//생성자   

    
}//LoginForm
