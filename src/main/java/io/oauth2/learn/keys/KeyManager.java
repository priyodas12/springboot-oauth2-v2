package io.oauth2.learn.keys;

import com.nimbusds.jose.jwk.RSAKey;
//import org.bouncycastle.asn1.eac.RSAPublicKey;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;


@Component
public class KeyManager {

    public RSAKey rsaKey() {

        try {
            KeyPairGenerator kpg=KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp=kpg.generateKeyPair();

            RSAPublicKey publicKey= (RSAPublicKey) kp.getPublic();
            RSAPrivateKey privateKey= (RSAPrivateKey) kp.getPrivate();

            return new RSAKey
                    .Builder(publicKey)
                    .privateKey(privateKey)
                    .keyID("key-"+ UUID.randomUUID().toString())
                    .build();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
