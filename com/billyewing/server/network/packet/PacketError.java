package com.billyewing.server.network.packet;

import com.billyewing.server.network.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketError
{
    private Client _client;
    private int _errorCode = 0;
    private String _errorMessage = null;
    
    public PacketError(Client c, int ec, String em)
    {
        _client = c;
        _errorCode = ec;
        _errorMessage = em;
    }
    
    public void read(DataInputStream dis)
    {
        
    }
    
    public void write(DataOutputStream dos) throws IOException
    {
        dos.writeInt(0xFFFE);
        dos.writeInt(_errorCode);
        dos.writeUTF(_errorMessage);
        dos.flush();
    }
}
