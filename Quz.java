//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.nio.channels.NonReadableChannelException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Quz extends JFrame {

	private JPanel contentPane;
	private JRadioButton rdbtnop1;
	private JRadioButton rdbtnop2;
	private JRadioButton rdbtnop3;
	private JRadioButton rdbtnop4;
	JLabel lblNewLabel;
	public  ArrayList <Question>q;
	private String youranswer="";
public int count=-1;
public int score=0;


	void fill()
	{
		 q =new ArrayList<Question>();
		
try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
			String query="select * from questions";
			PreparedStatement statement=cn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			while(rs.next())
			{
			Question question=new Question(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
				q.add(question);
				}
			cn.close();
			
		}
		catch(Exception ec)
		{
		System.out.println(ec.getMessage());	
		}
		
	}
	
	void next()
	{
	count++;	
	if(count<=q.size()-1)
	{
		Question question=q.get(count);     //question is the local object in which each value from array list q  is copied;
		lblNewLabel.setText(question.getQuestion());
		rdbtnop1.setText(question.getOp1());
		rdbtnop2.setText(question.getOp2());
		rdbtnop3.setText(question.getOp3());
		rdbtnop4.setText(question.getOp4());
	
	}
	else {
		setScore();
		JOptionPane.showMessageDialog(null, "You Have Already Reached The Last Question" + " Your Score Is "+score);
		this.dispose();
	}
	}
	
	
	void setScore()
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
			String query="insert into result values(?,?,?)";
			PreparedStatement statement=cn.prepareStatement(query);
			
			
			java.util.Date date=new java.util.Date();
			String currentdate=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDay();
			
			statement.setString(1,Login.pno);
			statement.setInt(2, score);
			statement.setString(3, currentdate);
			statement.executeUpdate();
			cn.close();
			
		}
		catch(Exception ec)
		{
		// System.out.println(ec.getMessage());	
		}
		
		
	}
	
	public Quz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Q");
		lblNewLabel.setBounds(10, 11, 414, 28);
		contentPane.add(lblNewLabel);
		
		 rdbtnop1 = new JRadioButton("df");
		rdbtnop1.setBounds(10, 94, 212, 23);
		contentPane.add(rdbtnop1);
		
		 rdbtnop2 = new JRadioButton("New radio button");
		rdbtnop2.setBounds(10, 136, 246, 23);
		contentPane.add(rdbtnop2);
		
		 rdbtnop3 = new JRadioButton("New radio button");
		rdbtnop3.setBounds(10, 176, 246, 23);
		contentPane.add(rdbtnop3);
		
		 rdbtnop4 = new JRadioButton("New radio button");
		rdbtnop4.setBounds(10, 220, 226, 23);
		contentPane.add(rdbtnop4);
		
		ButtonGroup gpButtonGroup = new ButtonGroup();
		gpButtonGroup.add(rdbtnop1);
		gpButtonGroup.add(rdbtnop2);
		gpButtonGroup.add(rdbtnop3);
		gpButtonGroup.add(rdbtnop4);
		
		JButton btnNewButton = new JButton("NEXT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Question currentQuestion=q.get(count);
				if(rdbtnop1.isSelected())
				
					//System.out.println(rdbtnop1.getText());
				youranswer=rdbtnop1.getText();
				
				else if(rdbtnop2.isSelected())
					youranswer=rdbtnop2.getText();
				else	if(rdbtnop3.isSelected())
					youranswer=rdbtnop3.getText();
				else 	if(rdbtnop4.isSelected())
					youranswer=rdbtnop4.getText();
				
				if(youranswer.equals(currentQuestion.getCr()))
				{
				System.out.println(youranswer);
				score++;
				}
				System.out.println(score);
				next();
			}
		});
		
		
		
		btnNewButton.setBounds(318, 208, 89, 23);
		contentPane.add(btnNewButton);
		fill();
		next();
		
	}
}





