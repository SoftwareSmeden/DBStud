package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DbSQL {

    private Connection connection;
    private final String URL = "jdbc:sqlite:E://IntelliJ Projects/DBStud/src/database/DBStud.db";
    private Scanner scan = new Scanner(System.in);

    DbSQL(){
        connection = null;
        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Opg. 3.1:
    public void createTableStud(){
        try {
            String sql = "CREATE TABLE Studerende (stdnr integer, fnavn text, enavn text, adresse text, postnr text, mobil text, klasse text, PRIMARY KEY (stdnr))";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Opg. 3.1:
    public void createTableFag(){
        try {
            String sql = "CREATE TABLE Fag (fagnr integer, fagnavn text, PRIMARY KEY (fagnr))";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Opg. 3.1:
    public void createTableStudfag(){
        try {
            String sql = "CREATE TABLE Studfag (stdnr integer, fagnr integer, PRIMARY KEY (stdnr,fagnr), FOREIGN KEY (stdnr) REFERENCES Studerende, FOREIGN KEY (fagnr) REFERENCES Fag)";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Opg. 4.1:
    public void indsaetStud(Studerende s){
        try {
            String sql = "INSERT INTO Studerende VALUES (" + s.getStdnr() + ",'" + s.getFnavn() + "','" + s.getEnavn() + "','" + s.getAdresse() + "','" + s.getPostnr() + "','" + s.getMobil() + "','" + s.getKlasse() + "')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Opg. 5.1:
    public void indsaetFag(Fag f){
        try {
            String sql = "INSERT INTO Fag (fagnavn) VALUES ('" + f.getFagnavn() + "')";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Opg. 6.1:
    public void updateStudKlasse(int stdnr, String nyKlasse){
        try {
            String sql = "UPDATE Studerende SET klasse = '" + nyKlasse + "' WHERE stdnr ="+stdnr;
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Opg. 7.1 og 2.2:
    public Studerende soeg(int stdnr){
        Studerende s = new Studerende();
        String sql = "SELECT * FROM Studerende LEFT JOIN Studfag ON Studerende.stdnr=Studfag.stdnr LEFT JOIN Fag ON Studfag.fagnr=Fag.fagnr WHERE Studerende.stdnr = "+stdnr;
        try {
            connection = DriverManager.getConnection(URL);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                s.setStdnr(rs.getInt(1));
                s.setFnavn(rs.getString(2));
                s.setEnavn(rs.getString(3));
                s.setAdresse(rs.getString(4));
                s.setPostnr(rs.getString(5));
                s.setMobil(rs.getString(6));
                s.setKlasse(rs.getString(7));
                Fag f = new Fag(rs.getInt("fagnr"),rs.getString("fagnavn"));
                s.getFagliste().add(f);
            }
            //connection.close();
            stmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return s;
    }

    //Opg. 9.1:
    public ArrayList<Studerende> soegNavn(String enavn){
        ArrayList<Studerende> sArr = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Studerende WHERE enavn = '" + enavn + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Studerende s = new Studerende();
                s.setStdnr(rs.getInt(1));
                s.setFnavn(rs.getString(2));
                s.setEnavn(rs.getString(3));
                sArr.add(s);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sArr;
    }

    //Opg. 1.2:
    public ArrayList<Studfag> soegAlleStud(){
        ArrayList<Studfag> sArr = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Studerende LEFT JOIN Studfag ON Studerende.stdnr = Studfag.stdnr LEFT JOIN Fag ON Fag.fagnr=Studfag.fagnr";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
               Studfag sf = new Studfag();
               sf.getStud().setStdnr(rs.getInt("stdnr"));
               sf.getStud().setFnavn(rs.getString("fnavn"));
               sf.getStud().setEnavn(rs.getString("enavn"));
               sf.getFag().setFagnr(rs.getInt("fagnr"));
               sf.getFag().setFagnavn(rs.getString("fagnavn"));
               sArr.add(sf);
            }
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sArr;
    }

    //Opg. 3.2:
    public void indsaetFag(int stdnr, int fagnr){
        String sql = "INSERT INTO Studfag (stdnr, fagnr) VALUES ("+stdnr+","+fagnr+")";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //Opg. 4.2:
    public ArrayList<Studfag> tilmeldtFag(String fagnavn){
        ArrayList<Studfag> sArr = new ArrayList<>();
        String sql = "SELECT * FROM Studerende INNER JOIN Studfag ON Studerende.stdnr = Studfag.stdnr INNER JOIN Fag ON Fag.fagnr = Studfag.fagnr WHERE fagnavn = '"+fagnavn+"'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Studfag sf = new Studfag();
                sf.getStud().setStdnr(rs.getInt("stdnr"));
                sf.getStud().setFnavn(rs.getString("fnavn"));
                sf.getStud().setEnavn(rs.getString("enavn"));
                sf.getFag().setFagnr(rs.getInt("fagnr"));
                sf.getFag().setFagnavn(rs.getString("fagnavn"));
                sArr.add(sf);
            }
            stmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sArr;
    }

    //Opg. 6.2:
    public ArrayList<Studfag> bestaetFag(String fagnavn){
        ArrayList<Studfag> sArr = new ArrayList<>();
        String sql = "SELECT * FROM Studerende INNER JOIN Studfag ON Studerende.stdnr = Studfag.stdnr INNER JOIN Fag on Fag.fagnr = Studfag.fagnr WHERE fagnavn = '"+fagnavn+"' AND kar >= 2";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Studfag sf = new Studfag();
                sf.getStud().setStdnr(rs.getInt("stdnr"));
                sf.getStud().setFnavn(rs.getString("fnavn"));
                sf.getStud().setEnavn(rs.getString("enavn"));
                sf.setKar(rs.getInt("kar"));
                sArr.add(sf);
            }
            stmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sArr;
    }

    //Opg. 7.2:
    public double gennemsnit(String fagnavn){
        double karSnit = 0.0;
        String sql = "SELECT AVG(kar) FROM Studfag INNER JOIN Fag on Fag.fagnr = Studfag.fagnr WHERE fagnavn = '"+fagnavn+"'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Studfag sf = new Studfag();
                sf.setKar(rs.getDouble("AVG(kar)"));
                karSnit += sf.getKar();
            }
            stmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return karSnit;
    }

    //Opg. 9.2:
    //Admin
    public void visAdminMenu(){
        System.out.println("\n---------MENU---------");
        System.out.println("1. Opret ny studerende");
        System.out.println("2. Opret nyt fag");
        System.out.println("3. Søg efter studerende");
        System.out.println("4. Tilmeld stud et fag");
        System.out.println("5. Log ud");
        System.out.println("6. Luk");
        System.out.println("----------------------");
    }

    //Stud
    public void visStudMenu(){
        System.out.println("\n--------MENU--------");
        System.out.println("1. Brugeroplysninger");
        System.out.println("2. Karakterer");
        System.out.println("3. Log ud");
        System.out.println("4. Luk");
        System.out.println("----------------------");
    }

    //Admin
    public void menuOpretStud(){
        System.out.println("Indtast ny studerendes information");
        Studerende s = new Studerende();
        System.out.print("Fornavn: ");
        s.setFnavn(scan.next());
        System.out.print("Efternavn: ");
        s.setEnavn(scan.next());
        System.out.print("Adresse: ");
        s.setAdresse(scan.next()+" "+ scan.next());
        System.out.print("Postnr: ");
        s.setPostnr(scan.next());
        System.out.print("Mobil: ");
        s.setMobil(scan.next());
        System.out.print("Klasse: ");
        s.setKlasse(scan.next());
        System.out.print("Brugernavn: ");
        s.setBrugernavn(scan.next());
        System.out.print("Password: ");
        s.setPassword(scan.next());
        System.out.print("Stilling: ");
        s.setStilling(scan.next());

        String sqlCheck = "SELECT * FROM Studerende WHERE brugernavn = ?";
        String sqlInsert = "INSERT INTO Studerende (fnavn, enavn, adresse, postnr, mobil, klasse, brugernavn, password, stilling) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DriverManager.getConnection(URL);
            PreparedStatement psCheck = connection.prepareStatement(sqlCheck);
            psCheck.setString(1,s.getBrugernavn());
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
                psInsert.setString(1, s.getFnavn());
                psInsert.setString(2, s.getEnavn());
                psInsert.setString(3, s.getAdresse());
                psInsert.setString(4, s.getPostnr());
                psInsert.setString(5, s.getMobil());
                psInsert.setString(6, s.getKlasse());
                psInsert.setString(7, s.getBrugernavn());
                psInsert.setString(8, s.getPassword());
                psInsert.setString(9, s.getStilling());
                psInsert.executeUpdate();
                psInsert.close();
                System.out.println("Ny studerende er blevet oprettet");
                visAdminMenu();
            }else {
                System.err.println("Brugernavn eksisterer allerede");
                visAdminMenu();
            }
            connection.close();
            psCheck.close();
            rs.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Admin
    public void menuOpretFag(){
        System.out.println("Indtast nyt fagnavn");
        String nf = scan.next();
        Fag nytFag = new Fag(nf.substring(0,1).toUpperCase()+nf.substring(1));
        String sqlCheck = "SELECT * FROM Fag WHERE fagnavn = ?";
        String sqlInsert = "INSERT INTO Fag (fagnavn) VALUES (?)";
        try {
            connection = DriverManager.getConnection(URL);
            PreparedStatement psCheck = connection.prepareStatement(sqlCheck);
            psCheck.setString(1, nytFag.getFagnavn());
            ResultSet rs = psCheck.executeQuery();
            if (!rs.next()){
                PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
                psInsert.setString(1,nytFag.getFagnavn());
                psInsert.executeUpdate();
                psInsert.close();
                System.out.println("Nyt fag er oprettet");
                visAdminMenu();
            }else{
                System.err.println("Fag eksisterer allerede");
                visAdminMenu();
            }
            connection.close();
            psCheck.close();
            rs.close();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //Admin
    public void menuFindStud() throws SQLException {
        System.out.println("Indtast studienummer (stdnr), for at finde en studerende");
        Studerende bruger = soeg(scan.nextInt());
        connection = DriverManager.getConnection(URL);
        if (bruger.getStdnr() != 0) {
            System.out.println("Fandt 1 studerende: ");
            System.out.printf("- %d %s %s %s %s %s %s \nTilmeldte fag: \n",bruger.getStdnr(),bruger.getFnavn(),bruger.getEnavn(),bruger.getAdresse(),bruger.getPostnr(),bruger.getMobil(),bruger.getKlasse());
            for (int i = 0; i <bruger.getFagliste().size(); i++) {
                System.out.println("- "+bruger.getFagliste().get(i).getFagnavn());
            }
            visAdminMenu();
        }else{
            System.err.println("Kunne ikke finde den studerende");
            visAdminMenu();
        }
        connection.close();
    }

    //Admin
    public void menuTilmeldStudFag(){
        System.out.println("Indtast studienummer (stdnr) og et fagnr den studerende skal tilmeldes");
        Studfag sf = new Studfag();
        System.out.print("Studienummer (stdnr): ");
        sf.getStud().setStdnr(scan.nextInt());
        System.out.print("Fagnr: ");
        sf.getFag().setFagnr(scan.nextInt());

        String sqlCheck = "SELECT * FROM Studfag WHERE stdnr = ? AND fagnr = ?";
        String sqlInsert = "INSERT INTO Studfag (stdnr, fagnr) VALUES (?,?)";
        try {
            connection = DriverManager.getConnection(URL);
            PreparedStatement psCheck = connection.prepareStatement(sqlCheck);
            psCheck.setInt(1,sf.getStud().getStdnr());
            psCheck.setInt(2,sf.getFag().getFagnr());
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()){
                PreparedStatement psInsert = connection.prepareStatement(sqlInsert);
                psInsert.setInt(1,sf.getStud().getStdnr());
                psInsert.setInt(2,sf.getFag().getFagnr());
                psInsert.executeUpdate();
                psInsert.close();
                System.out.println("Studerende er tilmeldt nyt fagnr");
                visAdminMenu();
            }else{
                System.err.println("Studerende er allerede tilmeldt dette fagnr");
                visAdminMenu();
            }
            connection.close();
            psCheck.close();
            rs.close();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    //Stud
    public void menuBrugerOplys(String brugernavn) throws SQLException {
        String sqlBruger = "SELECT * FROM Studerende WHERE brugernavn = '" + brugernavn + "'";
        connection = DriverManager.getConnection(URL);
        Statement stmt = connection.createStatement();
        ResultSet rsBruger = stmt.executeQuery(sqlBruger);

        Studerende st = new Studerende();
        while (rsBruger.next()) {
            st.setStdnr(rsBruger.getInt("stdnr"));
            st.setFnavn(rsBruger.getString("fnavn"));
            st.setEnavn(rsBruger.getString("enavn"));
            st.setAdresse(rsBruger.getString("adresse"));
            st.setPostnr(rsBruger.getString("postnr"));
            st.setMobil(rsBruger.getString("mobil"));
            st.setKlasse(rsBruger.getString("klasse"));
            st.setBrugernavn(rsBruger.getString("brugernavn"));
            st.setPassword(rsBruger.getString("password"));
        }
        System.out.printf("Studie-nr:    %d" +
                        "\nFornavn:      %s" +
                        "\nEfternavn:    %s" +
                        "\nAdresse:      %s" +
                        "\nPostnr:       %s" +
                        "\nMobil:        %s" +
                        "\nKlasse:       %s" +
                        "\nBrugernavn:   %s" +
                        "\nPassword:     %s\n", st.getStdnr(), st.getFnavn(), st.getEnavn(), st.getAdresse(), st.getPostnr(), st.getMobil(), st.getKlasse(), st.getBrugernavn(), st.getPassword());

        visStudMenu();
        connection.close();
        stmt.close();
        rsBruger.close();
    }

    //Stud
    public void menuKar(String brugernavn) throws SQLException {
        String sqlKar = "SELECT * FROM Studerende INNER JOIN Studfag on Studerende.stdnr = Studfag.stdnr INNER JOIN Fag on Fag.fagnr = Studfag.fagnr WHERE brugernavn = '" + brugernavn + "'";
        connection = DriverManager.getConnection(URL);
        Statement stmt = connection.createStatement();
        ResultSet rsKar = stmt.executeQuery(sqlKar);

        ArrayList<Studfag> stud = new ArrayList<>();
        while (rsKar.next()) {
            Studfag sf = new Studfag();
            sf.getFag().setFagnavn(rsKar.getString("fagnavn"));
            sf.setKar(rsKar.getInt("kar"));
            stud.add(sf);
        }
        for (Studfag studfag : stud) {
            System.out.printf("Fag: %-20s Karakter: %s\n", studfag.getFag().getFagnavn(), studfag.getKar());
        }
    }

    //Getter
    public String getURL() {
        return URL;
    }
}
