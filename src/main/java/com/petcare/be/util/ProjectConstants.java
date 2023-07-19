package com.petcare.be.util;

import java.util.Locale;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public final class ProjectConstants {

	// FIXME : Customize project constants for your application.

	public static final String DEFAULT_ENCODING = "UTF-8";

	public static final Locale VIETNAM_LOCALE = new Locale.Builder().setLanguage("" +
			"").setRegion("TR").build();

	private ProjectConstants() {

		throw new UnsupportedOperationException();
	}

}
