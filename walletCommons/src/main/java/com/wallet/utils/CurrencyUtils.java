package com.wallet.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by EBR3556 on 12/09/2017.
 */
public class CurrencyUtils {

    public final static Locale DEFAULT_LOCALE = Locale.getDefault();

    public static String formateCurrency(BigDecimal value) {
        return formateCurrency(value, null);
    }

    public static String formateCurrency(BigDecimal value, Locale locale) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale != null ? locale : DEFAULT_LOCALE);
        return currencyFormatter.format(value);
    }

}