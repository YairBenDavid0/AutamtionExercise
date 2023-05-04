import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class EightHomePage {
    protected WebDriver driver;

    protected WebDriverWait wait;

    @FindBy(css = "div.lang-bttn > span")
    protected WebElement languageButtonLabel;

    @FindBy(css = "div.slide")
    protected WebElement mainContent;

    protected WebElement getLanguageButton() {
        return driver.findElement(By.cssSelector("div.lang-bttn"));
    }

    protected List<WebElement> getLanguageOptions() {
        return driver.findElements(By.cssSelector("div.lang-bttn li.languageLink a"));
    }


    protected void waitAndClick(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement)).click();
    }

    public EightHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void clickLanguageButton() {
        waitAndClick(getLanguageButton());
    }

    public void printAllLanguages() {
        for (WebElement link : getLanguageOptions()) {
            System.out.println(link.getText());
        }
    }

    public List<List<String>> allLanguagesAttributes() {
        List<List<String>> languagesValue = new ArrayList<List<String>>();
        for (WebElement link : getLanguageOptions()) {
            String hrefValue = link.getAttribute("href");

            List<String> languageName = new ArrayList<String>();
            languageName.add(hrefValue.substring(hrefValue.lastIndexOf('/') + 1));
            languageName.add(hrefValue.split("/")[2]);
            languageName.add(hrefValue.replaceAll("^.*/", ""));

            languagesValue.add(languageName);
        }

        return languagesValue;
    }


    public void verifyLanguage() {
        int index = 0;
        List<String> indexOfValues = allLanguagesAttributes().get(index);
        for (WebElement option : getLanguageOptions()) {
            waitAndClick(getLanguageButton());
            waitAndClick(option);

            wait.until(ExpectedConditions.visibilityOf(getLanguageButton()));

            String languageButtonText = getLanguageButton().getText();
            if (languageButtonText.contains(indexOfValues.get(0))) {
                System.out.println("Success! The language value is: " + languageButtonText);
            }
            if (languageButtonText.contains(indexOfValues.get(1))) {
                System.out.println("Success! The language value is: " + languageButtonText);
            }
            if (languageButtonText.contains(indexOfValues.get(2))) {
                System.out.println("Success! The language value is: " + languageButtonText);
            } else {
                System.out.println("Error: Unable to extract the correct language value");
            }

            wait.until(ExpectedConditions.elementToBeClickable(getLanguageButton()));
            if (index < allLanguagesAttributes().size()) {
                index += 1;
            } else break;
        }
    }
}

