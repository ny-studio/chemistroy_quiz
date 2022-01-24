package com.example.Chemistryquiz;

public class 物質の構成 extends Quiz_Theme {

    public 物質の構成(){
        super.setquizdata(quizData.getQuizData_bussitu());
        super.settable_c1("quizdb7");
        super.settable_c2("rightqs7");
        super.setTitletext("物質の状態・構成");
        super.setbeforeClass(物質の状態平衡.class);
    }
}