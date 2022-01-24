package com.example.Chemistryquiz;

public class 合成高分子 extends Quiz_Theme {

    public 合成高分子(){
        super.setquizdata(quizData.getQuizData_gousei());
        super.settable_c1("quizdb5");
        super.settable_c2("rightqs5");
        super.setTitletext("合成高分子化合物");
        super.setbeforeClass(高分子.class);
    }
}