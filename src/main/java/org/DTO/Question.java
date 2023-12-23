package org.DTO;

public class Question {
    int questionId;
    String question;
    String qption1;
    String qption2;
    String qption3;
    String answer;

    public Question() {
    }

    public Question( String question, String qption1, String qption2, String qption3, String answer) {
        this.question = question;
        this.qption1 = qption1;
        this.qption2 = qption2;
        this.qption3 = qption3;
        this.answer = answer;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQption1() {
        return qption1;
    }

    public void setQption1(String qption1) {
        this.qption1 = qption1;
    }

    public String getQption2() {
        return qption2;
    }

    public void setQption2(String qption2) {
        this.qption2 = qption2;
    }

    public String getQption3() {
        return qption3;
    }

    public void setQption3(String qption3) {
        this.qption3 = qption3;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
