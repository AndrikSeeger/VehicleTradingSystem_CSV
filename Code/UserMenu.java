import Dateien.DataFile;
import Dateien.PrintableObject;
import Listen.Filter;
import Listen.ListeAktionen;
import Listen.Printer;
import Listen.Sorter;
import Exceptions.*;
import Sellers.*;
import Sellers.Exceptions.NoUserLoggedIn;
import Vehicles.*;

import java.io.File;
import java.nio.file.NoSuchFileException;
import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.Scanner;

import Menu.*;

import static Dateien.Csv.delimiter;

/**
 * Klasse für GUI Funktionalität
 *
 * @author 1319658
 * @version 10.3
 */
public class UserMenu {

    public static void main(String[] args) {
        userMenu();
    }

    public static void userMenu() {
        int maxFahrzeuge = 10; //Maximale Anzahl an Fahrzeugen pro Nutzer


        int wahl = -1;
        Long keyAcc = -1L; //Key des aktuell angemeldeten Accounts
        IdNummer idSupp;
        String pathFahrzeuge = "." + File.separator + "verzeichnisFahrzeuge.csv";
        String pathAccounts = "." + File.separator + "verzeichnisAccounts.csv";

        File dataFahrzeuge = new File(pathFahrzeuge);
        File dataAccounts = new File(pathAccounts);

        DataFile.createFile(dataFahrzeuge, "Es wurde eine neue Fahrzeug-Liste erstellt.");
        DataFile.createFile(dataAccounts, "Es wurde eine neue Account-Liste erstellt.");


        DataFile fahrzeugDatei;
        DataFile accountDatei;

        try
        {
            fahrzeugDatei = new DataFile(dataFahrzeuge);
            accountDatei = new DataFile(dataAccounts);
        }
        catch (NoSuchFileException e)
        {
            e.printStackTrace();
            throw new RuntimeException("Liste wurde gelöscht!");
        }


        ArrayList<FahrzeugBasis> fahrzeuge = ListeAktionen.createList(fahrzeugDatei, delimiter);
        ArrayList<AccountBasis> accounts = ListeAktionen.createList(accountDatei, delimiter);

        idSupp = initalizeId(accountDatei, accounts);

        MenuText.message("Willkommen beim Fahrzeugmarkt!");

        MenuText.message("Hinweis: Das Zeichen '" + delimiter + "' darf bei keiner Eingabe verwendet werden");

        while(true) {
            try {
                MenuText.printBlanc();
                MenuText.menuHaupt();

                switch (getNumber(0, 7))
                {
                    case 0:
                        boolean goBack = false;
                        ArrayList<FahrzeugBasis> resultList = fahrzeuge;


                        while(true)
                        {
                            Printer.printList(combineLists(resultList, accounts));
                            //ListeAktionen.printList(resultList); //,accounts)
                            MenuText.printBlanc();
                            MenuText.printBlanc();
                            MenuText.message("- 0 = Zurück --- 1 = Sortieren --- 2 = Filtern -");
                            switch (getNumber(0, 2))
                            {
                                case 0:
                                    throw new AbortActionException();
                                    //goBack = true;
                                    //break;
                                case 1:
                                    MenuText.menuSortieren();
                                    resultList = Sorter.sortList(resultList, FahrzeugVergleicher.returnCompInfo(getNumber(0, 7)));
                                    break;
                                case 2: //LKW // Auto
                                    MenuText.message("- 0 = Zurück --- 1 = Auto --- 2 = Lkw -");
                                    switch (getNumber(0, 2))
                                    {
                                        case 0:
                                            MenuText.printBlanc();
                                            MenuText.message("zurück...");
                                            break;
                                        case 1:
                                            MenuText.printBlanc();
                                            MenuText.menuFilternBasis();
                                            MenuText.menuFilterAuto();
                                            MenuText.printBlanc();
                                            resultList = switch (getNumber(0, 8)) {
                                                case 0 -> Filter.filterList(FahrzeugFilter.getFilter("getMarke", Auto.class), resultList);
                                                case 1 -> Filter.filterList(FahrzeugFilter.getFilter("getModell", Auto.class), resultList);
                                                case 2 -> Filter.filterList(FahrzeugFilter.getFilter("getPreis", Auto.class), resultList);
                                                case 3 -> Filter.filterList(FahrzeugFilter.getFilter("getLeistung", Auto.class), resultList);
                                                case 4 -> Filter.filterList(FahrzeugFilter.getFilter("getOdometer", Auto.class), resultList);
                                                case 5 -> Filter.filterList(FahrzeugFilter.getFilter("getGewicht", Auto.class), resultList);
                                                case 6 -> Filter.filterList(FahrzeugFilter.getFilter("getErstzulassung", Auto.class), resultList);
                                                case 7 -> Filter.filterList(FahrzeugFilter.getFilter("getFahrzeugArt", Auto.class), resultList);
                                                case 8 -> Filter.filterList(FahrzeugFilter.getFilter("getSportPaket", Auto.class), resultList);
                                                default -> resultList;
                                            };
                                            break;
                                        case 2:
                                            MenuText.printBlanc();
                                            MenuText.menuFilternBasis();

                                            MenuText.message("7 = Zuladung filtern: ");
                                            MenuText.message("8 = Zuglast filtern: ");
                                            MenuText.message("9 = Hydraulik (ja/nein): ");

                                            MenuText.printBlanc();
                                            resultList = switch (getNumber(0, 9)) {
                                                case 0 -> Filter.filterList(FahrzeugFilter.getFilter("getMarke", Lkw.class), resultList);
                                                case 1 -> Filter.filterList(FahrzeugFilter.getFilter("getModell", Lkw.class), resultList);
                                                case 2 -> Filter.filterList(FahrzeugFilter.getFilter("getPreis", Lkw.class), resultList);
                                                case 3 -> Filter.filterList(FahrzeugFilter.getFilter("getLeistung", Lkw.class), resultList);
                                                case 4 -> Filter.filterList(FahrzeugFilter.getFilter("getOdometer", Lkw.class), resultList);
                                                case 5 -> Filter.filterList(FahrzeugFilter.getFilter("getGewicht", Lkw.class), resultList);
                                                case 6 -> Filter.filterList(FahrzeugFilter.getFilter("getErstzulassungString", Lkw.class), resultList);
                                                case 7 -> Filter.filterList(FahrzeugFilter.getFilter("getZuladung", Lkw.class), resultList);
                                                case 8 -> Filter.filterList(FahrzeugFilter.getFilter("getZuglast", Lkw.class), resultList);
                                                case 9 -> Filter.filterList(FahrzeugFilter.getFilter("getHydraulik", Lkw.class), resultList);
                                                default -> resultList;
                                            };
                                            break;
                                    }

                                    //menuAuto();


                                    //menuFiltern();
                                    break;
                            }
                        }
                    case 1:
                        AccountAktionen.angemeldet(keyAcc);
                        if(FahrzeugSuche.fahrzeugeZuKey(fahrzeuge, keyAcc).size() < maxFahrzeuge)
                        {
                            MenuText.menuInserieren();
                            switch (getNumber(0, 2))
                            {
                                case 0:
                                    MenuText.printBlanc();
                                    MenuText.message("zurück...");
                                    break;
                                case 1:
                                    ListeAktionen.addObject(fahrzeugDatei, fahrzeuge, (Auto) Menu.createNewObject("Vehicles.Auto", keyAcc, delimiter));
                                    break;
                                case 2:
                                    ListeAktionen.addObject(fahrzeugDatei, fahrzeuge, (Lkw) Menu.createNewObject("Vehicles.Lkw", keyAcc, delimiter));
                                    break;
                            }
                        }
                        else
                        {
                            MenuText.message("Maximale Anzahl von " + maxFahrzeuge + " Inseraten erreicht!");
                        }
                        break;
                    case 2:
                        AccountAktionen.angemeldet(keyAcc);
                        resultList = FahrzeugSuche.fahrzeugeZuKey(fahrzeuge, keyAcc);
                        if(resultList.size() == 0)
                        {
                            MenuText.message("Keine Fahrzeuge inseriert!");
                            break;
                        }
                        Printer.printList(resultList);
                        break;
                    case 3:
                            AccountAktionen.angemeldet(keyAcc);
                            resultList = FahrzeugSuche.fahrzeugeZuKey(fahrzeuge, keyAcc);
                            Printer.printListWithNumbers(resultList);
                            if(resultList.size() == 0)
                            {
                                MenuText.message("Keine Fahrzeuge inseriert!");
                                break;
                            }
                            wahl = getNumber(0, resultList.size()-1);
                            MenuText.message("- 0 = Zurück --- 1 = Löschen --- 2 = Ersetzen -");
                            switch (getNumber(0, 2))
                            {
                                case 0:
                                    MenuText.printBlanc();
                                    MenuText.message("zurück...");
                                    break;
                                case 1:
                                    ListeAktionen.deleteObject(fahrzeugDatei, fahrzeuge, resultList.get(wahl));
                                    MenuText.message("Inserat wurde gelöscht");
                                    break;
                                case 2:
                                    String classVehicle = resultList.get(wahl).getClass().getSimpleName();
                                    FahrzeugBasis newVehicle;
                                    switch(classVehicle)
                                    {
                                        case "Auto": newVehicle = (Auto) Menu.createNewObject("Vehicles.Auto", keyAcc, delimiter); break;
                                        case "Lkw":  newVehicle = (Lkw) Menu.createNewObject("Vehicles.Lkw", keyAcc, delimiter); break;
                                        default:
                                            throw new IllegalStateException("Unexpected value: " + classVehicle);
                                    }

                                    ListeAktionen.replaceObject(fahrzeugDatei, fahrzeuge, resultList.get(wahl), newVehicle);
                            }

                        break;

                    case 4:
                        if(keyAcc == -1L)
                        {
                            keyAcc = AccountAktionen.login(accounts);
                        }
                        else
                        {
                            MenuText.message("User bereits angemeldet!");
                        }
                        break;
                    case 5:
                        keyAcc = AccountAktionen.logout(keyAcc);
                        break;
                    case 6:
                        MenuText.menuAccountErstellen();
                        switch (getNumber(0, 2))
                        {
                            case 0:
                                throw new AbortActionException();
                            case 1:
                                AccountBasis erstellterAccount = (Account) Menu.createNewObject("Sellers.Account", idSupp, delimiter);
                                addAccount(accountDatei, accounts, erstellterAccount);
                                break;
                            case 2:
                                AccountBasis erstellterAccountPro = (AccountPro) Menu.createNewObject("Sellers.AccountPro", idSupp, delimiter);
                                addAccount(accountDatei, accounts, erstellterAccountPro);
                                break;
                        }
                        break;
                    case 7:
                            AccountAktionen.angemeldet(keyAcc);
                            ListeAktionen.deleteObjects(fahrzeugDatei, fahrzeuge, FahrzeugSuche.fahrzeugeZuKey(fahrzeuge, keyAcc));
                            ListeAktionen.deleteObject(accountDatei, accounts, AccountAktionen.getAcc(accounts, keyAcc));
                            keyAcc = -1L;
                            MenuText.message("Account wurde gelöscht");
                        break;
                }
            }
            catch (AbortActionException e) //Nutzer will Eingabe abbrechen
            {
                MenuText.printBlanc();
                MenuText.message("zurück...");
            }
            catch (NoUserLoggedIn e)
            {
                MenuText.printBlanc();
                MenuText.message("Kein Nutzer angemeldet!");
            } catch (NoSuchObjectException e) {
                e.printStackTrace();
                throw new RuntimeException("Fehler in Liste");
            }
        }
    }

    private static <T1 extends FahrzeugBasis & PrintableObject, T2 extends AccountBasis & PrintableObject, T3 extends PrintableObject> ArrayList<T3> combineLists (ArrayList<T1> fahrzeuge, ArrayList<T2> accounts)
    {
        ArrayList<T3> combinedList = new ArrayList<>();
        for (T1 fzg : fahrzeuge) {
            combinedList.add((T3) fzg);
            combinedList.add((T3) AccountAktionen.getAcc(accounts, fzg.getKey()));
        }

        return combinedList;
    }


    private static IdNummer initalizeId(DataFile accountDatei, ArrayList<AccountBasis> accounts) {
        IdNummer idSupp;
        if(accounts.size() == 0) //Liste neu erstellt --> Keine Id-Supplier in Liste
        {
            idSupp = new IdNummer(-1L);
            ListeAktionen.addObject(accountDatei, accounts, idSupp);
        }
        else
        {
            idSupp = (IdNummer) accounts.get(0); //Erstes Element IdSupplier
        }
        return idSupp;
    }

    private static void addAccount(DataFile accountDatei, ArrayList<AccountBasis> accounts, AccountBasis erstellterAccount) {
        if(AccountAktionen.checkExist(accounts, erstellterAccount) == -1L)
        {
            ListeAktionen.addObject(accountDatei, accounts, erstellterAccount);
            MenuText.printBlanc();
            MenuText.message("Account erstellt!");

        }
        else
        {
            MenuText.message("Fehler. E-Mail-Adresse bereits vorhanden.");
        }
    }

    //Abfrage Nummer von Konsole. Min mindestens 0 und Max maximal 9. Gibt entweder Nummer oder -1 zurück
    private static int getNumber(int min , int max)
    {
        Scanner scanner = new Scanner(System.in);
        String regex = "[" + min + "-" + max + "]";
        String input;
        int wahl = -1;

        while(wahl == -1)
        {
            System.out.print("Nummer eingeben: ");
            input = scanner.nextLine();
            if(input.matches(regex))
            {
                wahl = Integer.parseInt(input);
            }
            else
            {
                MenuText.message("Falsche Eingabe!");
            }
        }
        MenuText.printBlanc();
        return wahl;
    }

}
