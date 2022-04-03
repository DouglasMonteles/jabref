package org.jabref.logic.importer.fetcher;

import org.jabref.testutils.category.FetcherTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@FetcherTest
class BiodiversityLibraryTest {

    private BiodiversityLibrary finder;
    private String url;
    private String fakeUrl;

    @BeforeEach
    void setUp() {
        finder = new BiodiversityLibrary();
        this.url = "https://www.biodiversitylibrary.org/api3?op=GetCollections&format=json&apikey=";
        this.fakeUrl = "https://fake-url.com/fake.json";
    }

    @Test
    void getFetchDataShouldReturnJsonWithDataTest() throws Exception {
        var data = finder.fetchData(url);

        assertTrue(data.length() > 0);
        assertTrue(data.contains("\"Status\":\"ok\""));
    }

    @Test
    void getFetchDataShouldThrowExceptionTest() throws Exception {
        assertThrows(Exception.class, () -> {
            finder.fetchData(fakeUrl);
        });
    }

    @Test
    void getCollectionsShouldReturnObjWithDataTest() throws Exception {
        var collections = finder.getCollections();

        assertEquals("ok", collections.getStatus());
        assertFalse(collections.getResult().isEmpty());
    }

}
