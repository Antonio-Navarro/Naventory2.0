package com.antoniojnavarro.naventory.app.commons.utils;

import javax.servlet.http.HttpServletRequest;

public class AOPUtils {

	public static String buildRequestURL(HttpServletRequest req) {
		return req.getScheme() + "://" + req.getServerName()
				+ ((req.getScheme().equalsIgnoreCase("http") && req.getServerPort() == 80)
						|| (req.getScheme().equalsIgnoreCase("https") && req.getServerPort() == 443) ? new String()
								: ":" + req.getServerPort())
				+ (req.getContextPath() != null && !req.getContextPath().isEmpty() ? req.getContextPath()
						: new String());
	}
}
