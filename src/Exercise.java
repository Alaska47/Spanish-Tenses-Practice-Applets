
public class Exercise {

    private String form;
    private String verb;
    private String answer;

    public Exercise(String f, String v, String a) {
        form = f;
        verb = v;
        answer = a;
    }

    public String getForm() {
        return form;
    }

    public String getVerb() {
        return verb;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean checkAnswer(String attempt) {
        if(attempt.equals(getAnswer())) {
            return true;
        } else {
            return false;
        }
    }

    public void setForm(String f) {
        form = f;
    }

    public void setVerb(String v) {
        verb = v;
    }

    public void setAnswer(String a) {
        answer = a;
    }
}