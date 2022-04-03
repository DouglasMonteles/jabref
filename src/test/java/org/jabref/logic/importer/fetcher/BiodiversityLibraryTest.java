package org.jabref.logic.importer.fetcher;

import org.jabref.testutils.category.FetcherTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@FetcherTest
class BiodiversityLibraryTest {

    private BiodiversityLibrary finder;

    @BeforeEach
    void setUp() {
        finder = new BiodiversityLibrary();
    }

    @Test
    void getFetchDataShouldReturnJsonWithDataTest() throws Exception {
        var url = "https://www.biodiversitylibrary.org/api3?op=GetCollections&format=json&apikey=";
        var data = finder.fetchData(url);

        assertTrue(data.length() > 0);
        assertTrue(data.contains("\"Status\":\"ok\""));
    }

    @Test
    void getFetchDataShouldThrowExceptionTest() throws Exception {
        var url = "https://fake-url.com/fake.json";

        assertThrows(Exception.class, () -> {
            finder.fetchData(url);
        });
    }
}
