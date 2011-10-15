package com.billyewing.server.network.packet;

import com.billyewing.server.network.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketUserInfoRequest
{
    private Client _client;
    private boolean _nick;
    
    public PacketUserInfoRequest(Client c)
    {
        _client = c;
    }

    public void read(DataInputStream dis) throws IOException
    {
        String string1 = readString(dis);
        
        _client.setAuth(_client.setName(string1));
    }

    public void write(DataOutputStream dos) throws IOException
    {
        PacketUserInfoResponse resp = new PacketUserInfoResponse(_client);
        resp.write(dos);
    }
    
    public String readString(DataInputStream dis) throws IOException
    {
        byte[] data = new byte[dis.readShort()];
        dis.read(data);
        String output = new String(data);
        return output;
    }
}
