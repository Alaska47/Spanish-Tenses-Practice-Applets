import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppletDriver extends JFrame{
	
	private JPanel mainPanel;
	ArrayList<JButton> quizzes;

	public AppletDriver() {
		mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initialize();
        getContentPane().add(mainPanel);
        setTitle("Spanish Tenses Applet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
	}
	
	public static void main(String[] args) {
		AppletDriver test = new AppletDriver();
        test.setDefaultCloseOperation(EXIT_ON_CLOSE);
        test.setVisible(true);
        test.pack();
	}
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public void initialize() {
		
		JLabel l = new JLabel("Please choose the tense you would like to practice");
		l.setAlignmentX(CENTER_ALIGNMENT);
		Font font = l.getFont();
        Font boldFont = new Font(font.getFontName(), font.getStyle(), 16);
        l.setFont(boldFont);
        
		quizzes = new ArrayList<JButton>();
		JButton PST2 = new JButton("Present Subjunctive Tense");
		PST2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	AppletDriver.this.setVisible(false);
                Present_Subjunctive_Tense test = new Present_Subjunctive_Tense();
                test.setDefaultCloseOperation(EXIT_ON_CLOSE);
                test.setVisible(true);
                test.pack();
                
            }
        });
		PST2.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton PIT = new JButton("Perfect Indicative Tense");
		PIT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	AppletDriver.this.setVisible(false);
                Perfect_Indicative_Tense test = new Perfect_Indicative_Tense();
                test.setDefaultCloseOperation(EXIT_ON_CLOSE);
                test.setVisible(true);
                test.pack();
                
            }
        });
		PIT.setAlignmentX(Component.CENTER_ALIGNMENT);
		JButton PST = new JButton("Perfect Subjunctive Tense");
		PST.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                AppletDriver.this.setVisible(false);
                Perfect_Subjunctive_Tense test = new Perfect_Subjunctive_Tense();
                test.setDefaultCloseOperation(EXIT_ON_CLOSE);
                test.setVisible(true);
                test.pack();
                
            }
        });
		PST.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainPanel.add(l);
		mainPanel.add(PST2);
		mainPanel.add(PST);
		mainPanel.add(PIT);
	}
}
