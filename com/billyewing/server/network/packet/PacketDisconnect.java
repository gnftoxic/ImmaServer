package com.billyewing.server.network.packet;

import com.billyewing.server.network.Client;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketDisconnect
{
    private Client _client;
    private String _reason;
    
    public PacketDisconnect(Client c, String r)
    {
        _client = c;
        _reason = r;
    }
    
    public void read()
    {
        
    }
    
    public void write(DataOutputStream dos) throws IOException
    {
        dos.writeInt(0xFFFF);
        dos.writeUTF(_reason);
        dos.flush();
        
        _client.endMyLife();
    }
}
