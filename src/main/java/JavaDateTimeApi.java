import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Optional;

public class JavaDateTimeApi {
    /**
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
        return "Today";
    }

    /**
     * Учитывая массив из 3 элементов, где
     * - 1-й элемент - это `год`;
     * - 2-й элемент - s `месяц`;
     * - 3-й элемент - это «день месяца»;
     *
     * Возврат Необязательный для даты, построенной из этих элементов..
     */
    public Optional<LocalDate> getDate(Integer[] dateParams) {
        return Optional.empty();
    }

    /**
     * Учитывая время и количество часов, которое нужно добавить, верните измененное время.
     */
    public LocalTime addHours(LocalTime localTime, Integer hoursToAdd) {
        return LocalTime.now();
    }

    /**
     * Учитывая время и количество минут, которое нужно добавить, верните измененное время.
     */
    public LocalTime addMinutes(LocalTime localTime, Integer minutesToAdd) {
        return LocalTime.now();
    }

    /**
     * Учитывая время и количество секунд, которое нужно добавить, вернуть измененное время.
     */
    public LocalTime addSeconds(LocalTime localTime, Integer secondsToAdd) {
        return LocalTime.now();
    }

    /**
     *Учитывая дату и количество недель, которые нужно добавить, верните измененную дату.
     */
    public LocalDate addWeeks(LocalDate localDate, Integer numberOfWeeks) {
        return LocalDate.now();
    }

    /**
     * Учитывая случайную дату someDate, верните одну из следующих строк:
     * - "` someDate` после `currentDate`"
     * если someDate находится в будущем относительно текущей даты;
     * - "` someDate` предшествует `currentDate`"
     * если someDate находится в прошлом относительно текущей даты;
     * - "` someDate` сегодня "
     * если someDate сегодня;
     */
    public String beforeOrAfter(LocalDate someDate) {
        return someDate + "is today";
    }

    /**
     * Учитывая строковое представление даты и некоторого часового пояса,
     * вернуть LocalDateTime в этот часовой пояс.
     */
    public LocalDateTime getDateInSpecificTimeZone(String dateInString, String zone) {
        return LocalDateTime.now();
    }

    /**
     * Учитывая некоторый LocalDateTime, вернуть OffsetDateTime с примененным смещением местного времени
     * (`+02: 00` для Украины).
     *
     * Пример: мы получаем LocalDateTime со значением `2019-09-06T13: 17`.
     * Мы должны вернуть OffsetDateTime со значением `2019-09-06T13: 17 + 02: 00`,
     * где `+02: 00` - смещение для нашего местного часового пояса.
     *
     * OffsetDateTime рекомендуется использовать для хранения значений дат в базе данных..
     */
    public OffsetDateTime offsetDateTime(LocalDateTime localTime) {
        return OffsetDateTime.now();
    }

    /**
     * Учитывая строку, отформатированную как `yyyymmdd`,
     * return Необязательно для этой даты как LocalDate.
     */
    public Optional<LocalDate> parseDate(String date) {
        return Optional.empty();
    }

    /**
     * Учитывая строку, отформатированную как `d MMM yyyy` (MMM - сентябрь, октябрь и т. Д.),
     * return Необязательно для этой даты как LocalDate.
     */
    public Optional<LocalDate> customParseDate(String date) {
        return Optional.empty();
    }

    /**
     * Учитывая некоторый LocalDateTime, вернуть строку в формате
     * `день (2-значное число) месяц (полное имя на английском языке) год (4-значное число) часы (24-часовой формат): минуты`.
     *
     * Пример: «01 января 2000 18:00».
     */
    public String formatDate(LocalDateTime dateTime) {
        return "";
    }
}
