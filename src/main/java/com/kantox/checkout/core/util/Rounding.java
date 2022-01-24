package com.kantox.checkout.core.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounding {

    public static double roundToScale(Double doubleToRound, int scale){
        return BigDecimal.valueOf(doubleToRound).setScale(scale, RoundingMode.HALF_UP).doubleValue();

    }
}
