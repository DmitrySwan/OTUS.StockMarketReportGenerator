package otus.model.exception;

import java.util.Calendar;

public class HistoricalQuoteException extends Exception {
    public HistoricalQuoteException(String ticker, Calendar calendar) {
        super("Error while getting " + ticker + " history by " + calendar);

    }
}
