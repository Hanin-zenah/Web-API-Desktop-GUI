package SCD2_2021_Exam.model;

import SCD2_2021_Exam.model.database.DatabaseCache;
import SCD2_2021_Exam.model.inputapi.TheOneInput;
import SCD2_2021_Exam.model.inputapi.TheOneInputOffline;
import SCD2_2021_Exam.model.inputapi.TheOneInputOnline;
import SCD2_2021_Exam.model.outputapi.PastebinOutput;
import SCD2_2021_Exam.model.outputapi.PastebinOutputOffline;
import SCD2_2021_Exam.model.outputapi.PastebinOutputOnline;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ModelFacadeImplTest {
    private ModelFacadeImpl model;

    @Mock
    private TheOneInput theOneInputApi;

    @Mock
    private PastebinOutput pastebinOutputApi;

    @Mock
    private DatabaseCache db;

    @Test
    public void testConstructorOnlineOffline() {
        model = new ModelFacadeImpl("online", "offline", db);
        assertNotNull(model);
        assertTrue(model.getInputApi() instanceof TheOneInputOnline);
        assertTrue(model.getOutputApi() instanceof PastebinOutputOffline);
    }

    @Test
    public void testConstructorOnlineOnline() {
        model = new ModelFacadeImpl("online", "online", db);
        assertNotNull(model);
        assertTrue(model.getInputApi() instanceof TheOneInputOnline);
        assertTrue(model.getOutputApi() instanceof PastebinOutputOnline);
    }

    @Test
    public void testConstructorOfflineOnline() {
        model = new ModelFacadeImpl("offline", "online", db);
        assertNotNull(model);
        assertTrue(model.getInputApi() instanceof TheOneInputOffline);
        assertTrue(model.getOutputApi() instanceof PastebinOutputOnline);
    }

    @Test
    public void testConstructorOfflineOffline() {
        model = new ModelFacadeImpl("offline", "offline", db);
        assertNotNull(model);
        assertTrue(model.getInputApi() instanceof TheOneInputOffline);
        assertTrue(model.getOutputApi() instanceof PastebinOutputOffline);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalid() {
        model = new ModelFacadeImpl("something", "offline", db);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalid2() {
        model = new ModelFacadeImpl("online", "offlineaaa", db);
    }

    @Test
    public void testListAvailableCharacters() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        verify(theOneInputApi).setToken(anyString());
        verify(pastebinOutputApi).setToken(anyString());
        model.listAvailableCharacters();
        verify(theOneInputApi).listAvailableCharacters();
        verify(theOneInputApi, never()).listAvailableQuotes(anyString());
        verify(pastebinOutputApi, never()).sendReport(anyString());
    }

    @Test
    public void testListAvailableCharactersNullInputAPI() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(null);
        boolean caught = false;
        try {
            model.listAvailableCharacters();
        } catch(IllegalStateException e) {
            caught = true;
        }
        assertTrue(caught);
        verify(theOneInputApi, never()).listAvailableCharacters();
        verify(theOneInputApi, never()).listAvailableQuotes(anyString());
        verify(pastebinOutputApi, never()).sendReport(anyString());
    }

    @Test
    public void testListAvailableQuotesNullCharacter() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        boolean caught = false;
        try {
            //when character is null handle in model, ie dont call inputApi
            model.listAvailableQuotes(null);
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        assertTrue(caught);
        verify(theOneInputApi, never()).listAvailableQuotes(anyString());
        verify(theOneInputApi, never()).listAvailableQuotes(null);
    }

    @Test
    public void testListAvailableQuotes() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        verify(theOneInputApi).setToken(anyString());
        model.setOutputApi(pastebinOutputApi);
        verify(theOneInputApi).setToken(anyString());
        model.listAvailableQuotes("someCharacter");
        verify(theOneInputApi, never()).listAvailableCharacters();
        verify(theOneInputApi).listAvailableQuotes("someCharacter");
        verify(pastebinOutputApi, never()).sendReport(anyString());
    }

    @Test
    public void testGetCharacterInfo() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        model.getCharacterInfo("someCharacter");
        verify(theOneInputApi, never()).listAvailableCharacters();
        verify(theOneInputApi, never()).listAvailableQuotes("someCharacter");
        verify(pastebinOutputApi, never()).sendReport(anyString());
        verify(theOneInputApi).getCharacterInfo("someCharacter");
    }

    @Test
    public void testSendReport() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        model.sendReport("Hello");
        verify(theOneInputApi, never()).listAvailableCharacters();
        verify(theOneInputApi, never()).listAvailableQuotes(anyString());
        verify(pastebinOutputApi).sendReport("Hello");
    }

    @Test
    public void testInsertIntoDb() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        model.insertIntoDb("ID", "QUOTE");
        verify(db).insertCharacter("ID", "QUOTE");
    }

    @Test
    public void testInsertIntoDbNull() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        boolean caught = false;
        try {
            model.insertIntoDb(null, "QUOTE");
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        verify(db, never()).insertCharacter(null, "QUOTE");
        assertTrue(caught);

        caught = false;
        try {
            model.insertIntoDb("ID", null);
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        verify(db, never()).insertCharacter("ID", null);
        assertTrue(caught);
    }

    @Test
    public void testInsertIntoDbEmptyId() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        boolean caught = false;
        try {
            model.insertIntoDb("", "Something");
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        verify(db, never()).insertCharacter("", "Something");
        assertTrue(caught);
    }

    @Test
    public void testInsertIntoDbBlankId() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        boolean caught = false;
        try {
            model.insertIntoDb("      ", "Something");
        } catch(IllegalArgumentException e) {
            caught = true;
        }
        verify(db, never()).insertCharacter("      ", "Something");
        assertTrue(caught);
    }

    @Test
    public void testInsertIntoDb2() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        model.insertIntoDb("Something", "Something");
        model.insertIntoDb("Something", "Something");
        verify(db, times(2)).insertCharacter("Something", "Something");
    }

    @Test
    public void testFetchFromCacheDoesNotExist() {
        model = new ModelFacadeImpl("offline", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        String result = model.getCachedQuote("id");
        //returns null because it does not exist
        //calls db.fetch once
        verify(db).fetchCharacterQuotes("id");
        assertNull(result);
    }

    @Test
    public void testFetchFromCache() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        model.getCachedQuote("id");
        model.insertIntoDb("id", "This string's the quote");
        model.getCachedQuote("id");
        verify(db, times(2)).fetchCharacterQuotes("id");
    }

    @Test
    public void testInsertOverwrite() {
        model = new ModelFacadeImpl("online", "offline", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        //returns null because it does not exist
        //calls db.fetch once
        model.insertIntoDb("123", "This string's the quote");
        model.getCachedQuote("123");
        model.insertIntoDb("123", "A different string");
        model.getCachedQuote("123");
        verify(db).insertCharacter("123", "This string's the quote");
        verify(db).insertCharacter("123", "A different string");
        verify(db, times(2)).fetchCharacterQuotes("123");
    }

    @Test
    public void testFetchNullId() {
        model = new ModelFacadeImpl("online", "offline", db);
        model.setInputApi(theOneInputApi);
        model.setOutputApi(pastebinOutputApi);
        boolean caught = false;
        try {
            model.getCachedQuote(null);
        } catch (IllegalArgumentException e) {
            caught = true;
        }

        assertTrue(caught);
        verify(db, never()).fetchCharacterQuotes(null);
    }

    @Test
    public void testCountQuotes() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        lenient().when(theOneInputApi.countQuotes("Some quotes")).thenReturn(4);
        lenient().when(theOneInputApi.countQuotes("")).thenReturn(0);
        lenient().when(theOneInputApi.countQuotes(null)).thenReturn(0);
        model.setOutputApi(pastebinOutputApi);

        assertEquals(4, model.countQuotes("Some quotes"));
        verify(theOneInputApi).countQuotes("Some quotes");
    }

    @Test
    public void testCountQuotesEmpty() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        lenient().when(theOneInputApi.countQuotes("Some quotes")).thenReturn(4);
        lenient().when(theOneInputApi.countQuotes("")).thenReturn(0);
        lenient().when(theOneInputApi.countQuotes(null)).thenReturn(0);
        model.setOutputApi(pastebinOutputApi);

        assertEquals(0, model.countQuotes(""));
        verify(theOneInputApi).countQuotes("");
    }

    @Test
    public void testCountQuotesNull() {
        model = new ModelFacadeImpl("online", "online", db);
        model.setInputApi(theOneInputApi);
        lenient().when(theOneInputApi.countQuotes("Some quotes")).thenReturn(4);
        lenient().when(theOneInputApi.countQuotes("")).thenReturn(0);
        lenient().when(theOneInputApi.countQuotes(null)).thenReturn(0);
        model.setOutputApi(pastebinOutputApi);

        assertEquals(0, model.countQuotes(null));
        verify(theOneInputApi).countQuotes(null);
    }

}
