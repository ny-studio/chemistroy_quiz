package com.example.Chemistryquiz;

public class 固体 extends Quiz_Theme {

    public 固体(){
        super.setquizdata(quizData.getQuizData_kotai());
        super.settable_c1("quizdb8");
        super.settable_c2("rightqs8");
        super.setTitletext("固体の性質");
        super.setbeforeClass(物質の状態平衡.class);
    }
}