package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {

    @Test
    void shouldSendValidForm() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        String inputDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] input").doubleClick().sendKeys(inputDate);
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+71231231234");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $("[data-test-id=notification] .notification__content")
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + inputDate));
    }
}