package shared;

public class Grade {

    public Grade(String assistant_name, int grade){
        this.assistant_name = assistant_name;
        this.grade = grade;
    }

    private String assistant_name;

    public void setGrade(int grade) {
        this.grade = grade;
    }

    private int grade;

    public String getAssistant_name() {
        return assistant_name;
    }

    public int getGrade() {
        return grade;
    }
}
