package me.krzyzek.tomaszkrzyzek.randkiuser;

public class Osoba {
    String imie;
    String nazwisko;
    String miasto;
    int wiek;
    String plec;
    int wzrost;
    String orientacja;
    String zawod;
    String wyznanie;
    String budowaCiala;
    String poCo;
    String stanCywilny;
    String preferencje;
    String alkohol;

    public Osoba(String imie, String nazwisko, String miasto, int wiek, String plec, int wzrost,
                 String orientacja, String zawod, String wyznanie, String budowaCiala,
                 String poCo, String stanCywilny, String preferencje, String alkohol) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.miasto = miasto;
        this.wiek = wiek;
        this.plec = plec;
        this.wzrost = wzrost;
        this.orientacja = orientacja;
        this.zawod = zawod;
        this.wyznanie = wyznanie;
        this.budowaCiala = budowaCiala;
        this.poCo = poCo;
        this.stanCywilny = stanCywilny;
        this.preferencje = preferencje;
        this.alkohol = alkohol;
    }
}
