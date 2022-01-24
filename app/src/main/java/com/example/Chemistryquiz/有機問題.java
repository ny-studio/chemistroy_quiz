package com.example.Chemistryquiz;

public class 有機問題 extends Quiz_Theme {

    public 有機問題(){
        super.setquizdata(quizData.getQuizData_yuuki());
        super.settable_c1("quizdb2");
        super.settable_c2("rightqs2");
        super.setTitletext("有機化合物");
        super.setbeforeClass(有機.class);
    }
}
