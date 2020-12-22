package com.authorization.authorization.model;

import java.util.Date;

public class UserSession {
	private String sessionId;
	private String jwtToken;
	private Date expiryTime;
}