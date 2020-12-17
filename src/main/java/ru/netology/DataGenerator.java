package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;

public class DataGenerator {
    DataGenerator() {}

    public static class CardRequest {
        private CardRequest() {}

        public static RequestCardInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    faker.address().city(),
                    LocalDate.now().plusDays(3),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().cellPhone()
            );
        }

        public static RequestCardInfo generateInfoWithHyphen(String locale) {
            Faker faker = new Faker(new Locale("ru"));

            return new RequestCardInfo(
                    faker.address().city(),
                    LocalDate.now().plusDays(3),
                    faker.regexify("[а-я]-[а-я]{10}"),
                    faker.phoneNumber().cellPhone()
            );
        }

        public static RequestCardInfo generateInvalidCityInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    faker.regexify("[а-я]{10}"),
                    LocalDate.now().plusDays(3),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().cellPhone()
            );
        }

        public static RequestCardInfo generateInvalidDateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    faker.address().cityName(),
                    LocalDate.now(),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().cellPhone()
            );
        }

        public static RequestCardInfo generateInvalidFullNameInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    faker.address().cityName(),
                    LocalDate.now().plusDays(3),
                    faker.regexify("[a-z]{10}"),
                    faker.phoneNumber().cellPhone()
            );
        }
        public static RequestCardInfo generateInvalidFullNameInfoWithSymbols(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    faker.address().cityName(),
                    LocalDate.now().plusDays(3),
                    faker.regexify("[!-)]{10}"),
                    faker.phoneNumber().cellPhone()
            );
        }

        public static RequestCardInfo generateInvalidPhoneInfoWithSymbols(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    faker.address().cityName(),
                    LocalDate.now().plusDays(3),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.regexify("[!-)]{10}")
            );
        }
    }
}
