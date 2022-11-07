package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    enum Month {
        JANUARY     (31),
        FEBRUARY    (28),
        MARCH       (31),
        APRIL       (30),
        MAY         (31),
        JUNE        (30),
        JULY        (31),
        AUGUST      (31),
        SEPTEMBER   (30),
        OCTOBER     (31),
        NOVEMBER    (30),
        DECEMBER    (31);

        private final int days;

        Month(int days) {
            this.days = days;
        }

        public static Month fromString(final String month) {
            if (month != null) {
                Month match = null;
                for (final Month elem : Month.values()) {
                    if (elem.toString().toLowerCase().startsWith(month.toLowerCase())){
                        if (match != null) {
                            return null;
                        }
                        match = elem;
                    }
                }
                return match;
            }
            return null;
        }
    }


    @Override
    public Comparator<String> sortByDays() {
        return new SortByDays();
    }

    private static class SortByDays implements Comparator<String> {
        public int compare(final String s1, final String s2) {
            final Month m1 = Month.fromString(s1);
            final Month m2 = Month.fromString(s2);
            return Integer.compare(m1.days, m2.days);
        }
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByOrder();
    }

    private static class SortByOrder implements Comparator<String> {
        public int compare(final String s1, final String s2) {
            return Month.fromString(s1).compareTo(Month.fromString(s2));
        }
    }
}
