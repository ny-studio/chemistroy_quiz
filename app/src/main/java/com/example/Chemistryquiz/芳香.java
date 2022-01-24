package com.example.Chemistryquiz;

public class 芳香 extends Quiz_Theme {

    public 芳香(){
        super.setquizdata(quizData.getQuizData_houkou());
        super.settable_c1("quizdb3");
        super.settable_c2("rightqs3");
        super.setTitletext("芳香族化合物");
        super.setbeforeClass(有機.class);
    }
}

