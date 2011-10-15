package com.billyewing.server.network.packet;

import com.billyewing.server.network.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketUserInfoResponse
{
    private Client _client;
    
    public PacketUserInfoResponse(Client c)
    {
        _client = c;
    }
    
    public void read(DataInputStream dis)
    {
        
    }
    
    public void write(DataOutputStream dos) throws IOException
    {
        dos.writeInt(0x0011);
        dos.writeUTF(_client.getName());
        dos.writeInt(_client.getId());
        dos.flush();
    }
}
