package com.example.Chemistryquiz;

public class 酸化還元 extends Quiz_Theme {

    public 酸化還元(){
        super.setquizdata(quizData.getQuizData_sankakangen());
        super.settable_c1("quizdb9");
        super.settable_c2("rightqs9");
        super.setTitletext("酸化還元反応");
        super.setbeforeClass(物質の反応.class);
    }
}