package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    enum Month {
        JANUARY (31),
        FEBRUARY (28),
        MARCH (31),
        APRIL (30),
        MAY (31),
        JUNE (30),
        JULY (31),
        AUGUST (31),
        SEPTEMBER (30),
        OCTOBER (31),
        NOVEMBER (30),
        DECEMBER (31);

        private final int days;

        Month(int days) {
            this.days = days;
        }

        public static Month fromString(final String month) {
            // argument can't be null
            if (month == null) {
                throw new IllegalArgumentException("The passed string is null");
            }

            try {
                // if we get a perfect match there is no need to search further
                return Month.valueOf(month);
            } catch (IllegalArgumentException e) {
                Month match = null;
                // searching for partial matches
                for (final Month elem : Month.values()) {
                    // ignoring case but the matching string has to be at the start of the month
                    if (elem.toString().toLowerCase().indexOf(month.toLowerCase()) == 0){
                        // if we already got a match then it's ambiguous
                        if (match != null) {
                            throw new IllegalArgumentException("The string passed as argument is ambiguous!");
                        }
                        match = elem;
                    }
                }
                // if we didn't get a match then there is no matching month
                if (match == null) {
                    throw new IllegalArgumentException("The string passed as argument isn't a month!");
                }
                return match;
            }
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
