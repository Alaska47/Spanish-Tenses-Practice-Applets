import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Present_Subjunctive_Tense extends JFrame {

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

    public Present_Subjunctive_Tense() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        initialize();
        getContentPane().add(mainPanel);
        setAlwaysOnTop(true);
        answer.requestFocusInWindow();
    }
    public static void main(String[] args) {
        Present_Subjunctive_Tense quiz = new Present_Subjunctive_Tense();
        quiz.setDefaultCloseOperation(EXIT_ON_CLOSE);
        quiz.setVisible(true);
        quiz.pack();
    }

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

        title = new JLabel("Present Subjunctive Tense Practice");
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
                answer.setCaretPosition(answer.getDocument().getLength());
            }
        });

        e = new JButton("é");
        e.setAlignmentX(Component.CENTER_ALIGNMENT);
        accents.add(e);
        e.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e1)
            {
                answer.setText(answer.getText() + e.getText());
                answer.requestFocusInWindow();
                answer.setCaretPosition(answer.getDocument().getLength());
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
                answer.setCaretPosition(answer.getDocument().getLength());
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
                answer.setCaretPosition(answer.getDocument().getLength());
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
                answer.setCaretPosition(answer.getDocument().getLength());
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
        Document doc = null;
        while(doc == null) {
            try {
                String baseUrl = "http://www.verbix.com/webverbix/Spanish/" + verb + ".html";
                Connection con = Jsoup.connect(baseUrl).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(60000);
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

        Elements els = doc.getElementsByClass("pure-u-1-2");

        String[] array = new String[100];

        int presentCounter = 0;
        for (int i = 0; i < els.size(); i++) {
            String dob = (els.get(i).text());
            String[] arr = dob.split("\\s+");
            if(arr[0].equals("Present")) {
                presentCounter++;
            }
            if(presentCounter == 2) {
                array = arr;
                break;
            }
        }
        ArrayList<String> options = new ArrayList<String>();
        for(int i = 1; i < array.length; i++) {
            String string = array[i];
            String one = "";
            String two = "";
            char[] charArr = string.toCharArray();
            int index = 0;
            while(charArr[index] != (char) 160) {
                one += charArr[index];
                index++;
            }
            while(charArr[index] == (char) 160) {
                index++;
            }
            while(index < string.length()) {
                two += charArr[index];
                index++;
            }
            if(one.equals("me")) {
                options.add("yo" + "=" + one + " " + two);
            }
            else if(one.equals("te")) {
                options.add("tú" + "=" + one + " " + two);
            }
            else if(one.equals("se")) {
                if(two.endsWith("n")) {
                    options.add("ellos" + "=" + one + " " + two);
                }
                else {
                    options.add("él" + "=" + one + " " + two);
                }
            }
            else if(one.equals("nos")) {
                options.add("nosotros" + "=" + one + " " + two);
            }
            else if(one.equals("os")) {
                options.add("vosotros" + "=" + one + " " + two);
            }
            else if(one.equals("se")) {
                if(two.endsWith("n")) {
                    options.add("ellos" + "=" + one + " " + two);
                }
                else {
                    options.add("él" + "=" + one + " " + two);
                }
            }
            else {
                options.add(one + "=" + two);
            }
        }
        String[] abc = options.get(new Random().nextInt(options.size())).split("=");
        String form = abc[0];
        String answer = abc[1];
        return new Exercise(form, verb, answer);
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