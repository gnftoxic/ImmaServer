package com.billyewing.server.chat;

import com.billyewing.server.network.Client;
import java.util.ArrayList;
import java.util.Map;

public class Channel
{
    public String _name;
    public Map<Client, UserStatus> _users;
    
    public Channel(String n, Client o)
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
