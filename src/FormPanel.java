import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import sun.font.TextLabel;

public class FormPanel extends JPanel {

	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okBtn;
	private FormListener formListener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox citizenCheck;
	private JTextField taxField; // If you checked te checkbox then you have to
									// enter the tax
	private JLabel taxLabel; // This will be grayed out if the box is not
								// checked
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup; // to be used with male/female buttons

	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);

		// Creating our components
		nameLabel = new JLabel("Name: ");
		occupationLabel = new JLabel("Occupation: ");
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		empCombo = new JComboBox();
		ageList = new JList();
		citizenCheck = new JCheckBox();
		taxField = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");

		maleRadio = new JRadioButton("male");
		femaleRadio = new JRadioButton("female");
		genderGroup = new ButtonGroup();

		// ***** Set up Gender Radios *****//
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);

		maleRadio.setSelected(true);

		// this sets an action that can be retrieved using getActionCommand
		// when the radio button is selected
		// You can also use action listener just as we did with other components
		// like JButton
		maleRadio.setActionCommand("male");
		femaleRadio.setActionCommand("female");

		// ***** Set up Tax ID *****//
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);

		// This action can be check the box or not
		citizenCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// so we must check here the status of the box after the event
				boolean isTicked = citizenCheck.isSelected();
				// Enabel the tax field and label if the check box is checked
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			}
		});

		// ***** Set up list box *****//
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or over"));
		// Setting the model of the created list
		ageList.setModel(ageModel);
		ageList.setPreferredSize(new Dimension(115, 70));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);
		// ****************************//

		// ***** Set up combo box *****//
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("employed");
		empModel.addElement("self-employed");
		empModel.addElement("unemployed");

		empCombo.setModel(empModel);
		empCombo.setSelectedIndex(0);
		// empCombo.setEditable(true);
		// ****************************//

		okBtn = new JButton("OK");

		okBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Gathering the information from our components in the panel to
				// able to generate a FormEvent with this information and send
				// it to the controller to take appropriate action
				String name = nameField.getText();
				String occupation = occupationField.getText();
				AgeCategory age = (AgeCategory) ageList.getSelectedValue();
				String empCategory = (String) empCombo.getSelectedItem();
				String taxID = taxField.getText();
				boolean usCitizen = citizenCheck.isSelected();

				// This will return the action command that has been set before
				// using setActionCommand (male/female)
				String gender = genderGroup.getSelection().getActionCommand();

				System.out.println(empCategory);

				// When Ok Button is clicked, create a new event of type
				// FormEvent
				FormEvent form_event = new FormEvent(this, name, occupation,
						age.getId(), empCategory, taxID, usCitizen, gender);

				if (formListener != null) {
					formListener.formEventOccured(form_event);
				}
			}
		});

		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		layoutComponents();
	}

	// This only will show how the components will be displayed on the screen
	public void layoutComponents() {
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		/****** FIRST ROW ******/
		gc.gridy = 0;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;

		gc.fill = GridBagConstraints.NONE;

		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5); // leaves some spaces after the
											// label
		add(nameLabel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);

		/****** SECOND ROW ******/
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;

		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5); // leaves some spaces after the
											// label
		add(occupationLabel, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);

		/****** NEXT ROW ******/
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);
		add(new JLabel("Age:"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);

		/****** NEXT ROW ******/
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);
		add(new JLabel("Employment:"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);

		/****** NEXT ROW ******/
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);
		add(new JLabel("US Citizen:"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(citizenCheck, gc);

		/****** NEXT ROW ******/
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);
		add(taxLabel, gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(taxField, gc);

		/****** NEXT ROW ******/
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);
		add(new JLabel("Gender: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);

		/****** NEXT ROW ******/
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femaleRadio, gc);

		/****** NEXT ROW ******/
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 2.0;

		gc.gridx = 1;

		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(okBtn, gc);
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

class AgeCategory {

	private int id;
	private String text;

	public AgeCategory(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public String toString() {
		return text;
	}

	public int getId() {
		return id;
	}
}
