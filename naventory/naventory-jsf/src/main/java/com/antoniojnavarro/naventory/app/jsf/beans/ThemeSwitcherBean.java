package com.antoniojnavarro.naventory.app.jsf.beans;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.primefaces.component.themeswitcher.ThemeSwitcher;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;

import com.antoniojnavarro.naventory.app.commons.PFScope;

@Named("themeSwitcherBean")
@Scope(value = PFScope.SESSION_SCOPED)
public class ThemeSwitcherBean extends MasterBean {

	private String theme = "omega";
	private static final long serialVersionUID = 1L;

	private Locale locale;
	private String localeStr;
	private List<String> themes;
	private Map<String, Object> idiomas;

	@PostConstruct
	public void init() {
		locale = LocaleContextHolder.getLocale();
		localeStr = locale.getLanguage();

		themes = Arrays.asList(new String[] { "afterdark", "afternoon", "afterwork", "aristo", "black-tie", "blitzer",
				"bluesky", "bootstrap", "casablanca", "cupertino", "cruze", "dark-hive", "dot-luv", "eggplant",
				"excite-bike", "flick", "glass-x", "home", "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
				"omega", "overcast", "pepper-grinder", "redmond", "rocket", "sam", "smoothness", "south-street",
				"start", "sunny", "swanky-purse", "trontastic", "ui-darkness", "ui-lightness", "vader" });
	}

	public void cambiarTheme(AjaxBehaviorEvent ajax) {
		setTheme((String) ((ThemeSwitcher) ajax.getSource()).getValue());
	}

	public List<String> getThemes() {
		return themes;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void cambiarIdiomaChanged(ValueChangeEvent e) {
		localeStr = (String) e.getNewValue();

		switch (localeStr) {
		case "en":
			this.locale = Locale.ENGLISH;
			break;
		case "es":
			this.locale = new Locale("es", "ES");

			break;
		default:
			this.locale = new Locale("es", "ES");
			break;
		}
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Map<String, Object> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(Map<String, Object> idiomas) {
		this.idiomas = idiomas;
	}

	public String getLocaleStr() {
		return localeStr;
	}

	public void setLocaleStr(String localeStr) {
		this.localeStr = localeStr;
	}

}
