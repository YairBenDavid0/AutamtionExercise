import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LanguageTest extends BaseTest {
    @BeforeClass
    public void start() {
        driver.get("https://www.888.com/");
        homePage = new EightHomePage(driver);
    }

    @Test
    public void languageTest() throws InterruptedException {
        homePage.clickLanguageButton();
        homePage.printAllLanguages();
        homePage.clickLanguageButton();
        homePage.verifyLanguage();
    }
}


