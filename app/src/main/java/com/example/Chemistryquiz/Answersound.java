package com.example.Chemistryquiz;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class Answersound {
    private SoundPool soundPool;
    private int soundright;
    private int soundwrong;

    public Answersound(Context context) {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundright = soundPool.load(context, R.raw.crrect_answer2, 1);
        soundwrong = soundPool.load(context, R.raw.blip04, 1);
    }

    public void 正解() {
        soundPool.play(soundright, 1.0f, 1.0f, 0, 0, 1.0f);
    }
    public void 不正解() {
        soundPool.play(soundwrong, 1.0f, 1.0f, 0, 0, 1.0f);
    }
}
