package buttons;

import java.awt.Color;

import javax.swing.JButton;
/**
 *  allows you to login. Action method in DefaultFrame
 * @author icebe
 *
 */
public class LoginButton extends JButton {

	public LoginButton() {
	this.setText("LOG IN");
	this.setBounds(100,300,100,25);
	this.setBackground(Color.GRAY);
	this.setFocusable(false);
	}

}
