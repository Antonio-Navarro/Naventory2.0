package com.antoniojnavarro.naventory.app.security.social;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonValue;
import com.restfb.types.User;

@Service
public class FacebookProvider extends BaseProvider {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accessToken;
	@Autowired
	private Environment env;

	private String appId;

	@PostConstruct
	public void initAppId() {
		this.appId = env.getProperty("facebook.appId");
	}

	public String login() {
		FacebookClient facebookClient = new DefaultFacebookClient(this.accessToken, Version.VERSION_3_1);
		User user = facebookClient.fetchObject("me", User.class,
				Parameter.with("fields", "email,first_name,last_name"));

		if (user == null) {
			return "error.login.facebook";
		}
		JsonObject jsonObject = facebookClient.fetchObject("/" + user.getId() + "/picture", JsonObject.class,
				Parameter.with("height", "720"), Parameter.with("redirect", "false"));
		JsonValue jsonValue = jsonObject.get("data");
		JsonObject object = jsonValue.asObject();
		String profileImageUrl = object.get("url").asString();
		return iniciarSesion(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), profileImageUrl);

	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}
