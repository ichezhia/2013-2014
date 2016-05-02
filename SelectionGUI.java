import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class SelectionGUI extends JPanel implements ActionListener {

	User user;
	
	int[] roomNumber;
	int[] maxPeople;
	String[] height; 
	String[] side;
	
	int[] chosenIndex;
	boolean[] chosenIndexBoolean;
	int possibleRooms = 0;
	JFrame window;
	
	JPanel mainPanel =  new JPanel();
	JPanel northPanel =  new JPanel();
	JPanel southPanel =  new JPanel();
	JPanel eastPanel =  new JPanel();
	JPanel westPanel =  new JPanel();
	JPanel centerPanel =  new JPanel(new GridLayout(1, 2));
	JPanel leftPanel =  new JPanel();
	JPanel rightPanel =  new JPanel();

	Image backgroundPic = new ImageIcon("src/images/hotelBackground.png").getImage();
	
	Object[][] data;
	JTable table;

	JLabel instructionLabel = new JLabel("Please select a room from the various options.");
	
	JButton[] buttonArray;
	JButton quit = new JButton("Quit");
	
	public SelectionGUI(User user) throws IOException {
		this.user= user;
		
		roomNumber = new int[user.getTotalRooms()];
		maxPeople = new int[user.getTotalRooms()];
		height = new String[user.getTotalRooms()];
		side = new String[user.getTotalRooms()];
		chosenIndexBoolean = new boolean[user.getTotalRooms()];
		
		window = new JFrame("Room Selector - " + user.getName());
		
		BufferedReader rd = new BufferedReader(new FileReader("src/data.txt"));
		for (int i=0; i<user.getTotalRooms(); i++)
		{
			try {
				String str = rd.readLine();
				String[] temp = str.split(" ", str.length());
				
				roomNumber[i] = Integer.parseInt(temp[0]);
				maxPeople[i] = Integer.parseInt(temp[1]);
				height[i] = temp[2];
				side[i] = temp[3];
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		rd.close();
		
		for(int i=0; i<user.getTotalRooms(); i++){
			if(user.getAdults()+user.getChildren() <= maxPeople[i]) {
				if (height[i].equalsIgnoreCase(user.getLocationHeight()) || user.getLocationHeight() == "No Preference") {
					if (side[i].equalsIgnoreCase(user.getLocationSide()) || user.getLocationSide() == "No Preference") {
						chosenIndexBoolean[i] = true;
						possibleRooms++;
					}
				}
			}
		}
		
		//JTable Code
		String[] columnNames = {"Room Number","Room Capacity","Height","Side"};
		data = new Object[possibleRooms][4];
		int j = 0;
		
		for (int i=0; i<user.getTotalRooms(); i++) {
			if (chosenIndexBoolean[i]) {
				data[j][0] = roomNumber[i];
				data[j][1] = maxPeople[i];
				data[j][2] = height[i];
				data[j][3] = side[i];
				j++;
				
			}
		}
		
		table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(410, 285));
        table.setFillsViewportHeight(true);
        
        buttonArray = new JButton[possibleRooms];
        int k = 0;
        for (int i=0; i<user.getTotalRooms(); i++) {
        	if (chosenIndexBoolean[i]) {
        		buttonArray[k] = new JButton();
        		buttonArray[k].setText(Integer.toString(roomNumber[i]));
        		k++;
        	}
        }
        
		mainPanel = new JPanel(new BorderLayout(150,150)) {
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
		centerPanel.add(leftPanel);
		centerPanel.add(rightPanel);
		leftPanel.add(instructionLabel);
		leftPanel.add(new JScrollPane(table));		
		
		
        for (int i=0; i<possibleRooms; i++) {
        	rightPanel.add(buttonArray[i]);
        }
		
		quit.setBackground(Color.RED);
		
		for (int i=0; i<possibleRooms; i++) {
			buttonArray[i].addActionListener(this);
		}
		
		southPanel.add(quit);
		
		quit.addActionListener(this);

		window.add(mainPanel);
		window.setSize(1209,716);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i=0; i<possibleRooms; i++) {
			if (e.getSource() == buttonArray[i]) {
				int chosenRoomNumber = (Integer)data[i][0];
				int chosenRoomCapacity = (Integer)data[i][1];
				String chosenRoomHeight = (String)data[i][2];
				String chosenRoomSide = (String)data[i][3];
				
				JOptionPane.showMessageDialog(null,"Thank you for booking a room. We will now provide you with a reciept overview of your reservation.");
				window.setVisible(false);
				
				ReceiptGUI recieptGUI = new ReceiptGUI(user, chosenRoomNumber, chosenRoomCapacity, chosenRoomHeight, chosenRoomSide);
			}
		}
		
		if (e.getSource() == quit) {
			JOptionPane.showMessageDialog(null,"We are sorry that you were unable to find a suitable room.");
			System.exit(0);
		}
	}
}