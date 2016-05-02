import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class InformationGUI extends JPanel implements ActionListener {

	User user = new User();
	
	JFrame window = new JFrame("Welcome to the Ringke Inn!");
	
	JPanel mainPanel =  new JPanel();
	JPanel northPanel =  new JPanel();
	JPanel southPanel =  new JPanel();
	JPanel eastPanel =  new JPanel();
	JPanel westPanel =  new JPanel();
	JPanel centerPanel =  new JPanel(new GridLayout(8, 2));

	Image backgroundPic = new ImageIcon("src/images/hotelBackground.png").getImage();
	
	JLabel informationLabel1 = new JLabel("Welcome to the Ringke Inn!");
	JLabel informationLabel2 = new JLabel("Please select your configuration below to book a room.");
	
	JLabel nameLabel = new JLabel("Name:");
	TextArea nameEnter = new TextArea(1,7);
	
	JLabel adultsLabel = new JLabel("Adults:");
	String[] adultsOptions = {"1","2","3","4"};
	JComboBox adultList = new JComboBox(adultsOptions);
	
	JLabel childrenLabel = new JLabel("Children:");
	String[] childrenOptions = {"0","1","2","3"};
	JComboBox childrenList = new JComboBox(childrenOptions);
	
	JLabel nightsLabel = new JLabel("Nights:");
	String[] nightsOptions = {"1","2","3","4","5","6","7"};
	JComboBox nightsList = new JComboBox(nightsOptions);
	
	JLabel locationHeightLabel = new JLabel("Location Height:");
	String[] locationHeightOptions = {"No Preference","Low","Middle","High"};
	JComboBox locationHeightList = new JComboBox(locationHeightOptions);
	
	JLabel locationSideLabel = new JLabel("Location Side:");
	String[] locationSideOptions = {"No Preference","Front","Back"};
	JComboBox locationSideList = new JComboBox(locationSideOptions);
	
	JButton submit = new JButton("Submit");
	JButton quit = new JButton("Quit");
	
	public InformationGUI() {
		mainPanel = new JPanel(new BorderLayout(200,200)) {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundPic,0,0,this);
			}
		};
		
		mainPanel.setOpaque(false);
		northPanel.setOpaque(false);
		southPanel.setOpaque(false);
		eastPanel.setOpaque(false);
		westPanel.setOpaque(false);
		
		mainPanel.add(BorderLayout.NORTH, northPanel);
		mainPanel.add(BorderLayout.SOUTH, southPanel);
		mainPanel.add(BorderLayout.EAST, eastPanel);
		mainPanel.add(BorderLayout.WEST, westPanel);
		mainPanel.add(BorderLayout.CENTER, centerPanel);

		centerPanel.setBackground(Color.YELLOW);
	
		centerPanel.add(informationLabel1);
		centerPanel.add(informationLabel2);
		centerPanel.add(nameLabel);
		centerPanel.add(nameEnter);
		centerPanel.add(adultsLabel);
		centerPanel.add(adultList);
		centerPanel.add(childrenLabel);
		centerPanel.add(childrenList);
		centerPanel.add(nightsLabel);
		centerPanel.add(nightsList);
		centerPanel.add(locationHeightLabel);
		centerPanel.add(locationHeightList);
		centerPanel.add(locationSideLabel);
		centerPanel.add(locationSideList);
		centerPanel.add(submit);
		centerPanel.add(quit);
		
		submit.setBackground(Color.GREEN);
		quit.setBackground(Color.RED);
		
		adultList.addActionListener(this);
		childrenList.addActionListener(this);
		nightsList.addActionListener(this);
		locationHeightList.addActionListener(this);
		locationSideList.addActionListener(this);
		submit.addActionListener(this);
		quit.addActionListener(this);

		window.add(mainPanel);
		window.setSize(1209,716);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == submit) {
			user.setName(nameEnter.getText());
			user.setAdults(Integer.parseInt((String)adultList.getSelectedItem()));
			user.setChildren(Integer.parseInt((String)childrenList.getSelectedItem()));
			user.setNights(Integer.parseInt((String)nightsList.getSelectedItem()));
			user.setLocationHeight((String)locationHeightList.getSelectedItem());

			JOptionPane.showMessageDialog(null,"Thanks for submitting your information. We will now try to find a suitable room for your stay.");
			window.setVisible(false);
			
			try {
				SelectionGUI selectionGUI = new SelectionGUI(user);
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}
		
		if (e.getSource() == quit) {
			JOptionPane.showMessageDialog(null,"Thank you for visiting the Ringke Inn!");
			System.exit(0);
		}
	}
}