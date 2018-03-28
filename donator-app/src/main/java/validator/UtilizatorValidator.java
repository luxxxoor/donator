package validator;

import domain.GrupaSanguina;
import domain.Sex;
import service.UtilizatorService;

import java.time.LocalDate;

public class UtilizatorValidator {
    private static UtilizatorService utilizatorService = new UtilizatorService();

    // Clasă statică, nu are rost să avem instanțe de Validator
    private UtilizatorValidator(){}

    public static void validate(String cnp, String nume, String prenume, Sex sex, GrupaSanguina grupaSanguina,
                                LocalDate dataNasterii, String domiciliu, String resedinta, String rh,
                                String numeUtilizator, String parola, String repetareParola) throws ValidatorException {
        String erori = "";
        if (cnp.length() != 13) {
            erori += "CNP invalid.\n";
        }
        if (nume.length() == 0) {
            erori += "Nume nemenționat.\n";
        }
        if (prenume.length() == 0) {
            erori += "Preume nemenționat.\n";
        }
        if (sex == null) {
            erori += "Sex nemenționat.\n";
        }
        if (grupaSanguina == null) {
            erori += "Gupa sanguină nemenționată.\n";
        }
        if (dataNasterii == null) {
            erori += "Data nașterii nemenționată.\n";
        }
        if (domiciliu.length() == 0) {
            erori += "Domiciliu nemenționat.\n";
        }
        if (resedinta.length() == 0) {
            erori += "Reședință nemenționată.\n";
        }
        if (rh.length() == 0) {
            erori += "Rh nemenționat.\n";
        }
        if (numeUtilizator.length() == 0) {
            erori += "Nume de utilizator nemenționat.\n";
        }
        if (utilizatorService.getUtilizator(numeUtilizator) != null) {
            erori += "Numele de utilizator e deja folosit.\n";
        }
        if (parola.length() == 0) {
            erori += "Parolă nemenționată.\n";
        }
        if (!parola.equals(repetareParola)) {
            erori += "Parolele nu coincid.\n";
        }

        if (erori.length() > 0)
            throw new ValidatorException(erori);
    }
}
