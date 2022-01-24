package com.example.Chemistryquiz;

public class 熱化学 extends Quiz_Theme {

    public 熱化学(){
        super.setquizdata(quizData.getQuizData_netsukagaku());
        super.settable_c1("quizdb11");
        super.settable_c2("rightqs11");
        super.setTitletext("熱化学");
        super.setbeforeClass(物質の反応.class);
    }

}