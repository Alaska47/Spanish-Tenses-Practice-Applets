import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Perfect_Indicative_Tense extends JFrame {

    private JPanel mainPanel;
    private JPanel subPanel;
    private JPanel accents;
    private JLabel question;
    private JLabel title;
    private JButton check;
    public JTextField answer;
    private Exercise currentExercise;
    private ArrayList<String> verbs;
    private int correctAnswer;
    private int errorCount = 0;

    public JButton a;
    public JButton e;
    public JButton i;
    public JButton o;
    public JButton u;

    public Perfect_Indicative_Tense() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initialize();
        getContentPane().add(mainPanel);
        setAlwaysOnTop(true);
        answer.requestFocusInWindow();
    }
    /*
    public static void main(String[] args) {
        Perfect_Indicative_Tense quiz = new Perfect_Indicative_Tense();
        quiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
        quiz.setVisible(true);
        quiz.pack();
    }
    */

    public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
    
    private void initialize() {
    	
    	String verb = "abrir,aprender,bailar,buscar,cambiar,cantar,cerrar,comenzar,comer,comprar,correr,creer,dar,decir,dormir,encontrar,entender,escribir,escuchar,estar,estudiar,explicar,haber,hablar,hacer,ir,leer,llevar,necesitar,olvidar,pagar,pensar,perder,poder,poner,preguntar,querer,saber,salir,ser,subir,tener,terminar,tomar,trabajar,traducir,traer,vender,venir,ver,viajar,vivir,acostarse,afeitarse,arrodillarse,despertarse,dormirse,irse,levantarse,ponerse,quitar,quedarse,sentarse,sentirse";
    	String[] l1 = verb.split(",");
    	verbs = new ArrayList<String>();
    	verbs.addAll(Arrays.asList(l1));
        

        subPanel = new JPanel();
        subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.X_AXIS));

        
        
        question = new JLabel("");
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        title = new JLabel("Perfect Indicative Tense Practice");
        title.setAlignmentX(CENTER_ALIGNMENT);
        Font font = question.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, 16);
        title.setFont(boldFont);

        check = new JButton("Check your answer");
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                updateGUI(1);
            }
        });
        check.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getRootPane().setDefaultButton(check);

        answer = new JTextField(15);
        answer.setCaret(new HighlightCaret());
        answer.setAlignmentX(Component.CENTER_ALIGNMENT);
        answer.setMaximumSize(
                new Dimension(250, answer.getPreferredSize().height) );

        accents = new JPanel();
        accents.setLayout(new BoxLayout(accents, BoxLayout.Y_AXIS));

        a = new JButton("á");
        a.setAlignmentX(Component.CENTER_ALIGNMENT);
        accents.add(a);
        a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                answer.setText(answer.getText() + a.getText());
                answer.requestFocusInWindow();
            }
        });

        e = new JButton("é");
        e.setAlignmentX(Component.CENTER_ALIGNMENT);
        accents.add(e);
        e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1)
            {
            	System.out.println(answer.getCaretPosition());
                answer.setText(answer.getText() + e.getText());
                answer.requestFocusInWindow();
            }
        });

        i = new JButton("í");
        i.setAlignmentX(Component.CENTER_ALIGNMENT);
        accents.add(i);
        i.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1)
            {
                answer.setText(answer.getText() + i.getText());
                answer.requestFocusInWindow();
            }
        });

        o = new JButton("ó");
        o.setAlignmentX(Component.CENTER_ALIGNMENT);
        accents.add(o);
        o.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1)
            {
                answer.setText(answer.getText() + o.getText());
                answer.requestFocusInWindow();
            }
        });

        u = new JButton("ú");
        u.setAlignmentX(Component.CENTER_ALIGNMENT);
        accents.add(u);
        u.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1)
            {
                answer.setText(answer.getText() + u.getText());
                answer.requestFocusInWindow();
            }
        });

        JPanel subMainPanel = new JPanel();
        subMainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subMainPanel.setLayout(new BoxLayout(subMainPanel, BoxLayout.Y_AXIS));
        subMainPanel.add(question);
        subMainPanel.add(answer);
        subMainPanel.add(check);

        subPanel.add(subMainPanel);
        subPanel.add(accents);

        mainPanel.add(title);
        mainPanel.add(subPanel);

        currentExercise = getNewExercise();
        updateGUI(0);
        answer.requestFocusInWindow();
        answer.setSelectionEnd(0);
        
    }

    public Exercise getNewExercise() {
        int choice = new Random().nextInt(verbs.size());
        String verb = (verbs.get(choice));
        boolean isReflex = verb.contains("se");
        //verb = "divertirse";
        Document doc = null;
        while(doc == null) {
            try {
                String baseUrl = "http://www.verbix.com/webverbix/Spanish/" + verb + ".html";
                Connection con = Jsoup.connect(baseUrl).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(5000);
                Connection.Response resp = con.execute();
                if (resp.statusCode() == 200) {
                    doc = con.get();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            errorCount++;
            if(errorCount > 5) {
                JOptionPane.showMessageDialog(null, "Something bad has happened! Please restart the applet", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        String str = doc.toString();
        //System.out.println(str);

        
        
        Elements els = doc.getElementsByClass("pure-u-1-2");

        String[] array = new String[100];
        String doc1 = "";
        int presentCounter = 0;
        for (Element s : els) {
        	String dob = (s.text());
            if(dob.contains("Perfect")) {
                presentCounter++;
            }
            if(presentCounter == 1) {
                doc1 = dob;
                //System.out.println(doc1);
                break;
            }
        }
        
        //System.out.println(doc1);
        String out = "";
        int count = 0;
        for(int i = 0; i < doc1.length(); i++) {
        	int a = ((int) doc1.charAt(i));
        	if(a != 32 && a != 194 && a != 160)
        		out += doc1.charAt(i);
        	else
        		out += ",";
        }
        array = out.split(",");
        String[] forms = {"yo","tú","él","nosotros","vosotros","ellos"};
        ArrayList<String> l = new ArrayList<String>();
        for(String a : array) {
        	if(!a.equals("Perfect") && !Arrays.asList(forms).contains(a) && a.trim().length() > 0) {
        		l.add(a);
        	}
        }
        ArrayList<String> options = new ArrayList<String>();
        if(!isReflex) {
        	for(int i = 0; i < l.size(); i += 2) {
        		String answer1 = l.get(i) + " " + l.get(i + 1);
        		switch(i / 2) {
        		case 0:
        			options.add("yo=" + answer1);
        			break;
        		case 1:
        			options.add("tú=" + answer1);
        			break;
        		case 2:
        			options.add("él=" + answer1);
        			break;
        		case 3:
        			options.add("nosotros=" + answer1);
        			break;
        		case 4:
        			options.add("vosotros=" + answer1);
        			break;
        		case 5:
        			options.add("ellos=" + answer1);
        			break;
        			
        		}
        	}
        } else {
        	for(int i = 0; i < l.size(); i += 3) {
        		String answer1 = l.get(i) + " " + l.get(i + 1) + " " + l.get(i + 2);
        		switch(i / 3) {
        		case 0:
        			options.add("yo=" + answer1);
        			break;
        		case 1:
        			options.add("tú=" + answer1);
        			break;
        		case 2:
        			options.add("él=" + answer1);
        			break;
        		case 3:
        			options.add("nosotros=" + answer1);
        			break;
        		case 4:
        			options.add("vosotros=" + answer1);
        			break;
        		case 5:
        			options.add("ellos=" + answer1);
        			break;
        			
        		}
        	}
        }
        
        String[] abc = options.get(new Random().nextInt(options.size())).split("=");
        String form = abc[0];
        String answer2 = abc[1];
        return new Exercise(form, verb, answer2);
    }

    public void updateGUI(int flag) {

        if(flag == 0) {
            question.setText("What is the " + currentExercise.getForm() + " form of the verb: " + currentExercise.getVerb() + "?");
            answer.setText("");
        } else {
            int i = validateAnswer();
            if(i == 0)
                return;
            currentExercise = getNewExercise();
            question.setText("What is the " + currentExercise.getForm() + " form of the verb: " + currentExercise.getVerb() + "?");
            answer.setText("");
        }
        answer.requestFocusInWindow();
        this.pack();
    }

    public int validateAnswer() {

        String input = answer.getText().trim();
        if(input.compareToIgnoreCase("") == 0)
            return 0;
        if(input.compareToIgnoreCase(currentExercise.getAnswer()) == 0) {

            JOptionPane.showMessageDialog(null, "CORRECT!", "Report", JOptionPane.INFORMATION_MESSAGE);
            correctAnswer++;
            return 1;
        }
        else {
            String mess = "INCORRECT! The right answer is " + currentExercise.getAnswer() + ". " +  "Your streak was " + correctAnswer + " answers right!";
            JOptionPane.showMessageDialog(null, mess, "Report", JOptionPane.INFORMATION_MESSAGE);
            correctAnswer = 0;
            return -1;
            //display report
        }
    }
}