package org.github.xfactory.tools;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class XtendTestExtension {
    public long days(final int day) {
        final Calendar now = GregorianCalendar.getInstance();
        final Calendar date = GregorianCalendar.getInstance();
        date.setTime(now.getTime());
        date.add(Calendar.DAY_OF_MONTH, day);
        return date.getTime().getTime() - now.getTime().getTime();
    }
    public Date ago(final long timeInMillis) {
        return new Date(System.currentTimeMillis() - timeInMillis);
    }

}
