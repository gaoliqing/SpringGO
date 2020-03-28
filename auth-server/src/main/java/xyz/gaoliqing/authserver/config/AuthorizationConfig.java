//package xyz.gaoliqing.authserver.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
///**
// * @author Mr.GaoLiqing
// * @create 2020-03-18 17:52
// * @description 授权服务的配置类
// */
//
//@Configuration
//public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//
//    /**
//     * 配置OAuth2的客户端, 就是第三方应用
//     *
//     * @param clients .
//     * @throws Exception .
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//        clients.inMemory()
//                .withClient("clientId")
//                .secret("123")
//                .redirectUris("http://www.baidu.com")
//                .authorizedGrantTypes("authorization_code")
//                .scopes("read", "write")
//                .and()
//                .withClient("abc")
//                .secret("123")
//                .authorizedGrantTypes("password")
//                .scopes("admin");
//    }
//
//    /**
//     * 哪些URL可以匿名访问
//     *
//     * @param security .
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) {
//
//        security.allowFormAuthenticationForClients();
//        // 要访问认证服务器tokenKey的时候需要经过身份认证
//        security.tokenKeyAccess("isAuthenticated()");
//    }
//
//    @Bean
//    public TokenStore jwtTokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        // 保证JWT安全的唯一方式
//        jwtAccessTokenConverter.setSigningKey("glq");
//        return jwtAccessTokenConverter;
//    }
//    /**
//     * Token的存储
//     *
//     * @param endpoints .
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//        endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
//    }
//}
