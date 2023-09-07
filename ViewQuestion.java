//import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewQuestion extends JInternalFrame {
	private JTextField textField;
	JTable table;
	private JScrollPane scrollPane;
	
	void disp()
	{
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
			String query="select * from questions";
			PreparedStatement statement=cn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			DefaultTableModel defaultTableModel=new DefaultTableModel();
			defaultTableModel.addColumn("ID");
			defaultTableModel.addColumn("Question");
			defaultTableModel.addColumn("Option 1");
			defaultTableModel.addColumn("Option 2");
			defaultTableModel.addColumn("Option 3");
			defaultTableModel.addColumn("Option 4");
			defaultTableModel.addColumn("Answer"); 
			
			while(rs.next())
			{
				String row[]= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
				defaultTableModel.addRow(row);
				}
			table.setModel(defaultTableModel);
			
			
			
			cn.close();
			
		}
		catch(Exception ec)
		{
		System.out.println(ec.getMessage());	
		}
	
	}
	
	void disp(String s)
	{
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
			String query="select * from questions where ques like '"+s+"%'";
			PreparedStatement statement=cn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			DefaultTableModel defaultTableModel=new DefaultTableModel();
			defaultTableModel.addColumn("ID");
			defaultTableModel.addColumn("Question");
			defaultTableModel.addColumn("Option 1");
			defaultTableModel.addColumn("Option 2");
			defaultTableModel.addColumn("Option 3");
			defaultTableModel.addColumn("Option 4");
			defaultTableModel.addColumn("Answer"); 
			
			while(rs.next())
			{
				String row[]= {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
				defaultTableModel.addRow(row);
				}
			table.setModel(defaultTableModel);
			
			
			
			cn.close();
			
		}
		catch(Exception ec)
		{
		System.out.println(ec.getMessage());	
		}
	
	}

			

	
	public ViewQuestion() {
		
		setBounds(0, 0, 450, 300);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				disp(textField.getText());
				
			}
		});
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		table = new JTable();
		 scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		disp();
		
		
	
		

	}

}
