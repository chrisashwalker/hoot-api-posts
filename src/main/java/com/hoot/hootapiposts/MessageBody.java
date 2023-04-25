package com.hoot.hootapiposts;

public class MessageBody {
    public String type;
    public Long objId;

    public MessageBody(String type, Long objId) 
    {
        this.type = type;
        this.objId = objId;
    }

    public String ToString() 
    {
        return String.format("\"type\": \"%s\", \"objId\": %l}}", this.type, this.objId);
    }
}
