package com.billyewing.server.logging;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class ServerFilter implements Filter
{
    @Override
    public boolean isLoggable(LogRecord lr)
    {
        String msg = lr.getMessage();

        if (msg.startsWith("SECJ0222E") || msg.startsWith("SECJ0373E") || msg.startsWith("SECJ0350E"))
        {
            return false;
        }
        return true;
    }
}
