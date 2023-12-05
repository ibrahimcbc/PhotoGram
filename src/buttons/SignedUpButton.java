package buttons;

import java.awt.Color;

import javax.swing.JButton;
/**
 * allows you to sign up. Action method in DefaultFrame
 * @author icebe
 *
 */
public class SignedUpButton extends JButton {
	public SignedUpButton() {
		this.setText("SIGN UP");
		this.setBounds(75, 500, 100, 20);
		this.setBackground(Color.LIGHT_GRAY);
		this.setFocusable(false);
	}
}
