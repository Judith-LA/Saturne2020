/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.saturne.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * Standart utilities
 *
 * @author ECN
 */
@MultipartConfig
public class Utilities {

    /**
     * Get a String configuration from the resource file
     *
     * @param res
     * @param element
     * @param defaultValue
     * @return
     */
    public static String getResourceElement(ResourceBundle res, String element, String defaultValue) {
        String newValue;
        String returnValue;

        returnValue = defaultValue;
        if (res != null) {
            try {
                newValue = res.getString(element);
                if (!newValue.equals("")) {
                    returnValue = newValue;
                }
            } catch (Exception e) {
            }
        }
        return returnValue;
    }

    /**
     * Get a String configuration from the resource file
     *
     * @param res
     * @param element
     * @return
     */
    public static String getResourceElement(ResourceBundle res, String element) {
        return getResourceElement(res, element, "");
    }

    /**
     * convert DATE to SQL string
     *
     * @param uneDate
     * @param format
     * @return
     */
    public static String getDate(Date uneDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(uneDate);
    }

    /**
     * convert DATE to standart string
     *
     * @param uneDate
     * @return
     */
    public static String getDate(Date uneDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(uneDate);
    }

    /**
     * convert DATE to standart string
     *
     * @param uneDate
     * @return
     */
    public static String getFullDate(Date uneDate) {
        return DateFormat.getDateInstance(DateFormat.LONG).format(uneDate);
    }

    /**
     * Check if a String is a Date and returns the Date, null otherwise
     *
     * @param aDate
     * @return
     */
    public static Date isDate(String aDate) {
        Date returnedValue = null;
        if (aDate != null) {
            if (!aDate.equals("")) {
                // Try strategies to get DATE formet
                try {
                    // French format
                    SimpleDateFormat aFormater = new SimpleDateFormat("dd/MM/yyyy");
                    returnedValue = aFormater.parse(aDate);
                } catch (ParseException ex) {
                }
                if (returnedValue == null) {
                    try {
                        // ISO format
                        SimpleDateFormat aFormater = new SimpleDateFormat("yyyy-MM-dd");
                        returnedValue = aFormater.parse(aDate);
                    } catch (ParseException ex) {
                    }
                }
                if (returnedValue == null) {
                    try {
                        // EN format
                        SimpleDateFormat aFormater = new SimpleDateFormat("yyyy-dd-MM");
                        returnedValue = aFormater.parse(aDate);
                    } catch (ParseException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (returnedValue != null) {
                    // There is a valid format
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(returnedValue);
                    if (aCalendar.get(Calendar.YEAR) < 1000) {
                        // Change year if it is 2 chars long
                        aCalendar.add(Calendar.YEAR, 1900);
                        returnedValue = aCalendar.getTime();
                    }
                }
            }
        }

        return returnedValue;
    }

    /**
     * Check if a String is a Time and returns the Date, null otherwise
     *
     * @param aTime
     * @return
     */
    public static Date isTime(String aTime) {
        Date returnedValue = null;
        if (aTime != null) {
            if (!aTime.equals("")) {
                // Try strategies to get DATE formet
                try {
                    // Hour, Minustes and seconds
                    SimpleDateFormat aFormater = new SimpleDateFormat("hh:mm:ss");
                    returnedValue = aFormater.parse(aTime);
                } catch (ParseException ex) {
                }
                if (returnedValue == null) {
                    try {
                        // Hous and minutes
                        SimpleDateFormat aFormater = new SimpleDateFormat("hh:mm");
                        returnedValue = aFormater.parse(aTime);
                    } catch (ParseException ex) {
                    }
                }
                if (returnedValue == null) {
                    // There is a valid format
                    try {
                        SimpleDateFormat aFormater = new SimpleDateFormat("hh");
                        returnedValue = aFormater.parse(aTime);
                    } catch (ParseException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (returnedValue != null) {
                    Calendar aCalendar = Calendar.getInstance();
                    aCalendar.setTime(returnedValue);
                }
            }
        }

        return returnedValue;
    }

    /**
     * Convert date to Timestamp
     *
     * @param aDate
     * @return
     */
    public static String asTimestamp(Date aDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(aDate);
    }

    /**
     * Get current date and convert it to a SQL timestamp
     *
     * @return
     */
    public static Date getCurrentTime() {
        Calendar aCalendar = Calendar.getInstance();
        return aCalendar.getTime();
    }

    /**
     * Get current date and converst it to a SQL timestamp
     *
     * @return
     */
    public static String getCurrentTimeAsSQL() {
        Calendar aCalendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return "TO_DATE('" + sdf.format(aCalendar.getTime()) + "','YYYY-MM-DD HH24:MI:SS')";
    }

    /**
     * Get integer from HTTP request parameter
     *
     * @param request
     * @param name
     * @return
     */
    public static Integer getRequestInteger(HttpServletRequest request, String name) {
        String value = getRequestString(request, name);
        if (!value.trim().equals("")) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Get float from HTTP request parameter
     *
     * @param request
     * @param name
     * @return
     */
    public static Float getRequestFloat(HttpServletRequest request, String name) {
        String value = getRequestString(request, name);
        if (!value.trim().equals("")) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException ex) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Get string from HTTP request parameter Never returns null
     *
     * @param request
     * @param name
     * @return
     */
    public static String getRequestString(HttpServletRequest request, String name) {
        if (existRequestParameter(request, name)) {
            String value = request.getParameter(name);
            if (value != null) {
                // value is a parameter => return value
                return value;
            } else {
                // Value is not a parameter. An attribute ?
                value = (String) request.getAttribute(name);
                if (value != null) {
                    // value is an attribute
                    return value;
                }
            }
        }
        // Unknown, return empty string
        return "";
    }

    /**
     * Get string from HTTP request parameter Never returns null
     *
     * @param request
     * @param name
     * @return
     */
    public static boolean existRequestParameter(HttpServletRequest request, String name) {
        boolean exists = false;

        String value = request.getParameter(name);
        if (value != null) {
            // value is a parameter => return value
            exists = true;
        } else {
            // Value is not a parameter. An attribute ?
            value = (String) request.getAttribute(name);
            if (value != null) {
                // value is an attribute
                exists = true;
            }
        }

        // Unknown, return empty string
        return exists;
    }

    /**
     * Get string from HTTP request parameter Never returns null
     *
     * @param request
     * @param name
     * @return
     */
    public static String getRequestText(HttpServletRequest request, String name) {
        String value = Utilities.getRequestString(request, name);
        if (value == null) {
            // Value is not a parameter. An attrobite ? -- because of multipart requests
            value = (String) request.getAttribute(name);
            if (value == null) {
                // None => return empty string
                value = "";
            }
        }
        try {
            value = URLDecoder.decode(value, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    /**
     * Get date from HTTP request parameter
     *
     * @param request
     * @param name
     * @return
     */
    public static Date getRequestDate(HttpServletRequest request, String name) {
        String value = getRequestString(request, name);
        if (!value.trim().equals("")) {
            return isDate(value);
        } else {
            return null;
        }
    }

    /**
     * Get time from HTTP request parameter
     *
     * @param request
     * @param name
     * @return
     */
    public static Date getRequestTime(HttpServletRequest request, String name) {
        String value = Utilities.getRequestString(request, name);
        if ((value != null) && (!value.equals(""))) {
            return isTime(value);
        } else {
            return null;
        }
    }

    /**
     * Get array from HTTP request parameter
     *
     * @param request
     * @param name
     * @return
     */
    public static HashMap<Integer, String> getRequestArray(HttpServletRequest request, String name) {
        HashMap<Integer, String> returnedvalue = new HashMap<Integer, String>();
        String parameter2 = name + "[";
        int lastIndex = -1;

        // process all parameters
        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String s = (String) e.nextElement();
            if (s.startsWith(parameter2)) {
                String s2 = s.substring(s.indexOf("[") + 1, s.length() - 1);
                int index;
                if (!s2.equals("")) {
                    index = Integer.parseInt(s2);
                    if (index > lastIndex) {
                        lastIndex = index;
                    }
                } else {
                    lastIndex++;
                    index = lastIndex;
                }
                returnedvalue.put(index, (String) request.getParameter(s));
            }
        }

        // The process attributes
        e = request.getAttributeNames();
        while (e.hasMoreElements()) {
            String s = (String) e.nextElement();
            if (s.startsWith(parameter2)) {
                String s2 = s.substring(s.indexOf("[") + 1, s.length() - 1);
                int index;
                if (!s2.equals("")) {
                    index = Integer.parseInt(s2);
                    if (index > lastIndex) {
                        lastIndex = index;
                    }
                } else {
                    lastIndex++;
                    index = lastIndex;
                }
                returnedvalue.put(index, (String) request.getParameter(s));
            }
        }

        return returnedvalue;
    }

    /**
     * Get array from HTTP request parameter
     *
     * @param request
     * @param name
     * @return
     */
    public static List<String> getRequestList(HttpServletRequest request, String name) {
        List<String> returnedvalue = new ArrayList<String>();
        String value = Utilities.getRequestString(request, name);
        int lastIndex = -1;

        // process all parameters
        StringTokenizer st = new StringTokenizer(value, "[,]");
        while (st.hasMoreTokens()) {
            String temp = st.nextToken().trim();
            if (!temp.isEmpty()) {
                returnedvalue.add(temp);
            }
        }

        return returnedvalue;
    }

    /**
     * Return Integer Array from request
     *
     * @param request
     * @param name
     * @return
     */
    public static HashMap<Integer, Integer> getRequestIntegerArray(HttpServletRequest request, String name) {
        HashMap<Integer, Integer> returnedValue = new HashMap<Integer, Integer>();
        HashMap<Integer, String> temp = getRequestArray(request, name);

        for (Integer i : temp.keySet()) {
            String value = temp.get(i);
            int intValue;
            if (!value.trim().equals("")) {
                try {
                    intValue = Integer.parseInt(value);
                } catch (NumberFormatException ex) {
                    intValue = 0;
                }
            } else {
                intValue = 0;
            }
            returnedValue.put(i, intValue);
        }
        return returnedValue;
    }

    /**
     * Return Float Array from request
     *
     * @param request
     * @param name
     * @return
     */
    public static HashMap<Integer, Double> getRequestFloatArray(HttpServletRequest request, String name) {
        HashMap<Integer, Double> returnedValue = new HashMap<Integer, Double>();
        HashMap<Integer, String> temp = getRequestArray(request, name);

        for (Integer i : temp.keySet()) {
            String value = temp.get(i);
            double dblValue;
            if (!value.trim().equals("")) {
                try {
                    dblValue = Double.parseDouble(value);
                } catch (NumberFormatException ex) {
                    dblValue = 0;
                }
            } else {
                dblValue = 0;
            }
            returnedValue.put(i, dblValue);
        }
        return returnedValue;
    }

    /**
     * Return Float Array from request
     *
     * @param request
     * @param name
     * @return
     */
    public static HashMap<Integer, String> getRequestTextArray(HttpServletRequest request, String name) {
        HashMap<Integer, String> returnedValue = getRequestArray(request, name);

        for (Integer i : returnedValue.keySet()) {
            String value = returnedValue.get(i);
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            }
            returnedValue.put(i, value);
        }
        return returnedValue;
    }

    /**
     * Get current Date
     *
     * @return
     */
    public static Date getCurrentDate() {
        Calendar aCalendar = Calendar.getInstance();
        Date current = aCalendar.getTime();
        return current;
    }

    /**
     * Get current Date formated for SQL
     *
     * @return
     */
    public static String getCurrentFormatedDate() {
        Calendar aCalendar = Calendar.getInstance();
        Date current = aCalendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(current);
    }

    /**
     * Get current Year
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar aCalendar = Calendar.getInstance();
        return aCalendar.get(Calendar.YEAR);
    }

    /**
     * Get Year in a date
     *
     * @param aDate
     * @return
     */
    public static int getYear(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        return aCalendar.get(Calendar.YEAR);
    }

    /**
     * Get Month in a date
     *
     * @param aDate
     * @return
     */
    public static int getMonth(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        return aCalendar.get(Calendar.MONTH);
    }

    /**
     * Get Day of Month in a date
     *
     * @param aDate
     * @return
     */
    public static int getDay(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        return aCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get first day of a week
     *
     * @param aDate
     * @return
     */
    public static Date getFirstDayOfWeek(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        int dayOfWeek = aCalendar.get(Calendar.DAY_OF_WEEK);
        aCalendar.add(Calendar.DAY_OF_MONTH, 2 - dayOfWeek);
        return aCalendar.getTime();
    }

    /**
     * Get last day of a week
     *
     * @param aDate
     * @return
     */
    public static Date getLastDayOfWeek(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        int dayOfWeek = aCalendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek != Calendar.SUNDAY) {
            aCalendar.add(Calendar.DAY_OF_MONTH, 8 - dayOfWeek);
        }
        return aCalendar.getTime();
    }

    /**
     * Get first day of a month
     *
     * @param aDate
     * @return
     */
    public static Date getFirstDayOfMonth(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        int dayOfMonth = aCalendar.get(Calendar.DAY_OF_MONTH);
        if (dayOfMonth > 1) {
            aCalendar.add(Calendar.DAY_OF_MONTH, 1 - dayOfMonth);
        }
        return aCalendar.getTime();
    }

    /**
     * Get last day of a month
     *
     * @param aDate
     * @return
     */
    public static Date getLastDayOfMonth(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        aCalendar.add(Calendar.MONTH, 1);
        int dayOfMonth = aCalendar.get(Calendar.DAY_OF_MONTH);
        aCalendar.add(Calendar.DAY_OF_MONTH, -dayOfMonth);
        return aCalendar.getTime();
    }

    /**
     * Get first day of a year
     *
     * @param aDate
     * @return
     */
    public static Date getFirstDayOfYear(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        int currentDayOfYear = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.add(Calendar.DAY_OF_YEAR, 1 - currentDayOfYear);
        return aCalendar.getTime();
    }

    /**
     * Get last day of a year
     *
     * @param aDate
     * @return
     */
    public static Date getLastDayOfYear(Date aDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(aDate);
        aCalendar.add(Calendar.YEAR, 1);
        int dayOfYear = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.add(Calendar.DAY_OF_YEAR, -dayOfYear);
        return aCalendar.getTime();
    }

    /**
     * Levenshtein Distance Algorithm: Java Implementation by Chas Emerick
     *
     * @param s
     * @param t
     * @return
     */
    public static int getLevenshteinDistance(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }

        int n = s.length(); // length of s
        int m = t.length(); // length of t

        if (n == 0) {
            return m;
        } else if (m == 0) {
            return n;
        }

        int p[] = new int[n + 1]; //'previous' cost array, horizontally
        int d[] = new int[n + 1]; // cost array, horizontally
        int _d[]; //placeholder to assist in swapping p and d

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char t_j; // jth character of t

        int cost; // cost

        for (i = 0; i <= n; i++) {
            p[i] = i;
        }

        for (j = 1; j <= m; j++) {
            t_j = t.charAt(j - 1);
            d[0] = j;

            for (i = 1; i <= n; i++) {
                cost = s.charAt(i - 1) == t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[n];
    }

    /**
     * Get classes in a package
     *
     * @param pkgName
     * @return
     */
    public static Map<String, String> getClasses(String pkgName) {
        Map<String, String> theList = new HashMap<String, String>();
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            String relPath = pkgName.replace('.', '/');
            Resource theResources[] = resolver.getResources("classpath*:" + relPath + "/*.class");

            for (Resource theResource : theResources) {
                String fileName = theResource.getFilename();
                if (fileName.endsWith(".class")) {
                    String fileNm = fileName.substring(0, fileName.length() - 6);
                    String className = pkgName + '.' + fileNm;
                    theList.put(fileNm.toLowerCase(), className);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return theList;
    }

    /**
     * Get root package name
     *
     * @return
     */
    public static String getRootPackagePath() {
        String packagePath = Utilities.class.getPackage().getName();
        int pos = packagePath.lastIndexOf(".");
        String rootPackagePath = packagePath.substring(0, pos);
        while ((!rootPackagePath.isEmpty()) && (!rootPackagePath.endsWith(".iea"))) {
            pos = rootPackagePath.lastIndexOf(".");
            rootPackagePath = rootPackagePath.substring(0, pos);
        }
        return rootPackagePath;
    }
}

