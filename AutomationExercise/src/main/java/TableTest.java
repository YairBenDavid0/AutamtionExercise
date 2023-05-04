import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TableTest extends BaseTest{
    @BeforeClass
    public void start() {
        driver.get("http://www.w3schools.com/html/html_tables.asp");
        w3schoolsHomePage = new W3schoolsHomePage(driver);
    }

    @Test
    public void verifyTableCellText() {
        String searchColumnName = "Country";
        String searchValue = "Mexico";
        String returnColumnName = "Contact";
        String expectedValue = "Francisco Chang";
        boolean isTableCellTextCorrect = w3schoolsHomePage.verifyTableCellText
                (w3schoolsHomePage.table, searchColumnName, searchValue, returnColumnName, expectedValue);
        assertTrue(isTableCellTextCorrect, "Table cell text is not correct");
    }
}
