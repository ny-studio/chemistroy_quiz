package com.example.Chemistryquiz;

public class 法則 extends Quiz_Theme {

    public 法則(){
        super.setquizdata(quizData.getQuizData_housoku());
        super.settable_c1("quizdb16");
        super.settable_c2("rightqs16");
        super.setTitletext("法則");
        super.setbeforeClass(法則反応など.class);
    }

}
