import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

public class JavaDateTimeApi {
    /*
     *  / **
     * Вернуть текущую дату в виде строки в зависимости от запроса.
     *
     * Запрос можно передать как на всю дату, так и на ее часть:
     * - ПОЛНЫЙ - текущая дата в целом: год, месяц, число месяца
     * форматируется как `ГГГГ-ММ-ДД` (возвращаемое значение по умолчанию);
     * - YEAR - текущий год;
     * - МЕСЯЦ - название текущего месяца;
     * - ДЕНЬ - текущий день месяца;
     * В любом другом случае выбросить DateTimeException.
     **/
    public String todayDate(DateTimePart datePart) {

        if (datePart.equals(DateTimePart.FULL)) {
            LocalDate localDate = LocalDate.now();
            return localDate.toString();
        }
        if (datePart.equals(DateTimePart.YEAR)) {
            LocalDate localDate = LocalDate.now();
            return String.valueOf(localDate.getYear());
        }
        if (datePart.equals(DateTimePart.MONTH)) {
            LocalDate localDate = LocalDate.now();
            return String.valueOf(localDate.getMonth());
        }
        if (datePart.equals(DateTimePart.DAY)) {
            LocalDate localDate = LocalDate.now();
            return String.valueOf(localDate.getDayOfMonth());
        } else {
            throw new DateTimeException("");
        }
    }

    /*  / **
     * Учитывая массив из 3 элементов, где
     * - 1-й элемент - это `год`;
     * - 2-й элемент - s `месяц`;
     * - 3-й элемент - это «день месяца»;
     *
     * Возврат Необязательный для даты, построенной из этих элементов.
     * /
     * */
    public Optional<LocalDate> getDate(Integer[] dateParams) {
        if (dateParams.length == 0) {
            return Optional.empty();
        }
        if (dateParams[1] > 12 || dateParams[1] < 1) {
            return Optional.empty();
        }
        if (dateParams[2] > 31 || dateParams[2] < 1) {
            return Optional.empty();
        }
        LocalDate localDate = LocalDate.of(dateParams[0], dateParams[1], dateParams[2]);
        Optional<LocalDate> op = Optional.ofNullable(localDate);
        return op;
    }

    /*
     * Учитывая время и количество часов, которое нужно добавить, верните измененное время.
     * */
    public LocalTime addHours(LocalTime localTime, Integer hoursToAdd) {
        return localTime.plusHours(hoursToAdd);
    }

    /*
     * Учитывая время и количество минут, которое нужно добавить, верните измененное время.
     * */
    public LocalTime addMinutes(LocalTime localTime, Integer minutesToAdd) {
        return localTime.plusMinutes(minutesToAdd);
    }

    /*
     * Учитывая время и количество секунд, которое нужно добавить, вернуть измененное время.
     */
    public LocalTime addSeconds(LocalTime localTime, Integer secondsToAdd) {
        return localTime.plusSeconds(secondsToAdd);
    }

    /*
    / **
     * Учитывая дату и количество недель, которые нужно добавить, верните измененную дату.
     * /*/
    public LocalDate addWeeks(LocalDate localDate, Integer numberOfWeeks) {
        return localDate.plusWeeks(numberOfWeeks);
    }

    /*
     * Учитывая случайную дату someDate, верните одну из следующих строк:
     * - "` someDate` после `currentDate`"
     * если someDate находится в будущем относительно текущей даты;
     * - "` someDate` предшествует `currentDate`"
     * если someDate находится в прошлом относительно текущей даты;
     * - "` someDate` сегодня "
     * если someDate сегодня;
     * /
     * */
    public String beforeOrAfter(LocalDate someDate) {
        if (someDate.equals(LocalDate.now())) {
            return someDate + " is today";
        }
        if (someDate.isAfter(LocalDate.now())) {
            return someDate + " is after " + LocalDate.now();
        }
        if (someDate.isBefore(LocalDate.now())) {
            return someDate + " is before " + LocalDate.now();
        }
        return "";
    }

    /* / **
     * Учитывая строковое представление даты и некоторого часового пояса,
     * вернуть LocalDateTime в этот часовой пояс.
     * /
     */
    public LocalDateTime getDateInSpecificTimeZone(String dateInString, String zone) {
        return LocalDateTime.ofInstant(Instant.parse(dateInString), ZoneId.of(zone));
    }

    /**
     * Учитывая некоторый LocalDateTime, вернуть OffsetDateTime
     * с примененным смещением местного времени
     * (`+02: 00` для Украины).
     * <p>
     * Пример: мы получаем LocalDateTime со значением `2019-09-06T13: 17`.
     * Мы должны вернуть OffsetDateTime со значением `2019-09-06T13: 17 + 02: 00`,
     * где `+02: 00` - смещение для нашего местного часового пояса.
     * <p>
     * OffsetDateTime рекомендуется использовать для хранения значений дат в базе данных..
     */
    public OffsetDateTime offsetDateTime(LocalDateTime localTime) {
        return OffsetDateTime.of(localTime, ZonedDateTime.now(ZoneId
                .of("Europe/Kiev")).getOffset());
    }

    public Optional<LocalDate> parseDate(String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(localDate);
    }

    /**
     * Учитывая строку, отформатированную как `d MMM yyyy` (MMM - сентябрь, октябрь и т. Д.),
     * return Необязательно для этой даты как LocalDate.
     */
    public Optional<LocalDate> customParseDate(String date) {
        DateTimeFormatter format = DateTimeFormatter
                .ofPattern("dd MMM yyyy", Locale.ENGLISH);
        try {
            LocalDate optDate = LocalDate.parse(date, format);
            return Optional.of(optDate);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

    /**
     * Учитывая некоторый LocalDateTime, вернуть строку в формате
     * `день (2-значное число) месяц (полное имя на английском языке)
     * год (4-значное число) часы (24-часовой формат): минуты`.
     * <p>
     * Пример: «01 января 2000 18:00».
     */
    public String formatDate(LocalDateTime dateTime) {

        /*DateTimeFormatter formatter = DateTimeFormatter
        .ofPattern("dd MMMM yyyy HH:mm", Locale.US);
        return dateTime.format(formatter);*/
        try {
            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("dd MMMM yyyy HH:mm", Locale.ENGLISH);
            return dateTime.format(formatter);
        } catch (DateTimeParseException e) {
            return "Date can't be formatted!";
        }
    }
}

