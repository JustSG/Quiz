package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pytanie> pytania = new ArrayList<>();
    private TextView textView;
    private Button buttonNastepne;
    private int licznikPytan = 0;
    private Button buttonTak;
    private Button buttonNie;
    private Button buttonPodpowiedz;
    private ImageView  imageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utworzPytanie();
        textView = findViewById(R.id.textViewTrescPytania);
        imageView = findViewById(R.id.imageView);
        buttonNastepne = findViewById(R.id.buttonNastepne);
        przygotujWidokPytania(0);
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
                    buttonPodpowiedz.setVisibility(View.INVISIBLE);
                } else {
                    przygotujWidokPytania(licznikPytan);
                }
            }
        });
        buttonPodpowiedz = findViewById(R.id.buttonPodpowiedz);
        buttonPodpowiedz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PodpowiedzActivity.class); //instancja (jawna)
                intent.putExtra("PODPOWIEDZ",pytania.get(licznikPytan).getPodpowiedz()); //Nazwa zmiennej pisana caps
                startActivity(intent);
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
        //TODO: wyswietlic zdjecie
        imageView.setImageResource(pytanie.getIdZdjecia());
    }

    private void utworzPytanie() {
        pytania.add(new Pytanie("Czy zespół Metallica jest ze Stanów Zjednoczonych?", "Amerykański zespół heavymetalowy i thrashmetalowy założony w Los Angeles w Kalifornii w 1981 roku przez Jamesa Hetfielda i Larsa Ulricha. Uważany za jeden z najważniejszych, najbardziej wpływowych zespołów metalowych lat 80. XX wieku, w latach 90.", true, R.drawable.metallica));
        pytania.add(new Pytanie("Czy John Lennon Żyje?", "tak.", true, R.drawable.john_lennon));
        pytania.add(new Pytanie("Czy wokalistą zespołu Pidżama Porno jest Andrzej Kozakiewicz?", "(Ur. 13 marca 1965 w Poznaniu) – polski wokalista, poeta i autor tekstów. Kompozytor większości utworów zespołów Strachy na Lachy, Pidżama Porno, Lavina Cox, Ryzyko, Papierosy i Ręce do Góry, z wykształcenia historyk. Teksty „Grabaża” funkcjonują również bez muzyki jako wiersze.", false,R.drawable.k_grabowski));
    }
}