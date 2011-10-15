package com.billyewing.server.network;

import com.billyewing.server.ImmaServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class ClientListener implements Runnable
{
    private ImmaServer _server;
    private ServerSocket _socket;
    private Logger _log;
    
    public ClientListener(ImmaServer s, ServerSocket ss)
    {
        _server = s;
        _socket = ss;
        _log = Logger.getLogger(ImmaServer.class.getName());
    }
    
    @Override
    public void run()
    {
        _log.info("Accepting new clients");
        
        try
        {
            Socket s = null;
            int id = 1;
            while((s = _socket.accept()) != null)
            {
                Client c = new Client(_server, s, id);
                c.start();
                _server._clients.add(c);
                id++;
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
