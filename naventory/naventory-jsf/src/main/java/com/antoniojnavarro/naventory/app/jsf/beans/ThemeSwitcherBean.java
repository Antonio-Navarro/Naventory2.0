package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.antoniojnavarro.naventory.app.commons.PFScope;

@Named("themeSwitcherBean")
@Scope(value = PFScope.SESSION_SCOPED)
public class ThemeSwitcherBean extends MasterBean {

	private static final long serialVersionUID = 1L;

	private List<String> themes;

	@PostConstruct
	public void init() {
		themes = Arrays.asList(new String[] { "afterdark", "afternoon", "afterwork", "aristo", "black-tie", "blitzer",
				"bluesky", "bootstrap", "casablanca", "cupertino", "cruze", "dark-hive", "delta", "dot-luv", "eggplant",
				"excite-bike", "flick", "glass-x", "home", "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
				"omega", "overcast", "pepper-grinder", "redmond", "rocket", "sam", "smoothness", "south-street",
				"start", "sunny", "swanky-purse", "trontastic", "ui-darkness", "ui-lightness", "vader" });
	}

	public List<String> getThemes() {
		return themes;
	}
}
