package com.example.QuizApp.Model;

public class QuestionWrapper {
        private int id;
        private String question;
        private String option1;
        private String option2;
        private String option3;
        private String option4;

        public QuestionWrapper( int id,String question, String optionA, String optionB, String optionC, String optionD) {

           // System.out.println(qNo,question,optionA);
            this.id=id;
            this.question = question;
            this.option1 = optionA;
            this.option2 = optionB;
            this.option3 = optionC;
            this.option4 = optionD;
        }


    public int getId() {
        return id;
    }
    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }
}
