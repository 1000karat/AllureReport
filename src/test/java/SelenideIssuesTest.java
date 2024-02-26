import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import steps.WebSteps;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SelenideIssuesTest {
    private static final String BASE_URL = "https://github.com";
    private static final String SEARCH_TEXT = "Selenide";
    private static final String SEARCH_RESULT = "selenide/selenide";

    @BeforeAll
    static void setUp() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.holdBrowserOpen = false;
        Configuration.headless = true;

    }

    @Feature("Тест без шагов")
    @CsvSource({"2094", "2095"})
    @ParameterizedTest(name = "Should have selenide issues {0}")
    public void shouldSearchIssues(String issueNumber) {

        open(BASE_URL);

        $(".search-input").click();
        $("#query-builder-test").sendKeys(SEARCH_TEXT);
        $("#query-builder-test").submit();

        $(linkText(SEARCH_RESULT)).click();

        $("#issues-tab").click();
        $(withText(issueNumber)).shouldHave(Condition.visible);
    }

    @Feature("Тест с шагами в методе")
    @CsvSource({"2094", "2095"})
    @ParameterizedTest(name = "Test with steps, issues {0}")
    public void shouldSearchIssuesWithSteps(String issueNumber) {
        step("Открыть сайт: " + BASE_URL, () -> {
            open(BASE_URL);
        });
        step("Нажать на строку поиска", () -> {
            $(".search-input").click();
        });
        step("Ввести текст: " + SEARCH_TEXT, () -> {
            $("#query-builder-test").sendKeys(SEARCH_TEXT);
        });
        step("Нажать Enter", () -> {
            $("#query-builder-test").submit();
        });
        step("Нажать на: " + SEARCH_RESULT, () -> {
            $(linkText(SEARCH_RESULT)).click();
        });
        step("Нажать на Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить отображение Issue c номером: " + issueNumber, () -> {
            $(withText(issueNumber)).shouldHave(Condition.visible);
        });
    }

    @Feature("Тест с шагами в классе")
    @CsvSource({"2094", "2095"})
    @ParameterizedTest(name = "Test with steps class, issues {0}")
    public void shouldSearchIssuesWithStepsClass(String issueNumber) {
        WebSteps webSteps = new WebSteps();
        webSteps.openPage(BASE_URL);
        webSteps.clickSearch();
        webSteps.inputSearchText(SEARCH_TEXT);
        webSteps.pressSumbit();
        webSteps.clickSearchResult(SEARCH_RESULT);
        webSteps.clickIssues();
        webSteps.shoulHaveIssueNumber(issueNumber);
    }
}
