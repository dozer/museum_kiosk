package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



public class LoginView extends JFrame {
	LoginView() {
		super("LoginView");
		new Login(this);
	}

	public static void main(String[] args) {
		new LoginView();
	}

	class Login extends JFrame implements ActionListener
	{
		final JButton submitBtn;
		final JPanel panel;
		final JLabel usernameLabel, passwordLabel;
		final JTextField  usernameField;
		final JPasswordField passwordField;

		JFrame father;

		Login(JFrame masterForm)
		{
			super("Login form");
			father = masterForm;
			usernameLabel = new JLabel("Username:");
			usernameField = new JTextField(15);

			passwordLabel = new JLabel("Password:");
			passwordField = new JPasswordField(15);

			submitBtn =new JButton("SUBMIT");
			submitBtn.addActionListener(this);

			panel = new JPanel(new GridLayout(3,2));


			panel.add(usernameLabel);
			panel.add(usernameField);
			panel.add(passwordLabel);
			panel.add(passwordField);
			panel.add(submitBtn);
			// to fill the GridLayout
			panel.add(new JLabel(""));
			add(panel,BorderLayout.CENTER);

			setTitle("System Login");
			setSize(250,125);

			// Get the size of the screen
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

			// Determine the new location of the window
			int w = this.getSize().width;
			int h = this.getSize().height;
			int x = (dim.width-w)/2;
			int y = (dim.height-h)/2;

			// Move the window
			this.setLocation(x, y);

			setVisible(true);
		}
		public void actionPerformed(ActionEvent ee)
		{
			String user= usernameField.getText().toLowerCase();
			String pass = new String(passwordField.getPassword()).toLowerCase();

			if(user.equals("admin")) {
				if(pass.equals("adminpassword")) {
					this.setVisible(false);				// hide myself

					
					//CASEY, PLEASE ADD YOU CALL TO OPEN YOUR APP HERE PLEASE!!

					this.dispose();						// clean my resource
					return;
				}
			}
			else if(user.equals("guest")) {
				if(pass.equals("guestpassword")) {
					GuestView.begin();

					this.dispose();						// clean my resource
					return;
				}
			}

			else{
				// send an option pane that does not match
				JOptionPane.showMessageDialog(this, "Sorry, wrong username and/or password.\n Please try again!");
			}
		}	
	}
}
