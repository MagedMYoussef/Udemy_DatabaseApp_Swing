import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private TextPanel text_panel;
	private Toolbar tool_bar;
	private FormPanel form_panel;

	public MainFrame() {
		super("Database System");

		setLayout(new BorderLayout());

		text_panel = new TextPanel();
		tool_bar = new Toolbar();
		form_panel = new FormPanel();

		tool_bar.setStringListener(new StringListener() {

			public void textEmitted(String text) {
				text_panel.appendText(text);
			}
		});

		// MainFrame will listen to the FormPanel through the FormListener and
		// when the event occurs, it wil take the control and instruct the other
		// component to do what is supposed to be done (here text panel)

		form_panel.setFormListener(new FormListener() {

			public void formEventOccured(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCategory = e.getAgeCategory();
				String empCategory = e.getEmploymentCategory();

				text_panel.appendText(name + ": " + occupation + ": "
						+ ageCategory + ": " + empCategory + "\n");
			}
		});

		add(form_panel, BorderLayout.WEST);
		add(tool_bar, BorderLayout.NORTH);
		add(text_panel, BorderLayout.CENTER);

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
