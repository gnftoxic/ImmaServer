package com.billyewing.server.chat;

import com.billyewing.server.network.Client;
import java.util.Map;

public class Room
{
    public String _name;
    public Map<Client, UserStatus> _users;
    
    public Room(String n, Client o)
    {
        _users.put(o, UserStatus.OPERATOR);
    }
    
    public String getName()
    {
        return _name;
    }
    
    public Map<Client, UserStatus> getUsers()
    {
        return _users;
    }
}
