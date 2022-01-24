package com.example.Chemistryquiz;

public class 非金属 extends Quiz_Theme {

    public 非金属(){
        super.setquizdata(quizData.getQuizData_hikinzoku());
        super.settable_c1("quizdb1");
        super.settable_c2("rightqs1");
        super.setTitletext("非金属元素");
        super.setbeforeClass(無機.class);
    }

}