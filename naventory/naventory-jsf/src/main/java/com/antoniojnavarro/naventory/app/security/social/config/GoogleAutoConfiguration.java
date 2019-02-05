//package com.antoniojnavarro.naventory.app.security.social.config;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionFactory;
//import org.springframework.social.connect.ConnectionRepository;
//import org.springframework.social.connect.web.GenericConnectionStatusView;
//import org.springframework.social.google.api.Google;
//import org.springframework.social.google.connect.GoogleConnectionFactory;
//
//@Configuration
//public class GoogleAutoConfiguration {
//
//	@Configuration
//	@EnableSocial
//	protected static class GoogleConfigurerAdapter extends SocialAutoConfigurerAdapter {
//
//		protected GoogleConfigurerAdapter(GoogleProperties properties) {
//			this.properties = properties;
//		}
//
//		@Bean
//		@ConditionalOnMissingBean(Google.class)
//		@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
//		public Google google(ConnectionRepository repository) {
//			Connection<Google> connection = repository.findPrimaryConnection(Google.class);
//			return connection != null ? connection.getApi() : null;
//		}
//
//		@Bean(name = { "connect/googleConnect", "connect/googleConnected" })
//		public GenericConnectionStatusView googleConnectView() {
//			return new GenericConnectionStatusView("google", "Google");
//		}
//
//		@Override
//		protected ConnectionFactory<?> createConnectionFactory() {
//			return new GoogleConnectionFactory(this.properties.getAppId(), this.properties.getAppSecret());
//		}
//
//	}
//
//}