package com.example.Chemistryquiz;

public class 電気分解 extends Quiz_Theme {

    public 電気分解(){
        super.setquizdata(quizData.getQuizData_denki());
        super.settable_c1("quizdb10");
        super.settable_c2("rightqs10");
        super.setTitletext("電池・電気分解");
        super.setbeforeClass(物質の反応.class);
    }

}