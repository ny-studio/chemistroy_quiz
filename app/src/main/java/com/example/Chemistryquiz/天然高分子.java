package com.example.Chemistryquiz;

public class 天然高分子 extends Quiz_Theme {

    public 天然高分子(){
        super.setquizdata(quizData.getQuizData_tennen());
        super.settable_c1("quizdb4");
        super.settable_c2("rightqs4");
        super.setTitletext("天然高分子化合物");
        super.setbeforeClass(高分子.class);
    }

}