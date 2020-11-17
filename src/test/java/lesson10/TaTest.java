package test.java.lesson10;


import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.java.TestBaseSetup;
import test.java.pages.HomePage;
import test.java.pages.ResultPage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TaTest extends TestBaseSetup {
    HomePage homePage;
    private List<String> laptopList;
    private String searchValue = "laptop";
    private String filter = "Brand";

    @BeforeClass
    public void filters() {
        //Take the filters list for Data Provider
        homePage = new HomePage(driver);
        laptopList = homePage
                .open()
                .enterLaptop()
                .searchText(searchValue)
                .filterName(filter);
        driver.close();
    }


    @BeforeMethod
    public void pageInitialize() {
        homePage = new HomePage(driver);


    }

    @DataProvider(name = "laptopsFilters")
    public Object[][] DP() {
        Object[][] result = new Object[laptopList.size()][1];
        for (int i = 0; i < laptopList.size(); i++) {
            result[i][0] = laptopList.get(i);
        }
        return result;
    }

    @Test(dataProvider = "laptopsFilters")
    public void checkFilters(String name) {

        assertTrue(homePage
                .enterLaptop(searchValue)
                .setFilterBy(name)
                .verifyEachSearchResultContainsValue(name));
    }
}


  /*  @Test{
        //enter the "Laptop" into the search field and press the "Enter" button
        homePage
                .open()
                .enterLaptop()
                .searchText();


        java.util.List<WebElement> searchProducts = driver.findElements();
        for (WebElement el: searchProducts) {
            el.getText().contains();
        }

        String expectedProduct = "HP";
        List<WebElement> actualProduct = searchProducts;
        assertEquals(actualProduct, expectedProduct);

    }*/





