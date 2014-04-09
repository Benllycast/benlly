package com.udec.vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author fritz
 */
public class DateConverter extends Converter<Date, String>
  {
    private static final String DEFAULT_PATTERN = "dd/MM/yyyy";
    
    private String pattern = DEFAULT_PATTERN;

    private Locale locale = Locale.getDefault();

    private SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, locale);

    public String getPattern()
      {
        return pattern;
      }

    public void setPattern (final String pattern)
      {
        this.pattern = pattern;
        dateFormat = new SimpleDateFormat(pattern, locale);
        // TODO: fire event
      }

    public TimeZone getTimeZone()
      {
        return dateFormat.getTimeZone();
      }

    public void setTimeZone (final TimeZone timeZone)
      {
        dateFormat.setTimeZone(timeZone);
      }

    public Locale getLocale()
      {
        return locale;
      }

    public void setLocale (final Locale locale)
      {
        this.locale = locale;
        dateFormat = new SimpleDateFormat(pattern, locale);
        // TODO: fire event
      }

    @Override
    public String convertForward (final Date date)
      {
        return (date != null) ? dateFormat.format(date) : "";
      }

    @Override
    public Date convertReverse (final String string)
      {
        try 
          {
            return dateFormat.parse(string);
          } 
        catch (ParseException ex)
          {
            Logger.getLogger(DateConverter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
          }
      }
  }
