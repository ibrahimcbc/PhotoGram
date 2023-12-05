package buttons;

import java.awt.Color;

import javax.swing.JButton;
/**
 * sends you to sign up page. Action method in DefaultFrame
 * @author icebe
 *
 */
public class SignupButton extends JButton {
	public SignupButton() {
	this.setText("SIGN UP");
	this.setBounds(250,300,100,25);
	this.setBackground(Color.GRAY);
	this.setFocusable(false);
	}

}
