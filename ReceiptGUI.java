import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


public class ReceiptGUI implements ActionListener {
	User user;
	
	JFrame window;
	
	JPanel mainPanel =  new JPanel();
	JPanel northPanel =  new JPanel();
	JPanel southPanel =  new JPanel();
	JPanel eastPanel =  new JPanel();
	JPanel westPanel =  new JPanel();
	JPanel centerPanel =  new JPanel(new GridLayout(8, 1));
	
	Image backgroundPic = new ImageIcon("src/images/hotelBackground.png").getImage();
	
	JLabel nameLabel;
	JLabel dateLabel;
	JLabel timeLabel;
	JLabel nightsLabel;
	JLabel roomNumberLabel;
	JLabel roomCapacityLabel;
	JLabel roomHeightLabel;
	JLabel roomSideLabel;
	
	JButton[] buttonArray;
	JButton submit = new JButton("Submit & Quit");
	
	public ReceiptGUI(User user, int roomNumber, int roomCapacity, String roomHeight, String roomSide) {
		
		this.user= user;
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		
		nameLabel = new JLabel("Name: " + user.getName());
		dateLabel = new JLabel("Today's Date: " + dateFormat.format(date));
		timeLabel = new JLabel("Current Time: " + timeFormat.format(date));
		nightsLabel = new JLabel("Nights: " + user.getNights());
		roomNumberLabel = new JLabel("Room Number: " + roomNumber);
		roomCapacityLabel = new JLabel("Room Capacity: " + roomCapacity);
		roomHeightLabel = new JLabel("Room Height Level: " + roomHeight.substring(0, 1).toUpperCase() + roomHeight.substring(1));
		roomSideLabel = new JLabel("Room Side: " + roomSide.substring(0, 1).toUpperCase() + roomSide.substring(1));
		
		window = new JFrame("Reciept - " + user.getName());
		
		mainPanel = new JPanel(new BorderLayout(500,150)) {
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

		centerPanel.add(nameLabel);
		centerPanel.add(dateLabel);
		centerPanel.add(timeLabel);
		centerPanel.add(nightsLabel);
		centerPanel.add(roomNumberLabel);
		centerPanel.add(roomCapacityLabel);
		centerPanel.add(roomHeightLabel);
		centerPanel.add(roomSideLabel);
		
		southPanel.add(submit);
		
		submit.setBackground(Color.GREEN);
		submit.addActionListener(this);
		
		window.add(mainPanel);
		window.setSize(1209,716);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == submit) {
			JOptionPane.showMessageDialog(null,"Thank you for choosing a room at Ringke Inn!");
			System.exit(0);
		}
	}
}
