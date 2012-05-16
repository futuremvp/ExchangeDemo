package au.com.realestate.features.common;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.features.support.Context;
import au.com.realestate.instrument.client.ReaAndroidElement;
import au.com.realestate.test.operations.MainScreen;
import au.com.realestate.test.operations.SearchScreen;
import com.google.android.testing.nativedriver.common.AndroidKeys;
import com.google.android.testing.nativedriver.common.AndroidNativeBy;
import cuke4duke.StepMother;
import cuke4duke.Table;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.Assert.*;

public class ListingSummaryDisplaySteps extends ReaStepDefinition {

    public ListingSummaryDisplaySteps(StepMother stepMother) {
        super(stepMother);
    }

    private static final String PROPERTY_WITHOUT_ALL_SUMMARY_DETAILS_POPULATED__A_CELL_WITH_AN_SUBURB = "Property without all summary details populated;a cell with an suburb";

    @Given("^I have activated the search view$")
    public void I_have_activated_the_search_view() {
        new MainScreen().openSearchScreen();
    }

    @When("^I search properties in \"([^\"]*)\"$")
    public void iSearchPropertiesIn(String term) throws MalformedURLException {
        new SearchScreen().searchBy(term + AndroidKeys.ENTER);
    }

    @When("^I scroll down in summary listing screen$")
    public void iScrollDownInSummaryListingScreen() {
        helper.scrollDown(5);
    }

    @Then("^I should see a list with one cell per property$")
    public void iShouldSeeAListWithOneCellPerProperty() {
        assertNotNull(helper.findDisplayedElementById("listing_screen_list_view"));
    }

    @Then("^I should be able to scroll through the list$")
    public void iShouldBeAbleToScroll() {
        System.out.println("TODO: I should be able to scroll through the list");
    }

    @Then("^I should see a cell for the property at \"([^\"]*)\"$")
    public void iShouldSeeCellWithPropertyAddress(String address) {
        assertNotNull(helper.findElementsByPartialText(address));
        ReaAndroidElement targetElement = new MainScreen().getSearchResultItemMatchTheAddress(address);
        assertNotNull(targetElement);
        Context.put(PROPERTY_WITHOUT_ALL_SUMMARY_DETAILS_POPULATED__A_CELL_WITH_AN_SUBURB, targetElement);
    }

    @Then("^I should see a cell with an suburb of \"([^\"]*)\"$")
    public void iShouldSeeCellWithAnSuburb(String address) {
        ReaAndroidElement targetElement = (ReaAndroidElement) Context.get(PROPERTY_WITHOUT_ALL_SUMMARY_DETAILS_POPULATED__A_CELL_WITH_AN_SUBURB);
        assertNotNull(targetElement.findElementByPartialText(address));
    }

    @Then("^the property cell should have a suburb of \"([^\"]*)\"$")
    public void thePropertyCellShouldHaveASuburbOfOlbia20154_(String suburb) {
        Then("I should see a cell with an suburb of \"" + suburb + "\"");
    }

    @Then("^the property cell should have an address of \"([^\"]*)\"$")
    public void iShouldSeeAPropertyWithNeedRequestPromotion(String toFind) {
        ReaAndroidElement targetElement = (ReaAndroidElement) Context.get(PROPERTY_WITHOUT_ALL_SUMMARY_DETAILS_POPULATED__A_CELL_WITH_AN_SUBURB);
        assertNotNull(targetElement.findElementByPartialText(toFind));
    }

    @Then("^the property cell should have a default thumbnail photo$")
    public void thePropertyCellShouldHaveADefaultThumbnailPhoto() {
        ReaAndroidElement targetElement = (ReaAndroidElement) Context.get(PROPERTY_WITHOUT_ALL_SUMMARY_DETAILS_POPULATED__A_CELL_WITH_AN_SUBURB);
        assertTrue(targetElement.findElementById("search_thumbnail").isDisplayed());
    }

    @Then("^the property cell should have a photo$")
    public void thePropertyCellShouldHaveAPhoto() {
        Then("the property cell should have a default thumbnail photo");
    }

    @Then("^the property cell should have a type of \"([^\"]*)\"$")
    public void thePropertyCellShouldHaveATypeOfAppartamento_(String type) {
        ReaAndroidElement targetElement = (ReaAndroidElement) Context.get(PROPERTY_WITHOUT_ALL_SUMMARY_DETAILS_POPULATED__A_CELL_WITH_AN_SUBURB);
        assertTrue(targetElement.findElementByPartialText(type).isDisplayed());
    }

    @Then("^the property cell of \"([^\"]*)\" should show the following features:$")
    public void iShouldSeePropertyFeaturesLike(String address, Table table) {
        ReaAndroidElement element = helper.findElementById("listing_screen_list_view");
        ReaAndroidElement targetElement = new MainScreen().getSearchResultItemMatchTheAddress(address);
        assertNotNull(targetElement);

        for (int i = 0; i < table.rows().size(); i++) {
            List<String> rowContent = table.rows().get(i);
            final List<WebElement> numbers = targetElement.findElements(By.id("number"));
            assertEquals(rowContent.get(1), numbers.get(i).getText());
        }
    }

    @Given("the \"([^\"]*)\" property has no address, no features, no main photo, and non numeric price")
    public void thereIsAPropertyWithoutAddressAndPrice(String locality) {
    }

    @Then("^the property cell should show no features$")
    public void iShouldNotSeeFeaturesWhenNoFeatures() {
        ReaAndroidElement targetElement = (ReaAndroidElement) Context.get(PROPERTY_WITHOUT_ALL_SUMMARY_DETAILS_POPULATED__A_CELL_WITH_AN_SUBURB);

        final List<ReaAndroidElement> numberElements = targetElement.findAndroidNativeElementsById("number");
        for (ReaAndroidElement element : numberElements) {
            assertFalse(element.isDisplayed());
        }
    }

    @Then("^the property cell should have a price of \"([^\"]*)\"$")
    public void thePropertyCellShouldHaveAPriceOfPrezzoSuRichiesta_(String price) {
        ReaAndroidElement targetElement = (ReaAndroidElement) Context.get(PROPERTY_WITHOUT_ALL_SUMMARY_DETAILS_POPULATED__A_CELL_WITH_AN_SUBURB);
        assertNotNull(targetElement.findElementByPartialText(price));
    }

    @Then("^I should see a cell with an address starting with \"([^\"]*)\"$")
    public void iShouldSeeACellStartingWith(String text) {
        assertNotNull(helper.findElementsByPartialText(text));
    }

    @Then("^the address field will take up more space than the locality field$")
    public void iShouldSeeAVeryLongAddressField() {
        System.out.println("TODO: the address field will take up more space than the locality field");
    }
}

