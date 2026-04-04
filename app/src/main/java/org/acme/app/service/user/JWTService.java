package org.acme.app.service.user;

import io.smallrye.jwt.build.Jwt;
import org.jboss.logging.Logger;

import java.util.HashSet;
import java.util.List;

public class JWTService
{
    private static Logger log = Logger.getLogger(JWTService.class.getSimpleName());
    private static final String issuer = "https://car-rental.acme.org/user-service";
    private static final Long lifespan = 86400L; // seconds (1 day)

    public static long getTokenExpiration()
    {
        long now = System.currentTimeMillis() / 1000;
        long span = JWTService.lifespan + now;
        JWTService.log.infof("Emitting a JSON Web Token at %s(ms), with a %s(ms) lifespan.", now, JWTService.lifespan);
        return span;
    }

    /**
     * Sign a JWT based on application.properties `smallrye.jwt.sign.key.location`
     * @param username The user's username
     * @param roles List of the user's roles
     * @return A Signed JWT with username at upn, roles at groups, and id at uid.
     */
    public static String generateSignedToken(Long id, String username, List<String> roles)
    {
        return Jwt
                .issuer(JWTService.issuer)
                .claim("uid", id)
                .upn(username)
                .groups(new HashSet<>(roles))
                .expiresAt(JWTService.getTokenExpiration())
                .claim("v", "1.1")
                .sign();
    }
}
