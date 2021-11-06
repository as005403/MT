package com.example.mt_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    private static final String LOG_TAG = "myLogs";
    List<Integer> listOpenCardsImages = new ArrayList<Integer>();
    List<ImageView> listCards = new ArrayList<ImageView>();
    List<ImageView> peepImages = new ArrayList<ImageView>();
    ImageView card1, card2, card3, card4, card5, card6, card7, card8,
            card9, card10, card11, card12, card13, card14, card15, card16;

    Handler h;
    Boolean isPeeped = false;
    Button button;
    Button btnReload;
    Button btnHint;
    private ImageView card = null;
    private ImageView prevCard = null;
    private Integer cardIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReload = findViewById(R.id.newGame);
        btnReload.setOnClickListener(this);
        btnHint = findViewById(R.id.btnHint);
        btnHint.setOnClickListener(this);

        listCards.add(card1 = findViewById(R.id.card1));
        listCards.add(card2 = findViewById(R.id.card2));
        listCards.add(card3 = findViewById(R.id.card3));
        listCards.add(card4 = findViewById(R.id.card4));
        listCards.add(card5 = findViewById(R.id.card5));
        listCards.add(card6 = findViewById(R.id.card6));
        listCards.add( card7 = findViewById(R.id.card7));
        listCards.add( card8 = findViewById(R.id.card8));
        listCards.add( card9 = findViewById(R.id.card9));
        listCards.add( card10 = findViewById(R.id.card10));
        listCards.add( card11 = findViewById(R.id.card11));
        listCards.add( card12 = findViewById(R.id.card12));
        listCards.add( card13 = findViewById(R.id.card13));
        listCards.add( card14 = findViewById(R.id.card14));
        listCards.add( card15 = findViewById(R.id.card15));
        listCards.add( card16 = findViewById(R.id.card16));

        findCardsImage();
        closeCards();

        for (ImageView card : listCards)
        {
            card.setOnTouchListener(this);

        }

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {
                prevCard.setEnabled(true);
                prevCard.setBackgroundColor(0xFF8BC34A);
                prevCard.setImageResource(R.drawable.question);
                prevCard.setTag(R.drawable.question);
                card.setEnabled(true);
                card.setBackgroundColor(0xFF8BC34A);
                card.setImageResource(R.drawable.question);
                card.setTag(R.drawable.question);
                prevCard = null;
                card = null;
                for (ImageView iv : listCards)
                {
                    iv.setEnabled(true);
                }
                btnReload.setClickable(true);
                btnHint.setClickable(true);
            }
        };
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {//открываем карточку

            case MotionEvent.ACTION_UP: // отпускание
                String cardID = String.valueOf(v.getId());
                card = (ImageView) findViewById(Integer.parseInt(cardID));
                cardIndex = listCards.indexOf(card);

                if (card.getTag().equals(R.drawable.question)) { //закрыта ли карточка?

                    card.setBackgroundColor(0xFFFFFFFF);
                    card.setImageResource(listOpenCardsImages.get(cardIndex));
                    card.setTag(listOpenCardsImages.get(cardIndex));

                    if (prevCard != null) {
                        Log.d(LOG_TAG, "card " + card.getTag());
                        Log.d(LOG_TAG, "prevCard " + prevCard.getTag());
                    }

                    if (prevCard == null) { //если первая карточка то
                        prevCard = card;
                        card = null;
                    }
                    else if (prevCard.getTag().equals(card.getTag())) { //если текст второй совпал с текстом первой

                        prevCard = null;
                        card = null;
                    }
                    else { //если текст второй НЕ совпал с текстом первой то

                        Thread t = new Thread(new Runnable() {// Тело второго потока
                            public void run() {
                                for (ImageView iv : listCards) {
                                    iv.setEnabled(false);
                                }
                                btnReload.setClickable(false);
                                btnHint.setClickable(false);
                                waitThread();
                                h.sendEmptyMessage(0);
                            }
                        });
                        t.start();//запуск второго потока
                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        String btnID = String.valueOf(v.getId());
        button = (Button) findViewById(Integer.parseInt(btnID));

        if (button.getId() == btnReload.getId()){// кнопка new Game
            listOpenCardsImages.clear();
            findCardsImage();
            closeCards();
            isPeeped = false;
        }

        if (button.getId() == btnHint.getId()){// кнопка "Подсмотреть"
            if(!isPeeped)
                openHint();
            else
                closeHint();
        }
    }

    private void findCardsImage(){
        for (int i = 0; i < 2; i++){
            listOpenCardsImages.add(R.drawable.bible);
            listOpenCardsImages.add(R.drawable.kawaii);
            listOpenCardsImages.add(R.drawable.rage);
            listOpenCardsImages.add(R.drawable.ezzy);
            listOpenCardsImages.add(R.drawable.punoco);
            listOpenCardsImages.add(R.drawable.rabotyaga);
            listOpenCardsImages.add(R.drawable.smorc);
            listOpenCardsImages.add(R.drawable.trail);
        }
        Collections.shuffle(listOpenCardsImages);
    }

    private void closeCards(){

        for (ImageView card : listCards)
        {
            card.setBackgroundColor(0xFF8BC34A);
            card.setImageResource(R.drawable.question);
            card.setTag(R.drawable.question);
        }
    }

    private void openHint(){
        isPeeped = true;
        for (int i = 0; i < listCards.size(); i++) {
            if(listCards.get(i).getTag().equals(R.drawable.question)) {
                Log.d(LOG_TAG, String.valueOf(i));
                peepImages.add(listCards.get(i));
                listCards.get(i).setBackgroundColor(0xFFFFFFFF);
                listCards.get(i).setImageResource(listOpenCardsImages.get(i));
                listCards.get(i).setTag(listOpenCardsImages.get(i));
            }
        }
        for (ImageView iv : listCards)
        {
            iv.setEnabled(false);
        }
    }

    private void closeHint(){
        isPeeped = false;
        for (ImageView ivPeep : peepImages) {
            ivPeep.setBackgroundColor(0xFF8BC34A);
            ivPeep.setImageResource(R.drawable.question);
            ivPeep.setTag(R.drawable.question);
        }
        peepImages.clear();
        for (ImageView iv : listCards)
        {
            iv.setEnabled(true);
        }
    }

    void waitThread() {
        // пауза - 1 секунда
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}