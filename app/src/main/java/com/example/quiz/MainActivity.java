package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pytanie> pytania = new ArrayList<>();
    private TextView textView;
    private Button buttonNastepne;
    private int licznikPytan = 0;
    private Button buttonTak;
    private Button buttonNie;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utworzPytanie();
        textView = findViewById(R.id.textViewTrescPytania);
        przygotujWidokPytania(0);
        buttonNastepne = findViewById(R.id.buttonNastepne);
        buttonNastepne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                licznikPytan++;
                if (licznikPytan == pytania.size()) {
                    int wynik = policzPunkty();
                    textView.setText("Quiz zakończony! Twój wynik: " + Integer.toString(wynik) + " punktów");
                    buttonNastepne.setVisibility(View.INVISIBLE);
                    buttonTak.setVisibility(View.INVISIBLE);
                    buttonNie.setVisibility(View.INVISIBLE);
                } else {
                    przygotujWidokPytania(licznikPytan);
                }
            }
        });

        buttonTak = findViewById(R.id.buttonTak);
        buttonTak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zaznaczOdp(true);
            }
        });
        buttonNie = findViewById(R.id.buttonNie);
        buttonNie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zaznaczOdp(false);
            }
        });

    }
    private int policzPunkty(){
        int punkty = 0;
        for (Pytanie pytanie : pytania) {
            if (pytanie.isCzyUdzielonoPoprawnejOdpowiedzi()){
                punkty++;
            }
        }
        return punkty;
    }
    public void zaznaczOdp(boolean odpowiedz){
        Pytanie pytanie = pytania.get(licznikPytan);
        if(pytanie.isOdpPoprawna() == odpowiedz){
            pytania.get(licznikPytan).setCzyUdzielonoPoprawnejOdpowiedzi();
        }
    }
    private void przygotujWidokPytania(int i) {
        Pytanie pytanie = pytania.get(i);
        textView.setText(pytanie.getTersc());
    }

    private void utworzPytanie() {
        pytania.add(new Pytanie("Czy zespół Metallica jest ze Stanów Zjednoczonych?", "amerykański zespół heavymetalowy i thrashmetalowy założony w Los Angeles w Kalifornii w 1981 roku przez Jamesa Hetfielda i Larsa Ulricha. Uważany za jeden z najważniejszych, najbardziej wpływowych zespołów metalowych lat 80. XX wieku, w latach 90.", true));
        pytania.add(new Pytanie("Czy John Lennon Żyje?", "tak.", true));
        pytania.add(new Pytanie("Czy wokalistą zespołu Pidżama Porno jest Andrzej Kozakiewicz?", "(ur. 13 marca 1965 w Poznaniu) – polski wokalista, poeta i autor tekstów. Kompozytor większości utworów zespołów Strachy na Lachy, Pidżama Porno, Lavina Cox, Ryzyko, Papierosy i Ręce do Góry, z wykształcenia historyk. Teksty „Grabaża” funkcjonują również bez muzyki jako wiersze.", false));
    }
}