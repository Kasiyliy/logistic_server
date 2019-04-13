package kz.logistic.logistic_server.shared.security;

/**
 * @author Assylkhan
 * on 15.03.2019
 * @project realq
 */
public class SecurityConstants {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users";
    public static final String LOGIN_URL = "/login";
    public static final String SOCKET_URL = "/socket/**";
}
