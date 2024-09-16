package edu.au.cpsc.module4;

import java.io.*;

public class AirlineDatabaseFile {

    public static final File database = new File("Airline-view.dat");

    private static AirlineDatabase airLineDatabase = null;

    public static AirlineDatabase getDatabase() {
        if (airLineDatabase == null) {
            airLineDatabase = loadDatabase();
        }
        return airLineDatabase;

    }

    private static AirlineDatabase loadDatabase() {
        try (InputStream is = new FileInputStream(database)) {
            return AirlineDatabaseIO.load(is);

        }
        catch (IOException | ClassNotFoundException e) {
            return new AirlineDatabase();
        }
    }

    protected static void saveDatabase() {
        try (OutputStream os = new FileOutputStream(database)) {
            AirlineDatabaseIO.save(getDatabase(), os);
        }
        catch (IOException e) {
            System.err.println("Error saving database: " + database);
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
