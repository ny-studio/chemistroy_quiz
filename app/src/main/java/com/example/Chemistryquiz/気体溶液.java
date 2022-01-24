package com.example.Chemistryquiz;

public class 気体溶液 extends Quiz_Theme {

    public 気体溶液() {
        super.setquizdata(quizData.getQuizData_kitai());
        super.settable_c1("quizdb6");
        super.settable_c2("rightqs6");
        super.setTitletext("気体・溶液の性質");
        super.setbeforeClass(物質の状態平衡.class);
    }
}