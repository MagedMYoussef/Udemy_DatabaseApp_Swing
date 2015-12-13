import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {

	private TextPanel textPanel;
	private Toolbar toolBar;
	private FormPanel formPanel;

	public MainFrame() {
		super("Database System");

		setLayout(new BorderLayout());

		textPanel = new TextPanel();
		toolBar = new Toolbar();
		formPanel = new FormPanel();

		// Setting the menu bar of the frame
		setJMenuBar(createMenuBar());

		toolBar.setStringListener(new StringListener() {

			public void textEmitted(String text) {
				textPanel.appendText(text);
			}
		});

		// MainFrame will listen to the FormPanel through the FormListener and
		// when the event occurs, it wil take the control and instruct the other
		// component to do what is supposed to be done (here text panel)

		formPanel.setFormListener(new FormListener() {

			public void formEventOccured(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCategory = e.getAgeCategory();
				String empCategory = e.getEmploymentCategory();

				textPanel.appendText(name + ": " + occupation + ": "
						+ ageCategory + ": " + empCategory + "\n");
			}
		});

		add(formPanel, BorderLayout.WEST);
		add(toolBar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);

		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// creating a menu bar for out App
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// ***** File Menu *****//
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator(); // separate the above and the following items
		fileMenu.add(exitItem);

		// ***** Window Menu *****//
		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");
		// JMenuItem showFormItem = new JMenuItem("Person Form");

		// use checkbox as a menu item
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);

		// show is a submenu of window menu
		showMenu.add(showFormItem);
		windowMenu.add(showMenu);

		// ***** Addign the created menues to the menu bar *****//
		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// getSource() returns the object that caused the event which is
				// here showFormItem which is also of JCheckBoxMenuItem
				// ** We could use showFormItem directly!
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();

				// this controls the visibility of the formPanel based on the
				// checkbox in the menu item
				formPanel.setVisible(menuItem.isSelected());
			}
		});
		return menuBar;
	}
}
