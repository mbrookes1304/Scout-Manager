import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * This is the screen where the user deletes a badge from
 * the database. All the badges in the database are displayed
 * in two columns. Each badge can be selected then the user is
 * prompted to confirm whether badge should be deleted. The system
 * will then remove badge from the database.
 * @author Matthew Brookes
 */
public class DeleteBadge {
	/**
	 * The class' only constructor has a JFrame as an argument which
	 * is passed from the home screen. On this frame the badge names
	 * are displayed then the pop-up is displayed.
	 * The back button will be directed towards the Badge Management screen.
	 * @param frame The screen where buttons will be drawn
	 */
	public DeleteBadge(JFrame frame){
		System.out.println("Delete a badge");
		Container pane = frame.getContentPane();
		pane.removeAll();
		pane.repaint();
		drawScreen(frame);
		frame.setVisible(true);
	}
	
	/**
	 * This function draws the first screen the user sees when this
	 * section is started. It consists of the names of all badges
	 * in the database in two columns which are ordered alphabetically.
	 * The user is then prompted whether to delete badge.
	 * @param frame The screen of the program
	 */
	private static void drawScreen(final JFrame frame){
		Container pane = frame.getContentPane();
		pane.setBounds(0,0,800,100);
		pane.setBackground(Color.WHITE); //Set the background to white
		JPanel header = new JPanel(); //Header panel
		header.setBounds(0, 0, 800, 100);
		//Set box layout on header
		BoxLayout headerLayout = new BoxLayout(header, BoxLayout.X_AXIS);
		header.setLayout(headerLayout);
		Box box = Box.createHorizontalBox();
		
		header.setBackground(new Color(139,0,102)); //Set purple color to background
		//Create home and back buttons
		ImageIcon back = new ImageIcon(ScoutManager.class.getResource("back.png"));
		JLabel backButton = new JLabel(back);
		backButton.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Return to badge management
				new BadgeManagement(frame);
			}
			
			//Required methods but useless
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		box.add(backButton);
		
		ImageIcon home = new ImageIcon(ScoutManager.class.getResource("home.png"));
		JLabel homeButton = new JLabel(home);
		homeButton.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Return to main screen
				new ScoutManager(frame);
			}
			
			//Required methods but useless
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});
		box.add(homeButton);
		
		//Add title
		JLabel title = new JLabel("BADGES");
		title.setFont(new Font("Verdana",Font.PLAIN, 40));
		title.setForeground(new Color(237,119,3));
		box.add(Box.createHorizontalGlue());
		box.add(title);
		
		//Add scout logo
		ImageIcon scoutLogo = new ImageIcon(ScoutManager.class.getResource("scout_logo.png"));
		JLabel logo = new JLabel(scoutLogo);
		logo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Add to header
		box.add(Box.createHorizontalGlue());
		box.add(logo);
		header.add(box);
		
		pane.add(header, BorderLayout.NORTH); //Add header to main screen
		
		//Create main body
		JPanel body = new JPanel();
		body.setBackground(Color.WHITE);
		body.setLayout(new ListLayout(2, 0));
		body.setBorder(new EmptyBorder(20, 0, 0, 0));
		//Make list scrollable if exceeds screen size
		JScrollPane scrollBody = new JScrollPane(body);
        scrollBody.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		Connection db = ScoutManager.connectToDB();
		ArrayList<String> badgesInDb = new ArrayList<String>(); //Holds all badge names
		try {
			//Retrieve all badges from db
			Statement stmt = db.createStatement();
			String sql = "SELECT NAME FROM BADGES";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//Foreach scout add to array
				badgesInDb.add((String) rs.getObject(1));
			}
			//Close connections
			stmt.close();
			db.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		//Sort names alphabetically
		Collections.sort(badgesInDb, new Comparator<String>() {
	        @Override
	        public int compare(String s1, String s2) {
	            return s1.compareToIgnoreCase(s2);
	        }
	    });
		
		for(final String name: badgesInDb){
			//Foreach badge in database draw a label
			JLabel nameLabel = new JLabel(name);
			nameLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			//If label clicked then show pop-up
			nameLabel.addMouseListener(new MouseListener(){
				@Override
				public void mouseClicked(MouseEvent e) {
					//Get confirmation from user
					int confirm = JOptionPane.showConfirmDialog(
						    frame,
						    "Are you sure you want to delete " + name + "?",
						    "Confirm delete",
						    JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.OK_OPTION){
						//If user wants to delete badge
						Connection db = ScoutManager.connectToDB();
						try {
							Statement stmt = db.createStatement();
							//Delete badge record and table
							String sql = "DELETE FROM BADGES WHERE NAME='"
									+ name + "'";
							stmt.executeUpdate(sql);
							stmt.close();
							db.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						new DeleteBadge(frame);
					}
				}
				//Useless methods but required
				@Override
				public void mouseEntered(MouseEvent e) {					
				}
				@Override
				public void mouseExited(MouseEvent e) {					
				}
				@Override
				public void mousePressed(MouseEvent e) {					
				}
				@Override
				public void mouseReleased(MouseEvent e) {					
				}
				
			});
			body.add(nameLabel);
		}
		
		pane.add(scrollBody, BorderLayout.CENTER);	
	}
}
