import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Toolbar extends JPanel implements ActionListener {
	
	private JButton button_one;
	private JButton button_two;
	
	private StringListener textListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		button_one = new JButton("Hello");
		button_two = new JButton("Goodbye");
		
		button_one.addActionListener(this);
		button_two.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(button_one);
		add(button_two);
		
	}

	public void setStringListener(StringListener listener) {
		this.textListener = listener;
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton)e.getSource();
		
		if (clicked == button_one) {
			if (textListener != null) {
				textListener.textEmitted("Hello\n");
			}
		}
		else if (clicked == button_two) {
			if (textListener != null) {
				textListener.textEmitted("Goodbye\n");
			}
		}
	}
}
