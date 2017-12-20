package com.gbc.flightbooker.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nooran on 2017-12-20.
 */

public class Validator {

    //validate customer name
    public boolean validateName(String name)
    {
        String regex = "[A-Za-z]+" ;
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(name);
        if(matcher.matches())
        {
            return true;
        }
        return false;
    }
    //validate customer address
    public boolean validateAddress(String address)
    {
        String regex = "\\d+\\s+(([a-zA-Z])+|([a-zA-Z]+\\s+[a-zA-Z]+))\\s+[a-zA-Z]*";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(address);
        if (matcher.matches())
            return true;
        return false;
    }
    //validate credit card number
    public boolean validateCreditCard(String credit)
    {
        String regex = "\\d{16}";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(credit);
        if (matcher.matches())
            return true;
        return false;
    }
}
