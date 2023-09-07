import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AddStudent extends JInternalFrame {
	private JTextField txtPno;
	private JTextField txtPass;
	private JTextField txtName;

	
	public AddStudent() {
		setTitle("Add Student");
		setBounds(0, 0, 450, 300);
setMaximizable(true);
setIconifiable(true);
setClosable(true);
getContentPane().setLayout(null);
txtPno = new JTextField();
txtPno.setBounds(35, 33, 356, 35);
getContentPane().add(txtPno);
txtPno.setColumns(10);
txtPass = new JTextField();
txtPass.setBounds(35, 97, 356, 35);
getContentPane().add(txtPass);
txtPass.setColumns(10);
txtName = new JTextField();
txtName.setBounds(35, 168, 356, 35);
getContentPane().add(txtName);
txtName.setColumns(10);
JButton btnNewButton = new JButton("SAVE");
btnNewButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
			String query="insert into student values(?,?,?)";
			PreparedStatement statement=cn.prepareStatement(query);
			statement.setString(1,txtPno.getText());
			statement.setString(2,txtPass.getText());
			statement.setString(3,txtName.getText());
			statement.executeUpdate();
			cn.close();
			JOptionPane.showMessageDialog(null, "Data Saved");
			
		}
		catch(Exception ec)
		{
		System.out.println(ec.getMessage());	
		}
	}
});
btnNewButton.setBounds(162, 223, 89, 23);
getContentPane().add(btnNewButton);
JLabel lblNewLabel = new JLabel("Enter Phone Number");
lblNewLabel.setBounds(36, 11, 253, 14);
getContentPane().add(lblNewLabel);
JLabel lblNewLabel_1 = new JLabel("Enter Password");
lblNewLabel_1.setBounds(36, 79, 215, 14);
getContentPane().add(lblNewLabel_1);
JLabel lblNewLabel_2 = new JLabel("Enter Name");
lblNewLabel_2.setBounds(35, 143, 105, 14);
getContentPane().add(lblNewLabel_2);
	}
}
