package com.billyewing.server;

import com.billyewing.server.chat.Room;
import com.billyewing.server.logging.ServerFilter;
import com.billyewing.server.logging.ServerFormatter;
import com.billyewing.server.logging.ServerHandler;
import com.billyewing.server.network.Client;
import com.billyewing.server.network.NetHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

public class ImmaServer
{
    public NetHandler _net;
    public Properties _config;
    public Logger _log;
    public ArrayList<Client> _clients;
    public ArrayList<Room> _channels;
    
    public static void main(String[] args)
    {
        try
        {
            new ImmaServer();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public ImmaServer() throws FileNotFoundException, IOException
    {
        doLogger();
        doConfig();
        
        _clients = new ArrayList<Client>();
        _channels = new ArrayList<Room>();
        
        _log.info("Starting network handler");
        _net = new NetHandler(this);
        _net.init();
    }
    
    protected void doLogger()
    {
        _log = Logger.getLogger(ImmaServer.class.getName());
        
        ServerHandler servHand = new ServerHandler("server.log");
        
        servHand.setFilter(new ServerFilter());
        servHand.setFormatter(new ServerFormatter());
        _log.addHandler(servHand);
        _log.setUseParentHandlers(false);
    }
    
    protected void doConfig() throws FileNotFoundException, IOException
    {
        _config = new Properties();
        
        File f = new File("config.properties");
        boolean writeDefault = false;
        if(!f.exists())
        {
            f.createNewFile();
            writeDefault = true;
        }
        
        _config.load(new FileInputStream("config.properties"));
        
        if(writeDefault)
        {
            _config.setProperty("server-address", "192.168.0.7");
            _config.setProperty("server-port", "4477");
            _config.store(new FileWriter("config.properties"), "");
            _config.load(new FileInputStream("config.properties"));
        }
    }
}
