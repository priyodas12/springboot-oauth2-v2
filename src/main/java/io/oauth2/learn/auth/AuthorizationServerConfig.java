package io.oauth2.learn.auth;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import io.oauth2.learn.keys.KeyManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.web.SecurityFilterChain;
import java.util.UUID;

@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {

    private final KeyManager keyManager;


    public AuthorizationServerConfig(KeyManager keyManager) {
        this.keyManager = keyManager;
    }


    @Bean
    @Order(1)
    public SecurityFilterChain configSecurityFilterChain(HttpSecurity http) throws Exception{

        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        return http.formLogin().and().build();
    }



    //have to implement with JPA
    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        RegisteredClient reg1 = RegisteredClient.withId("client-" + UUID.randomUUID().toString())//internal Id
                .clientId("client")
                .clientSecret("secret")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
                .scope(OidcScopes.OPENID)
                .redirectUri("http://spring.io/auth")
                .build();
        return new InMemoryRegisteredClientRepository(reg1);
    }

    @Bean
    public ProviderSettings providerSettings() {
        return ProviderSettings.builder().build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        JWKSet set=new JWKSet(keyManager.rsaKey());
        return (j,sc)-> j.select(set);
    }
}
