package com.billyewing.server.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface Packet
{
    public byte[] read(DataInputStream dis) throws IOException;
    public void write(DataOutputStream dos);
}
