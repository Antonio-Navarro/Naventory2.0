package com.antoniojnavarro.naventory.app.security.social.providers;

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

		return iniciarSesion(user.getEmail(), user.getFirstName(), user.getLastName(), profileImageUrl);

	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
