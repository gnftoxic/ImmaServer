package com.billyewing.server.network;

import com.billyewing.server.ImmaServer;
import com.billyewing.server.network.packet.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.logging.Logger;

public class ClientReader implements Runnable
{
    private Logger _log;
    private Client _client;
    private DataInputStream _reader;
    private DataOutputStream _writer;
    
    public ClientReader(Client c, DataInputStream dis, DataOutputStream dos)
    {
        _log = Logger.getLogger(ImmaServer.class.getName());
        _client = c;
        _reader = dis;
        _writer = dos;
    }

    @Override
    public void run()
    {
        try
        {
            int input = 0x00;
            
            while(!_client._sock.isInputShutdown())
            {
                input = _reader.readInt();
                
                _log.info(_client._name + " sent packet ID " + Integer.toHexString(input));
                
                if(!_client.getAuth() && input != 0x0010)
                {
                    PacketError pae = new PacketError(_client, 9001, "You are not authenticated, need PacketUserInfoRequest.");
                    pae.write(_writer);
                    PacketDisconnect pd = new PacketDisconnect(_client, "InvalidPacketException");
                    pd.write(_writer);
                    _log.info(_client._addr.toString() + " disconnected due to protocol error");
                    continue;
                }
                switch(input)
                {
                    case 0x0010:
                        PacketUserInfoRequest p1 = new PacketUserInfoRequest(_client);
                        p1.read(_reader);
                        p1.write(_writer);
                        if(_client.getAuth())
                        {
                            _log.info(_client.getName() + " connected with entity id " + _client._id + " [" + _client._addr + "]");
                        }
                        break;
                    case 0x1010:
                        PacketUserListResponse p10 = new PacketUserListResponse(_client, _client._server);
                        p10.read(_reader);
                        break;
                        
                    case 0x0011: // User Info
                    case 0x1011: // Room List Response
                    case 0x1021: // Room User List Response
                    case 0xFFFF: // Disconnect (forced by server)
                        break;
                        
                    default:
                        _log.severe("Unknown packet ID: " + input);
                        break;
                }
            }
        } catch(Exception e)
        {
            _client.endMyLife();
        }
    }
}
