import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Browsers.FIREFOX;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDrop {
    @BeforeAll
    static void beforeAll() {
        Configuration.browser = FIREFOX;
        Configuration.startMaximized = true;
        open("https://the-internet.herokuapp.com/drag_and_drop");
    }

    @BeforeEach
    void beforeEach() {
        Selenide.refresh();
    }

    @AfterEach
    void afterEach() {
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

    @Test
    void byActionsClickAndHold() {
        actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
    }

    @Test
    void byActionsWithSecondElement() {
        actions().dragAndDrop($("#column-a"), $("#column-b")).perform();
    }

    @Test
    void byActionsWithOffsets() {
        actions().dragAndDropBy($("#column-a"), 250, 2).perform();
    }

    @Test
    void bySelenideMethodUsingJavaScript() {
        $("#column-a").dragAndDropTo("#column-b", DragAndDropOptions.usingJavaScript());
    }

    @Test
    void bySelenideMethodUsingActions() {
        $("#column-a").dragAndDropTo("#column-b", DragAndDropOptions.usingActions());
    }
}
