package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открыть сайт: {url}")
    public void openPage(String url) {
        open(url);
    }
    @Step("Нажать на строку поиска")
    public void clickSearch() {
        $(".search-input").click();
    }
    @Step("Ввести текст: {searchText}")
    public void inputSearchText(String searchText) {
        $("#query-builder-test").sendKeys(searchText);
    }
    @Step("Нажать Enter")
    public void pressSumbit() {
        $("#query-builder-test").submit();
    }
    @Step("Нажать на: {result}")
    public void clickSearchResult(String result) {
        $(linkText(result)).click();
    }
    @Step("Нажать на Issues")
    public void clickIssues() {
        $("#issues-tab").click();
    }
    @Step("Проверить отображение Issue c номером: {issueNumber}")
    public void shoulHaveIssueNumber(String issueNumber) {
        $(withText(issueNumber)).shouldHave(Condition.visible);
    }
}
