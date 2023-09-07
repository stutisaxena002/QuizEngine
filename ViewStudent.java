//import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
//import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.exceptions.DeadlockTimeoutRollbackMarker;
//import com.mysql.cj.result.Row;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewStudent extends JInternalFrame {
	private JTextField textField;
	JTable table;

	
	void disp()
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
			String query="select * from student";
			PreparedStatement statement=cn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			DefaultTableModel defaultTableModel=new DefaultTableModel();
			defaultTableModel.addColumn("Pno.");
			defaultTableModel.addColumn("Pass");
			defaultTableModel.addColumn("Name");
			
			while(rs.next())
			{
				String row[]= {rs.getString(1),rs.getString(2),rs.getString(3)};
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
	void disp(String n)
	{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
			String query="select * from student where name like '"+n+"%'";
			PreparedStatement statement=cn.prepareStatement(query);
			ResultSet rs=statement.executeQuery();
			DefaultTableModel defaultTableModel=new DefaultTableModel();
			defaultTableModel.addColumn("Pno.");
			defaultTableModel.addColumn("Pass");
			defaultTableModel.addColumn("Name");
			
			while(rs.next())
			{
				String row[]= {rs.getString(1),rs.getString(2),rs.getString(3)};
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
	
	public ViewStudent() {
		setBounds(0, 0, 450, 300);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		
		table = new JTable();
		
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				disp(textField.getText());
			}
		});
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		disp();
		

	}

}
