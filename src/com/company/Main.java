package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //Bruges til at ændre på tekstens farve i linje 323.
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws SQLException {
        //Opgaveark 25/11/21
        //Opg. 1.1:
        //Opret en klasse studerende.
        //Opret en klasse fag.
        //Opret en klasse studfag.
        //Programmer konstruktør/konstruktører samt set og get metoder til hver af de 3 klasse.
        System.out.println("Opg. 1.1:");
        System.out.println("Opretter klasserne Studerende, Fag og Studfag\n");

        //Opg. 2.1:
        //Opret en klasse DbSql.
        System.out.println("Opg. 2.1:");
        System.out.println("Opretter DbSQL klasse med metoder\n");

        //Opg. 3.1:
        //Indsæt 3 metoder i DbSql, som kan oprette hver af de 3 tabeller i den database du arbejder med.
        //Det er de tabeller som vi arbejdede med sidste gang, som nu skal oprettes fra Java-program: studerende, fag, studfag.
        //Kald disse 3 metoder i Main for at oprette hver af de 3 tabeller i din database.
        System.out.println("Opg. 3.1:");
        System.out.println("Medtoder indsættes og tabeller oprettes\n");
        DbSQL dbSQL = new DbSQL();
     // Create new table
     // dbSQL.createTableStud();
     // dbSQL.createTableFag();
     // dbSQL.createTableStudfag();

        //Opg. 4.1:
        //Lav en metode indsaetstud i DbSql-klassen, som kan indsætte en studerende i tabellen studerende.
        //Opret et objekt af klassen studerende i Main.
        //Indsæt værdier for den studerende.
        //Kald metoden indsaetstud i Main for at indsætte den studerende i databasen.
        //Opret nogle flere studerende i databasen på samme måde.
        System.out.println("Opg. 4.1:");
        System.out.println("Studerende indsættes i tabel\n");
        Studerende stud1 = new Studerende(1111,"Torben","Nørgaard","Femøvej 3","4700","44941232","DatSem1","tbn","123","admin" );
     // dbSQL.indsaetStud(stud1);
        Studerende stud2 = new Studerende(1112,"Daniel","Hansen","Gavnøvej 32","4700","26253108","DatSem1","dan","456","stud" );
     // dbSQL.indsaetStud(stud2);
        Studerende stud3 = new Studerende(1113,"Arne","Jensen","Havnegade 19","4700","66182121","DatSem1","arn","789","stud" );
     // dbSQL.indsaetStud(stud3);

        //Opg. 5.1:
        //Lav en metode indsaetfag i DbSql-klassen, som kan indsætte et fag i tabellen fag.
        //Opret et objekt af klassen fag i Main.
        //Indsæt værdier for faget.
        //Kald metoden indsaetfag i Main.
        //Opret nogle flere fag i databasen på samme måde.
        System.out.println("Opg. 5.1:");
        System.out.println("Fag indsættes i tabel\n");
        Fag fag1 = new Fag(21,"Programmering");
     // dbSQL.indsaetFag(fag1);
        Fag fag2 = new Fag(22,"System Udvikling");
     // dbSQL.indsaetFag(fag2);
        Fag fag3 = new Fag(23,"Virksomhed");
     // dbSQL.indsaetFag(fag3);

        //Opg. 6.1:
        //Lav en metode updatestudklasse i DbSql-klassen, som kan opdatere en studerendes klasse.
        //Kald metoden i Main.
        System.out.println("Opg. 6.1:");
        System.out.println("En studerendes klasse opdateres\n");
        dbSQL.updateStudKlasse(stud1.getStdnr(),"DatSem1");

        //Opg. 7.1:
        //Lav en metode soegstud i DbSql-klassen, som kan returnere den studerende der søges på.
        //Kald metoden i Main og udskriv data for den studerende.
        System.out.println("Opg. 7.1:");
        Studerende enkeltStud;
        enkeltStud = dbSQL.soeg(1113);
        System.out.printf("%s %s %s %s %s %s %s\n",
                enkeltStud.getStdnr(),
                enkeltStud.getFnavn(),
                enkeltStud.getEnavn(),
                enkeltStud.getAdresse(),
                enkeltStud.getPostnr(),
                enkeltStud.getMobil(),
                enkeltStud.getKlasse());

        //Opg. 8.1:
        //Opgave udført med ArrayList istedet for studcontainer klasse.
        System.out.println("\nOpg. 8.1:");
        ArrayList<Studerende> sList = new ArrayList<>();
        sList.add(stud1);
        sList.add(stud2);
        sList.add(stud3);
        for (Studerende studerende : sList) {
            System.out.format("%s %s %s %s %s %s %s",
                    studerende.getStdnr(),
                    studerende.getFnavn(),
                    studerende.getEnavn(),
                    studerende.getAdresse(),
                    studerende.getPostnr(),
                    studerende.getMobil(),
                    studerende.getKlasse() + "\n");
        }

        //Opg. 9.1:
        //Lav en metode soegnavn i DbSql-klassen, som returnerer en ArrayList<> med de studerende med et givet efternavn.
        System.out.println("\nOpg. 9.1:");
        sList.clear();
        sList = dbSQL.soegNavn("Jensen");
        for (int i = 0; i < sList.size() ; i++) {
            System.out.printf("%s %s %s\n",sList.get(i).getStdnr(),sList.get(i).getFnavn(),sList.get(i).getEnavn());
        }

        //Opgaveark 03/12/21
        //Opg. 1.2:
        //Opret en funktion i DbSql, som søger oplysninger om alle studerende, også hvilke fag de er
        //tilmeldt. Kald funktionen i main og udskriv alle oplysningerne til skærm.
        System.out.println("\nOpgaveark 03/12/21");
        System.out.println("Opg. 1.2:");
        ArrayList<Studfag> sfList = dbSQL.soegAlleStud();
        int checkStdnr;
        int setStdnr = 0;
        int sfListIndex = 0;
        for (int i = 0; i < sfList.size(); i++) {
                checkStdnr = sfList.get(i).getStud().getStdnr();
                if (checkStdnr != setStdnr) {
                    System.out.printf("\nNavn: %-8s Fag: ", sfList.get(i).getStud().getFnavn());
                    setStdnr = sfList.get(i).getStud().getStdnr();
                }
            for (int j = 0; j < 1; j++) {
                if (sfList.get(sfListIndex).getFag().getFagnr() != 0) {
                    System.out.printf("%s,",sfList.get(sfListIndex).getFag().getFagnavn());
                    sfListIndex++;
                }
            }
        }

        //Opg. 2.2:
        //Udvid funktionen public studerende soeg(int stdnr) i DbSql, så der også returneres hvilke, fag den studerende er tilmeldt.
        System.out.println("\nOpg 2.2:");
        Studerende s1;
        s1 = dbSQL.soeg(1111);
        System.out.printf("%d %s %s %s %s %s %s - Tilmeldt fag: ",s1.getStdnr(),s1.getFnavn(),s1.getEnavn(),s1.getAdresse(),s1.getPostnr(),s1.getMobil(),s1.getKlasse());
        for (int i = 0; i < s1.getFagliste().size(); i++) {
                System.out.print(s1.getFagliste().get(i).getFagnavn());
            if (i < 2 && s1.getFagliste().size() > 1)
                System.out.print(", ");
        }

        //Opg 3.2:
        //Opret en funktion indsaetfag, som kan tilmelde et fag til en studerende.
        //public void indsaetfag(int stdnr,int fagnr)
        //Test funktionen i main.
        System.out.println("\n\nOpg. 3.2:");
     // dbSQL.indsaetFag(1112,22);
        Studfag sf1 = new Studfag(1111,21);
     // dbSQL.indsaetFag(sf1);
        System.out.println("Fag indsat ved brug af 'stdnr' og 'fagnr'");

        //Opg. 4.2:
        //Lav en funktion i DbSql, som kan returnere alle studerende der er tilmeldt et givet fag.
        //public ArrayList<> tilmeldtfag(String fagnavn)
        //Test funktionen i main.
        System.out.println("\nOpg. 4.2:");
        sfList.clear();
        sfList = dbSQL.tilmeldtFag("System Udvikling");
            for (Studfag studfag : sfList) {
                System.out.printf("%-5s %-8s %-12s %-2s %s\n",
                 studfag.getStud().getStdnr()
                ,studfag.getStud().getFnavn()
                ,studfag.getStud().getEnavn()
                ,studfag.getFag().getFagnr()
                ,studfag.getFag().getFagnavn());
            }

        //Opg. 5.2:
        //Udvid tabellen studfag med et felt kar, som indeholder den karakter den studerende har fået i det pågældende fag.
        //Alter table.
        //Indsæt karakterer for de studerende fag.
        //Tilret funktionerne som anvender tabellen studfag.
        //Test i main.
        System.out.println("\nOpg. 5.2:");
        System.out.println("Opgave udført i konsollen");

        //Opg. 6.2:
        //Lav en funktion public ArrayList<> bestaetfag(String fagnavn), som returnerer de studerende, der er bestået i et givet fag.
        //Test i main.
        System.out.println("\nOpg. 6.2:");
        sfList.clear();
        sfList = dbSQL.bestaetFag("System Udvikling");
        System.out.println("Bestået Studerende:");
            for (Studfag studfag : sfList) {
                    System.out.printf("%d %s %-10s Karakter: %.0f\n",
                            studfag.getStud().getStdnr(),
                            studfag.getStud().getFnavn(),
                            studfag.getStud().getEnavn(),
                            studfag.getKar());
            }

        //Opg. 7.2:
        //Lav en funktion public double gennemsnit(String fagnavn), som returnerer gennemsnitskarakteren i et givet fag.
        //Test i main.
        System.out.println("\nOpg. 7.2:");
        String fagnavn = "Programmering";
        double karSnit;
        karSnit = dbSQL.gennemsnit(fagnavn);
        System.out.println("Gennemsnitskarakteren for faget '"+fagnavn+"' er: "+karSnit);

        //Opg. 8.2:
        //Udvid tabellen studerende med felterne brugernavn og password.
        //Indsæt værdier for de studerende på disse 2 felter.
        System.out.println("\nOpg. 8.2:");
        System.out.println("Udført i konsollen ved brug af 'ALTER TABLE Studfag ADD kar'");

        //Opg. 9.2:
        //Opret i main en menu, hvor brugeren kan vælge forskellige søgninger.
        //Brugeren skal først taste brugernavn og password.
        //Hvis brugeren indtaster en korrekt kode, skal menuen vises.
        System.out.println("\nOpg. 9.2:");
        System.out.println("Indtast brugernavn og password");
        menu();
    }

    //Opg. 9.2 fortsat:
    public static void menu() throws SQLException {
        DbSQL dbSQL = new DbSQL();
        Studerende bruger = null;
        Scanner input = new Scanner(System.in);

        System.out.print("\n---LOGIN---\n");
        System.out.print("Brugernavn: ");
        String brugernavn = input.next();
        System.out.print("Password: ");
        String pass = input.next();
        System.out.println("---------");

        String sql = "SELECT * FROM Studerende WHERE brugernavn = ? AND password = ?";
        Connection conn = DriverManager.getConnection(dbSQL.getURL());
        PreparedStatement preStmt = conn.prepareStatement(sql);
        preStmt.setString(1,brugernavn);
        preStmt.setString(2,pass);
        ResultSet rs = preStmt.executeQuery();

        if (rs.next()){
            String stilling = rs.getString("stilling");
            bruger = new Studerende(brugernavn,pass,stilling);
            preStmt.close();
            conn.close();
            rs.close();
        }

        if (bruger != null){
            switch (bruger.getStilling()) {
                case "admin" -> {
                    System.out.println("Du er logget ind, som admin");
                    dbSQL.visAdminMenu();
                    boolean koer = true;
                    while (koer){
                        switch (input.next()){
                            case "1" -> {
                                dbSQL.menuOpretStud();
                            }
                            case "2" -> {
                                dbSQL.menuOpretFag();
                            }
                            case "3" -> {
                                dbSQL.menuFindStud();
                            }
                            case "4" -> {
                                dbSQL.menuTilmeldStudFag();
                            }
                            case "5" -> {
                                menu();
                            }
                            case "6" -> {
                                System.out.println("Programmet lukker ned");
                                System.exit(1);
                                koer = false;
                            }
                            default -> {
                                System.err.println("Ugyldig indtastning!");
                            }
                        }
                    }
                }
                case "stud" -> {
                    System.out.println("Du er logget ind, som studerende");
                    dbSQL.visStudMenu();
                    boolean koer = true;
                    while (koer){
                        switch (input.next()){
                            case "1" -> {
                                dbSQL.menuBrugerOplys(brugernavn);
                            }
                            case "2" -> {
                                dbSQL.menuKar(brugernavn);
                            }
                            case "3" -> {
                                menu();
                            }
                            case "4" -> {
                                System.out.println("Programmet lukker ned");
                                System.exit(1);
                                koer = false;
                            }
                            default -> {
                                System.err.println("Ugyldig indtastning!");
                            }
                        }
                    }
                }
            }
        }else{
            System.out.println(ANSI_RED+"Forkert 'brugernavn' eller 'password'"+ANSI_RESET);
            menu();
        }
    }
}
