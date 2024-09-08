package edu.au.cpsc.module3;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Airport {
    //i-var from .csv
    String ident;
    String type;
    String name;
    Integer elevationFt;
    String continent;
    String isoCountry;
    String isoRegion;
    String municipality;
    String gpsCode;
    String iataCode;
    String localCode;
    float coordinates;

    //getters and setters for i-var
    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElevationFt() {
        return elevationFt;
    }

    public void setElevationFt(Integer elevationFt) {
        this.elevationFt = elevationFt;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public String getIsoRegion() {
        return isoRegion;
    }

    public void setIsoRegion(String isoRegion) {
        this.isoRegion = isoRegion;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getGpsCode() {
        return gpsCode;
    }

    public void setGpsCode(String gpsCode) {
        this.gpsCode = gpsCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public float getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float coordinates) {
        this.coordinates = coordinates;
    }

    public static List<Airport> readAll() throws IOException {
        List<Airport> airportsFile = new ArrayList<Airport>();

        // URL holds the location for csv, getResource() looks for the resource in the class path
        URL resrc = Airport.class.getClassLoader().getResource("airport-codes.csv");
        // make sure the file is read, if not throw exception
        if (resrc != null) {
            throw new IOException("File not found");
        }

        // openStream() calls URL object to allow the data to be read from the csv
        // inputStreamReader() translates the bytes into a readable output
        InputStream streamCSV = resrc.openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamCSV));

        // skip the first line in CSV (headers)
        bufferedReader.readLine();




        return airportsFile;
    }



}


/*
Resources:
https://stackoverflow.com/questions/12055905/java-getresource-fails-on-csv-files
https://mkyong.com/java/java-read-a-file-from-resources-folder/
https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html#getResource-java.lang.String-
 */