package Data;

import com.github.javafaker.Faker;
import ru.netology.RequestCardInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {}

    public static class Date {
        private Date() {}

        public static String getFormattedDate() {
            return getFormattedDate(0);
        }

        public static String getFormattedDate(int shiftDays) {
            LocalDate date = LocalDate.now().plusDays(shiftDays);

            return formatDate(date);
        }

        private static String formatDate(LocalDate date) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            return date.format(formatter);
        }
    }

    public static class City {
        private City() {}

        public static String getRandomValidCity() {
            String[] validCities = {"Москва", "Владимир", "Нижний Новгород", "Ярославль"};

            return getRandomCity(validCities);
        }

        public static String getRandomInvalidCity() {
            String[] invalidCities = {"Подольск", "Мытищи", "Гороховец", "Павлово"};

            return getRandomCity(invalidCities);
        }

        private static String getRandomCity(String[] cities) {
            Random random = new Random();

            return cities[random.nextInt(cities.length - 1)];
        }
    }

    public static class CardRequest {
        private CardRequest() {}

        public static RequestCardInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    DataGenerator.City.getRandomValidCity(),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().phoneNumber()
            );
        }

        public static RequestCardInfo generateInfoWithHyphen(String locale) {
            Faker faker = new Faker(new Locale("ru"));

            return new RequestCardInfo(
                    DataGenerator.City.getRandomValidCity(),
                    faker.regexify("[а-я]-[а-я]{10}"),
                    faker.phoneNumber().phoneNumber()
            );
        }

        public static RequestCardInfo generateInvalidCityInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    DataGenerator.City.getRandomInvalidCity(),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.phoneNumber().phoneNumber()
            );
        }

        public static RequestCardInfo generateInvalidFullNameInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    DataGenerator.City.getRandomValidCity(),
                    faker.regexify("[a-z]{10}"),
                    faker.phoneNumber().phoneNumber()
            );
        }
        public static RequestCardInfo generateInvalidFullNameInfoWithSymbols(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    DataGenerator.City.getRandomValidCity(),
                    faker.regexify("[!-)]{10}"),
                    faker.phoneNumber().phoneNumber()
            );
        }

        public static RequestCardInfo generateInvalidPhoneInfoWithSymbols(String locale) {
            Faker faker = new Faker(new Locale(locale));

            return new RequestCardInfo(
                    DataGenerator.City.getRandomValidCity(),
                    faker.name().firstName() + " " + faker.name().lastName(),
                    faker.regexify("[!-)]{10}")
            );
        }
    }
}
