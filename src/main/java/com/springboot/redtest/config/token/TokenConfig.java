package com.springboot.redtest.config.token;

public class TokenConfig {
    public static final long TOKEN_EXPIRATION_TIME = 2700000000L;    // 3600; // 2700000000L 2 Hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_SECRET = "3dec1d62686176BCA66599B0475FCC7FEA0A14C1F89B480E6C9F058E8b449bc9c1f812020651"; // UUID.randomUUID().toString().replace("-", "");
}
