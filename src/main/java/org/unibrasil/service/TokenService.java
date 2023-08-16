package org.unibrasil.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.lang.JoseException;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Collections;

@ApplicationScoped
public class TokenService {
    public static final String ROLE_USER = "User";
    public static final String ROLE_ADMIN = "Admin";

    @ConfigProperty(name = "mp.jwt.verify.privatekey")
    String privateKey;

    public String generate(String usuario) {
        try {
            var jwtClaims = new JwtClaims();
            jwtClaims.setIssuer("https://quarkus.io/using-jwt-rbac");
            jwtClaims.setJwtId("a-123");
            jwtClaims.setSubject(usuario);
            jwtClaims.setClaim(Claims.upn.name(), usuario);
            jwtClaims.setClaim(Claims.groups.name(), Collections.singletonList(ROLE_USER));
            jwtClaims.setAudience("using-jwt");
            jwtClaims.setExpirationTimeMinutesInTheFuture(10);

            return generateTokenString(jwtClaims);
        } catch (Exception e) {
            throw new RuntimeException("Erro desconhecido ao tentar gerar token!");
        }
    }

    private String generateTokenString(JwtClaims claims) throws NoSuchAlgorithmException, InvalidKeySpecException, JoseException {
        var encodedBytes = Base64.getDecoder().decode(privateKey);
        var keySpec = new PKCS8EncodedKeySpec(encodedBytes);
        var kf = KeyFactory.getInstance("RSA");
        var pk = kf.generatePrivate(keySpec);

        return generateTokenString(pk, claims);
    }

    private String generateTokenString(PrivateKey privateKey, JwtClaims claims) throws JoseException {
        var currentTimeInSecs = currentTimeInSecs();

        claims.setIssuedAt(NumericDate.fromSeconds(currentTimeInSecs));
        claims.setClaim(Claims.auth_time.name(), NumericDate.fromSeconds(currentTimeInSecs));

        var jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(privateKey);
        jws.setHeader("typ", "JWT");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        return jws.getCompactSerialization();
    }

    private static int currentTimeInSecs() {
        var currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}
