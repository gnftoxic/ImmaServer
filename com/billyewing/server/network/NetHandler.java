package com.billyewing.server.network;

import com.billyewing.server.ImmaServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Logger;

public class NetHandler
{
    private Logger _log;
    private ImmaServer _server;
    private ServerSocket _socket;
    private ClientListener _cl;
    private Thread _clThread;
    
    public NetHandler(ImmaServer aServer)
    {
        _server = aServer;
        _log = Logger.getLogger(ImmaServer.class.getName());
    }
    
    public void init() throws IOException
    {
        _socket = new ServerSocket(Integer.parseInt(_server._config.getProperty("server-port")), 0, InetAddress.getByName(_server._config.getProperty("server-address")));
        _log.info("Now listening on " + _socket.getLocalSocketAddress().toString().substring(1));
        
        _cl = new ClientListener(_server, _socket);
        _clThread = new Thread(_cl);
        _clThread.start();
    }
}
