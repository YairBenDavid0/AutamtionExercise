import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class W3schoolsHomePage {
    protected WebDriver driver;

    protected WebDriverWait wait;

    public W3schoolsHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 5);
    }

    @FindBy(id = "customers")
    protected WebElement table;

    public String getTableCellText(WebElement table, String searchColumnName, String searchValue, String returnColumnName) {
        int searchColumnIndex = getColumnIndex(table, searchColumnName);
        int returnColumnIndex = getColumnIndex(table, returnColumnName);
        String xpath = String.format("//table[@id='%s']/tbody/tr[td[%d]='%s']/td[%d]",
                table.getAttribute("id"), searchColumnIndex, searchValue, returnColumnIndex);
        WebElement cell = driver.findElement(By.xpath(xpath));
        return cell.getText();
    }

    public boolean verifyTableCellText(WebElement table, String searchColumnName, String searchValue, String returnColumnName, String expectedValue) {
        String actualValue = getTableCellText(table, searchColumnName, searchValue, returnColumnName);
        return actualValue.equals(expectedValue);
    }

    public String getTableCellTextByXpath(WebElement table, String rowXpathExpression, String returnColumnName) throws Exception {
        String xpath = String.format("%s/td[%d]", rowXpathExpression, getColumnIndexByName(table, returnColumnName));
        WebElement cell = driver.findElement(By.xpath(xpath));
        return cell.getText();
    }

    private int getColumnIndex(WebElement table, String columnName) {
        List<WebElement> headers = table.findElements(By.tagName("th"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().equals(columnName)) {
                return i + 1;
            }
        }
        throw new NoSuchElementException("Column not found: " + columnName);
    }

    private int getColumnIndexByName(WebElement table, String columnName) {
        List<WebElement> headers = table.findElements(By.tagName("th"));
        for (int i = 0; i < headers.size(); i++) {
            if (headers.get(i).getText().equals(columnName)) {
                return i + 1;
            }
        }
        throw new NoSuchElementException("Column not found: " + columnName);
    }
}
