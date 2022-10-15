package io.oauth2.learn.auth;

import io.oauth2.learn.config.PrivilegedUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import io.oauth2.learn.config.*;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("security.oauth2.resource.id")
    private String resource_id;
    @Value("security.oauth2.client.client-id")
    private String client_id;
    @Value("security.oauth2.client.client-secret")
    private String client_secret;
    TokenStore tokenStore=new InMemoryTokenStore();

    @Autowired
    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;


    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices tokenServices=new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }
    @Autowired
    private PrivilegedUserDetailsService privilegedUserDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(privilegedUserDetailsService);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(client_id)
                .authorizedGrantTypes("password","refresh_token")
                .scopes("read","write")
                .secret(bCryptPasswordEncoder.bCryptPasswordEncoder().encode(client_secret))
                .resourceIds(resource_id);
    }
}
