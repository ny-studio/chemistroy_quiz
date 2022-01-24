package com.example.Chemistryquiz;

public class 金属 extends Quiz_Theme {

    public 金属(){
        super.setquizdata(quizData.getQuizData_kinzoku());
        super.settable_c1("quizdb0");
        super.settable_c2("rightqs0");
        super.setTitletext("金属元素");
        super.setbeforeClass(無機.class);
    }

}