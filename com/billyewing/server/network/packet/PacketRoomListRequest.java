package com.billyewing.server.network.packet;

import com.billyewing.server.network.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class PacketRoomListRequest
{
    private Client _client;
    
    public PacketRoomListRequest(Client c)
    {
        _client = c;
    }
    
    public void read(DataInputStream dis)
    {
        
    }
    
    public void write(DataOutputStream dos)
    {
        
    }
}
