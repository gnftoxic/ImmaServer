package com.billyewing.server.network;

import com.billyewing.server.ImmaServer;
import com.billyewing.server.chat.Channel;
import com.billyewing.server.chat.UserStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.logging.Logger;

public class Client
{
    protected SocketAddress _addr;
    protected ImmaServer _server;
    protected boolean _auth;
    private Logger _log;
    protected Socket _sock;
    protected String _name;
    protected int _id;
    protected Map<Channel, UserStatus> _channels;
    protected DataInputStream _input;
    protected DataOutputStream _output;
    private Thread _readerThread;
    
    public Client(ImmaServer ss, Socket s, int i)
    {
        try
        {
            _server = ss;
            
            _sock = s;
            _addr = _sock.getRemoteSocketAddress();
            _id = i;
            _log = Logger.getLogger(ImmaServer.class.getName());
            _auth = false;
            
            _input = new DataInputStream(_sock.getInputStream());
            _output = new DataOutputStream(_sock.getOutputStream());
        } catch (IOException ex)
        {
            endMyLife();
            ex.printStackTrace();
        }
    }
    
    public void start()
    {
        _readerThread = new Thread(new ClientReader(this, _input, _output));
        _readerThread.start();
    }
    
    public void endMyLife()
    {
        try
        {
            _log.info(_name + " disconnected [" + _sock.getRemoteSocketAddress() + "]");
            _readerThread.interrupt();
            _output.close();
            _input.close();
            _sock.close();   
        } catch(Exception e)
        {
            // I dun goof'd
        }
        _server._clients.remove(this);
    }
    
    public String getName()
    {
        return _name;
    }
    
    public void setAuth(boolean blr)
    {
        _auth = blr;
    }
    
    public boolean getAuth()
    {
        return _auth;
    }
    
    public boolean setName(String s)
    {
        for(Client c : _server._clients)
        {
            if(c == this)
                continue;
            if(s.equalsIgnoreCase(c._name))
                return false;
        }
        
        _name = s;
        return true;
    }

    public int getId()
    {
        return _id;
    }
}
