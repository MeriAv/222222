package ru.netology;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.sun.tools.doclint.Entity.exist;
import static java.nio.channels.FileChannel.open;


public class FormTest {

    Generator  dataGenerator = new Generator ();

    String city = dataGenerator.getCity();
    String name = dataGenerator.getName();
    String phone = dataGenerator.getPhone();


    @BeforeEach
    void setup () throws IOException {
        open (Path.of("http://localhost:9999"));
    }


    @Test
    void shouldTestValid () {
        $("[data-test-id=city] input").setValue(city);

        $("[data-test-id='date'] input").doubleClick().sendKeys(dataGenerator.formateDate(3));

        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(byText("Успешно!")).should(exist);
        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на "+dataGenerator.formateDate(3)));
        $("[data-test-id='date'] input").doubleClick().sendKeys(dataGenerator.formateDate(5));
        $(".button").click();

        $("[data-test-id=replan-notification]").shouldBe(visible, Duration.ofMillis(12000));

        $(byText("Необходимо подтверждение")).should(exist);
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).should(exist);
        $(byText("Перепланировать")).click();
        $(".notification_status_ok").should(exist);
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на "+dataGenerator.formateDate(5)));
    }


    @Test
    void shouldTestNoValid() {
        $("[data-test-id=city] input").setValue("Moscow");

        $("[data-test-id='date'] input").doubleClick().sendKeys(dataGenerator.formateDate(3));

        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();

        $("[data-test-id=city].input_invalid .input__sub")
                .shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

}