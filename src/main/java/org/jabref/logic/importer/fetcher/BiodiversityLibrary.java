package org.jabref.logic.importer.fetcher;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiodiversityLibrary {

    private static final Logger LOGGER = LoggerFactory.getLogger(BiodiversityLibrary.class);

    private static final String API_COLLECTION_URL = "https://www.biodiversitylibrary.org/api3?op=GetCollections&format=json&apikey=";
    private static final String API_KEY = "35c3b825-b2a5-4fd3-8c58-e16a6862a9fe";


    public ResponseCollection getCollections() throws Exception {
        var response = this.fetchData(API_COLLECTION_URL);
        var collections = new Gson().fromJson(response.toString(), ResponseCollection.class);

        if ((collections != null) && collections.getStatus().equals("ok")) {
            LOGGER.info("getCollections: " + collections.toString());
            return collections;
        }

        return null;
    }

    public String fetchData(String apiUrl) throws Exception {
        try {
            URL url = new URL(apiUrl + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Scanner sc = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();

            while (sc.hasNextLine()) {
                response.append(sc.nextLine());
            }

            sc.close();

            LOGGER.info("fetchData: " + response.toString());

            return response.toString();
        } catch (Exception e) {
            throw new Exception();
        }
    }

}

class ResponseCollection {

    private String Status;
    private List<Collection> Result = new ArrayList<>();

    public ResponseCollection() {
    }

    public ResponseCollection(String status) {
        super();
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<Collection> getResult() {
        return Result;
    }

    public void setResult(List<Collection> result) {
        Result = result;
    }

    @Override
    public String toString() {
        return "ResponseCollection [Status=" + Status + ", Result=" + Result + "]";
    }

}

class Collection {

    private Integer CollectionID;
    private String CollectionName;
    private String CollectionDescription;

    public Collection() {
    }

    public Collection(Integer collectionID, String collectionName, String collectionDescription) {
        super();
        CollectionID = collectionID;
        CollectionName = collectionName;
        CollectionDescription = collectionDescription;
    }

    public Integer getCollectionID() {
        return CollectionID;
    }

    public void setCollectionID(Integer collectionID) {
        CollectionID = collectionID;
    }

    public String getCollectionName() {
        return CollectionName;
    }

    public void setCollectionName(String collectionName) {
        CollectionName = collectionName;
    }

    public String getCollectionDescription() {
        return CollectionDescription;
    }

    public void setCollectionDescription(String collectionDescription) {
        CollectionDescription = collectionDescription;
    }

    @Override
    public String toString() {
        return "Collection [CollectionID=" + CollectionID + ", CollectionName=" + CollectionName
               + ", CollectionDescription=" + CollectionDescription + "]";
    }

}