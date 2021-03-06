import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * This is the screen which the user visits to select a report
 * to be generated from the database. It will be made up of a header
 * which is standard to each screen and five buttons linking to the
 * relevant sections.
 * @author Matthew Brookes
 */
public class Reports {

	/**
	 * The class' only constructor has a JFrame as an argument which
	 * is passed from the home screen. On this frame the five buttons
	 * are drawn to replace those from the home screen and the back
	 * button is linked to the home screen.
	 * @param frame The screen where buttons will be drawn
	 */
	public Reports(JFrame frame) {
		System.out.println("Reports");
		Container pane = frame.getContentPane();
		pane.removeAll();
		pane.repaint();
		drawScreen(frame);
		frame.setVisible(true);
	}
	/**
	 * This method draws the header and body of the report screen
	 * on the frame which is passed
	 * @param frame The current window
	 */
	private static void drawScreen(final JFrame frame){
		Container pane = frame.getContentPane(); //Main screen
		pane.setBounds(0,0,800,100);
		pane.setBackground(Color.WHITE); //Set the background to white
		JPanel header = new JPanel(); //Header panel
		header.setBounds(0, 0, 800, 100);
		//Set box layout on header
		BoxLayout headerLayout = new BoxLayout(header, BoxLayout.X_AXIS);
		header.setLayout(headerLayout);
		Box box = Box.createHorizontalBox();
		
		//Set purple color to background
		header.setBackground(new Color(139,0,102)); 
		//Create home and back buttons
		ImageIcon back = new ImageIcon(ScoutManager.class.getResource("back.png"));
		JLabel backButton = new JLabel(back);
		backButton.addMouseListener(new MouseListener(){
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
		JLabel title = new JLabel("REPORTS");
		title.setFont(new Font("Verdana",Font.PLAIN, 50));
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
		GridLayout bodyLayout = new GridLayout(2,3);
		body.setLayout(bodyLayout);
		body.setBorder(new EmptyBorder(20,20,80,80));
		
		//Create buttons
		JButton ecs = new JButton("<html>" +
				"<center>Emergency Contact Sheet</center></html>");
		ecs.setFont(new Font("Calibri",Font.PLAIN,20));
		ecs.setForeground(Color.WHITE);
		ecs.setBackground(new Color(79,129,189));
		ecs.setFocusable(false);
		
		//When button is clicked start ECS screen
		ecs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmergencyContactSheet(frame);
			}	
		});
		
		JButton register = new JButton("Register");
		register.setFont(new Font("Calibri",Font.PLAIN,20));
		register.setForeground(Color.WHITE);
		register.setFocusable(false);
		register.setBackground(new Color(79,129,189));
		
		//When button is clicked start Register screen
		register.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new Register(frame);
			}	
		});
				
		JButton emailAddresses = new JButton("<html>" +
				"<center>Email Addresses</center></html>");
		emailAddresses.setBackground(new Color(79,129,189));
		emailAddresses.setFont(new Font("Calibri",Font.PLAIN,20));
		emailAddresses.setForeground(Color.WHITE);
		emailAddresses.setFocusable(false);
		
		//When button is clicked start Email Addresses screen
		emailAddresses.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmailAddresses(frame);
			}	
		});
		
		JButton badges = new JButton("Badges");
		badges.setBackground(new Color(79,129,189));
		badges.setFont(new Font("Calibri",Font.PLAIN,20));
		badges.setForeground(Color.WHITE);
		badges.setFocusable(false);
		
		//When button is clicked start Badges screen
		badges.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new Badges(frame);
			}	
		});
		
		JButton scouts = new JButton("Scouts");
		scouts.setBackground(new Color(79,129,189));
		scouts.setFont(new Font("Calibri",Font.PLAIN,20));
		scouts.setForeground(Color.WHITE);
		scouts.setFocusable(false);
		
		//When button is clicked start Scouts screen
		scouts.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new Scouts(frame);
			}	
		});
				
		//Set padding between buttons
		bodyLayout.setHgap(50);
		bodyLayout.setVgap(100);
		
		//Add buttons to body panel
		body.add(ecs);
		body.add(register);
		body.add(emailAddresses);
		body.add(badges);
		body.add(scouts);
		
		pane.add(body, BorderLayout.CENTER);
	}
}
