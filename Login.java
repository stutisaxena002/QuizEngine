import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
//import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPass;
	static String pno;
    
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(140, 68, 159, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setBounds(140, 134, 159, 20);
		contentPane.add(txtPass);
		txtPass.setColumns(10);
		
		
		//JComboBox comboBox = new JComboBox();
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Student"}));
        String[] boxOptions = {"Admin", "Student"};
        JComboBox<String> comboBox = new JComboBox<>(boxOptions);
		comboBox.setBounds(175, 180, 89, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Enter Username/Phone Number:");
		lblNewLabel.setBounds(140, 43, 195, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Password");
		lblNewLabel_1.setBounds(140, 113, 159, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getSelectedItem().toString().equals("Student"))
				{
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/qe?user=root&password=root");
					String query="select * from student where pno=? and pass=?";
					PreparedStatement statement=cn.prepareStatement(query);
					statement.setString(1, txtUser.getText());
					statement.setString(2, (String)txtPass.getText());
					
					ResultSet rs=statement.executeQuery();
					if(rs.next())
					{
					    pno=txtUser.getText();                  //to be used in score saving
						txtPass.setText(null);
						txtUser.setText(null);
						Quz frame=new Quz();
						frame.setVisible(true);
						}
					else {
						JOptionPane.showMessageDialog(null, "Enter Valid Credentials");
					
					cn.close();
					Login.this.dispose();
					}
				}
				catch(Exception ec)
				{
				System.out.println(ec.getMessage());	
				}
				}                                                // If Close
			
			else{
				if(txtUser.getText().equals("user") && txtPass.getText().equals("pass"))
				{
					txtPass.setText(null);
					txtUser.setText(null);
					HomePage fHomePage=new HomePage();
					fHomePage.setVisible(true);
					
				}
				
			}                            //  else closed
			
			}	
		});
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(140, 227, 159, 23);
		contentPane.add(btnNewButton);
	}
}