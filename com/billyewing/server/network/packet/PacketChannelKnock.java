package com.billyewing.server.network.packet;

import com.billyewing.server.ImmaServer;
import com.billyewing.server.chat.Channel;
import com.billyewing.server.chat.UserStatus;
import com.billyewing.server.network.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map.Entry;

public class PacketChannelKnock
{
    private Client _client;
    private ImmaServer _server;
    private Channel _channel;
    
    public PacketChannelKnock(Client c, ImmaServer s)
    {
        _server = s;
        _client = c;
    }
    
    public void read(DataInputStream dis) throws IOException
    {
        String chanName = dis.readUTF();
        for(Channel c : _server._channels)
        {
            if(_client != null)
                break;
            if(c.getName().equalsIgnoreCase(chanName))
                _channel = c;
        }
        
        if(_client == null)
            _channel = new Channel(null, _client);
    }
    
    public void write(DataOutputStream dos) throws IOException
    {
        dos.writeByte(0x10);
        dos.writeUTF(_channel.getName());
        dos.writeInt(_channel.getUsers().size());
        for(Entry<Client, UserStatus> e : _channel.getUsers().entrySet())
        {
            dos.writeUTF(e.getKey().getName()); // Client Name
            dos.writeUTF(e.getValue().name());  // Client Status
        }
        dos.flush();
    }
}
