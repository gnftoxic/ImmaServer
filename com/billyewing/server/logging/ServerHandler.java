package com.billyewing.server.logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ServerHandler extends Handler
{
    FileOutputStream fileOutputStream;
    PrintWriter printWriter;
    
    public ServerHandler(String filename)
    {
        super();
        try
        {
            fileOutputStream = new FileOutputStream(new File("server.log"));
            printWriter = new PrintWriter(fileOutputStream);
        } catch(Exception e)
        {
            System.exit(1);
        }
        setFormatter(new ServerFormatter());
    }
    
    @Override
    public void publish(LogRecord record)
    {
        if(!isLoggable(record))
            return;
        
        String result = getFormatter().format(record);
        printWriter.println(result);
        System.out.println(result);
    }

    @Override
    public void flush()
    {
        printWriter.flush();
    }

    @Override
    public void close() throws SecurityException
    {
        printWriter.close();
    }
    
}
