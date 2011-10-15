package com.billyewing.server.logging;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ServerFormatter extends Formatter
{
    public ServerFormatter()
    {
        super();
    }
    
    @Override
    public String format(LogRecord record)
    {
        String output = "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        Date d = new Date();
        output += sdf.format(d);
        
        output += " [" + record.getLevel().getName() + "] " + record.getMessage();
        
        return output;
    }
    
}
