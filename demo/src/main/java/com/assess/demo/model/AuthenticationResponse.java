package com.assess.demo.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private  String jwt;
    
    
    public AuthenticationResponse() {
    	
    }
    

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
