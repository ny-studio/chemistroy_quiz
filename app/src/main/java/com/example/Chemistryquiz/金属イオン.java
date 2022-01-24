package com.example.Chemistryquiz;

public class 金属イオン extends Quiz_Theme {

    public 金属イオン(){
        super.setquizdata(quizData.getQuizData_kinzoku_ion());
        super.settable_c1("quizdb0_1");
        super.settable_c2("rightqs0_1");
        super.setTitletext("金属イオンの沈殿");
        super.setbeforeClass(無機.class);
    }
}