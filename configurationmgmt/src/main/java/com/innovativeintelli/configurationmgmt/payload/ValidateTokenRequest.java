package com.innovativeintelli.configurationmgmt.payload;

import javax.validation.constraints.NotBlank;

public class ValidateTokenRequest {
    @NotBlank
    private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
   
}