package ui.demo.com.quizui;

/**
 * Created by Design-4 on 2/28/2018.
 */

public class Quest {
    public String Question;
    public String Ans_one;
    public String Ans_two;
    public String Ans_three;
    public String Ans_four;
    public String Score;
    public String AnsCorr;

    public Quest(String question, String ans_one, String ans_two, String ans_three, String ans_four, String score,String answer){
        Question=question;
        Ans_one=ans_one;
        Ans_two=ans_two;
        Ans_three=ans_three;
        Ans_four=ans_four;
        Score=score;
        AnsCorr=answer;
    }
    public String getAnsCorr() {
        return AnsCorr;
    }

    public void setAnsCorr(String answer) {
        AnsCorr = answer;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAns_one() {
        return Ans_one;
    }

    public void setAns_one(String ans_one) {
        Ans_one = ans_one;
    }

    public String getAns_two() {
        return Ans_two;
    }

    public void setAns_two(String ans_two) {
        Ans_two = ans_two;
    }

    public String getAns_three() {
        return Ans_three;
    }

    public void setAns_three(String ans_three) {
        Ans_three = ans_three;
    }

    public String getAns_four() {
        return Ans_four;
    }

    public void setAns_four(String ans_four) {
        Ans_four = ans_four;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }




}
