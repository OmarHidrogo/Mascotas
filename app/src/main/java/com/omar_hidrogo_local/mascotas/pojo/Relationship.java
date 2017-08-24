package com.omar_hidrogo_local.mascotas.pojo;

/**
 * Created by tmhidrooma on 23/08/2017.
 */

public class Relationship {

    private String outgoing_status;
    private String incoming_status;

    public Relationship(String outgoing_status, String incoming_status) {
        this.outgoing_status = outgoing_status;
        this.incoming_status = incoming_status;
    }

    public Relationship() {
    }

    public String getOutgoing_status() {
        return outgoing_status;
    }

    public void setOutgoing_status(String outgoing_status) {
        this.outgoing_status = outgoing_status;
    }

    public String getIncoming_status() {
        return incoming_status;
    }

    public void setIncoming_status(String incoming_status) {
        this.incoming_status = incoming_status;
    }
}
