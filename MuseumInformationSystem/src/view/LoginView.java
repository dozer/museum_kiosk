package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import access.FillDatabase;
import access.FloorPlanDAO;
import access.MuseumItemDAO;
import control.LoginController;
import model.FloorPlan;
import view.FloorPlanEditView;


/**
 * This is the login popup.  This takes in a username and
 * password, and determines which view is seen, Admin or Guest
 * @author Sara
 *
 */
public class LoginView extends JFrame {
	public LoginView() {
		super("LoginView");
		new Login(this);
	}

	public static void main(String[] args) {
		new LoginView();			//make this call to prompt user with login screen
		//FillDatabase.fillAll();		//call this method to fill databases with default info
	}
	
	/**
	 * Login Class
	 * 
	 *
	 */
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

			int accessLevel = LoginController.verifyUser(user, pass);
			if(accessLevel == 0){
				this.setVisible(false);				// hide myself

				FloorPlanEditView fpev = new FloorPlanEditView(FloorPlanDAO.findFloorPlan(0));
				fpev.display();

				FloorPlan fp = fpev.getFloorPlan();

				this.dispose();						// clean my resource
				return;
			}

			else if(accessLevel == 1){
				GuestView.begin();
				FloorPlanView fpv = new FloorPlanView(FloorPlanDAO.findFloorPlan(0));
				fpv.display();
				this.dispose();						// clean my resource
				return;
			}

			else{
				// send an option pane that does not match
				JOptionPane.showMessageDialog(this, "Sorry, wrong username and/or password.\n Please try again!");
			}
		}	
	}
}

