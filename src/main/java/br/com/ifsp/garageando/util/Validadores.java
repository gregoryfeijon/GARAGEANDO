package br.com.ifsp.garageando.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validadores {

	public static boolean validarEMail(String email) {
		Pattern p = Pattern.compile(
				"^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
