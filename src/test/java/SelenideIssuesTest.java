import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideIssuesTest {
    @BeforeEach
    void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.holdBrowserOpen = false;
        Configuration.headless = true;
    }

    @DisplayName("Should have selenide issues 2094")
    @Test
    public void shouldSearchIssues() {

        open("https://github.com/");

        $(".search-input").click();
        $("#query-builder-test").sendKeys("Selenide");
        $("#query-builder-test").submit();

        $(linkText("selenide/selenide")).click();

        $("#issues-tab").click();
        $(withText("2094")).shouldHave(Condition.visible);
    }
}
