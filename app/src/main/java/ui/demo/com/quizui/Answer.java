package ui.demo.com.quizui;

/**
 * Created by Design-4 on 2/28/2018.
 */

public class Answer {
    public Answer(String answer,String option){
        this.answer=answer;
        this.option=option;
    }
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String answer;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String option;

}
