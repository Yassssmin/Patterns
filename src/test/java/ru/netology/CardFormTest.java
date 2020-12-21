package ru.netology;

import Data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class CardFormTest {

    @BeforeEach
    void setUp() {
        openSite();

    }

    void openSite() {
        open("http://localhost:9999");
    }

    @Test
    void shouldCardDeliverySuccess() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInfo("ru");

        String date = DataGenerator.Date.getFormattedDate(3);

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(date);
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 15000);

        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + date));
    }

    @Test
    void shouldCardDeliverySuccessWithHyphen() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInfoWithHyphen("ru");

        String date = DataGenerator.Date.getFormattedDate(3);

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(date);
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 15000);

        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + date));
    }

    @Test
    void shouldCardDeliveryCityInvalid() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInvalidCityInfo("ru");

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(DataGenerator.Date.getFormattedDate(3));
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Доставка в выбранный город недоступна")).shouldHave(text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldCardDeliveryCityInvalidEmpty() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInvalidCityInfo("ru");

        $("input[type='text']").setValue("");
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(DataGenerator.Date.getFormattedDate(3));
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Поле обязательно для заполнения")).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotCardDeliveryInvalidDate() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInfo("ru");

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(DataGenerator.Date.getFormattedDate());
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Заказ на выбранную дату невозможен")).shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldNotCardDeliveryInvalidDateEmpty() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInfo("ru");

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue("");
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Неверно введена дата")).shouldHave(text("Неверно введена дата"));
    }

    @Test
    void shouldCardDeliveryNameAndSurnameInvalid() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInvalidFullNameInfo("ru");

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(DataGenerator.Date.getFormattedDate(3));
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."))
                .shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldCardDeliveryNameAndSurnameInvalidWithSymbols() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInvalidFullNameInfoWithSymbols("ru");

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(DataGenerator.Date.getFormattedDate(3));
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."))
                .shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldCardDeliveryNameAndSurnameInvalidEmpty() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInvalidFullNameInfoWithSymbols("ru");

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(DataGenerator.Date.getFormattedDate(3));
        $("input[name='name']").setValue("");
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Поле обязательно для заполнения")).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCardDeliveryPhoneInvalidEmpty() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInvalidPhoneInfoWithSymbols("ru");

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(DataGenerator.Date.getFormattedDate(3));
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue("");
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Поле обязательно для заполнения")).shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldCardDeliveryFlagOff() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInfo("ru");

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(DataGenerator.Date.getFormattedDate(3));
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $(withText("Запланировать")).click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }

    @Test
    void shouldCardDeliveryChange() {
        RequestCardInfo requestCardInfo = DataGenerator.CardRequest.generateInfo("ru");

        String date = DataGenerator.Date.getFormattedDate(4);

        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(date);
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(withText("Запланировать")).click();
        $(byText("Успешно!")).waitUntil(visible, 15000);

        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + date));

        String newDate = DataGenerator.Date.getFormattedDate(5);

        $("input[type='text']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[type='text']").setValue(requestCardInfo.getCity());
        $("input.input__control[type='tel']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input.input__control[type='tel']").setValue(newDate);
        $("input[name='name']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[name='name']").setValue(requestCardInfo.getFullName());
        $("input[name='phone']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("input[name='phone']").setValue(requestCardInfo.getPhoneNumber());
        $(withText("Запланировать")).click();

        $(byText("Необходимо подтверждение")).exists();
        $(byText("У вас уже запланирована встреча на другую дату. Перепланировать?")).exists();
        $(withText("Перепланировать")).click();

        $(byText("Встреча успешно запланирована на " + newDate)).exists();
    }
}