import helpers.BaseTest;
import org.junit.Test;
import pages.ItineraryPage;
import pages.SearchResultPage;

import java.util.List;


public class CarnivalTest extends BaseTest {

    @Test
    public void searchCruisesWithNoFilters() {
        homePage.closeModal();
        SearchResultPage searchResultPage = homePage.searchCruises();
        searchResultPage.filterByPrice();
    }

    @Test
    public void searchCruisesWithAllFilters() {
        homePage.closeModal();
        String sailTo = homePage.selectSailTo();
        String expectedDate = homePage.selectDates();
        List<Integer> duration = homePage.selectDuration();
        SearchResultPage searchResultPage = homePage.searchCruises();
        searchResultPage.filterByPrice();
        searchResultPage.assertSailTo(sailTo);
        searchResultPage.assertDuration(duration);
        String actualDate = searchResultPage.openCruiseInformation();
        searchResultPage.assertDates(expectedDate, actualDate);
    }

    @Test
    public void searchCruisesWithSailTo() {
        homePage.closeModal();
        String sailTo = homePage.selectSailTo();
        SearchResultPage searchResultPage = homePage.searchCruises();
        searchResultPage.filterByPrice();
        searchResultPage.assertSailTo(sailTo);
    }

    @Test
    public void searchCruisesWithDates() {
        homePage.closeModal();
        String expectedDate = homePage.selectDates();
        SearchResultPage searchResultPage = homePage.searchCruises();
        searchResultPage.filterByPrice();
        String actualDate = searchResultPage.openCruiseInformation();
        searchResultPage.assertDates(expectedDate, actualDate);
    }

    @Test
    public void searchCruisesWithDuration() {
        homePage.closeModal();
        List<Integer> duration = homePage.selectDuration();
        SearchResultPage searchResultPage = homePage.searchCruises();
        searchResultPage.filterByPrice();
        searchResultPage.assertDuration(duration);
    }

    @Test
    public void viewItinerary() {
        homePage.closeModal();
        homePage.selectSailTo();
        SearchResultPage searchResultPage = homePage.searchCruises();
        ItineraryPage itineraryPage = searchResultPage.openItinerary();
        itineraryPage.assertItineraryPageIsLoaded();
        itineraryPage.assertCardForEachDay();
        itineraryPage.assertBookingButtonExists();
    }
}
