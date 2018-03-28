package service;

import domain.GrupaSanguina;
import domain.Sex;
import domain.Utilizator;

public class UtilizatorService {

    public Object getUtilizator(String numeUtilizator) {
        return null;
    }

    public Utilizator creareCont(String cnp, String nume, String prenume, Sex sex, GrupaSanguina grupaSanguina, String domiciliu, String resedinta, String rh, String numeUtilizator, String parola, String repetareParola) {
        System.out.println("UTILIZATOR CREAT în UtilizatorService !!!");
        return new Utilizator(cnp, nume, prenume, sex, grupaSanguina, domiciliu, resedinta, rh,
                numeUtilizator, parola, repetareParola);
    }
}
