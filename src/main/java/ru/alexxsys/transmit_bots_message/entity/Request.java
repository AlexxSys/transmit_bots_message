package ru.alexxsys.transmit_bots_message.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue
    private UUID id;
    private Long timeStamp;
    private String patchFrom;
    private String patch;
    @ElementCollection
    @CollectionTable(name = "request_headers")
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> headers;
    @ElementCollection
    @CollectionTable(name = "request_parametrs")
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> parametrs;
    private byte[] body;

    public Request() {

    }
    public Request(UUID id, Long timeStamp, String patchFrom, String patch, Map<String, String> headers, Map<String, String> parametrs, byte[] body) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.patchFrom = patchFrom;
        this.patch = patch;
        this.headers = headers;
        this.parametrs = parametrs;
        this.body = body;
    }

    public UUID getId() {
        return id;
    }
    public Long getTimeStamp() {
        return timeStamp;
    }
    public String getPatchFrom() {
        return patchFrom;
    }
    public String getPatch() {
        return patch;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public Map<String, String> getParametrs() {
        return parametrs;
    }
    public byte[] getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        return id.equals(request.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Request{" +
                "patch='" + patch + '\'' +
                ", headers=" + headers.toString() +
                ", headers=" + parametrs.toString() +
                ", body='" + body + '\'' +
                '}';
    }

}
