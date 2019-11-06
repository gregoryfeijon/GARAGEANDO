package br.com.ifsp.garageando.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 6 de nov de 2019
 * 
 * @author gregory.feijon
 */

public class Validadores {

	public static boolean validarEMail(String email) {
		Pattern p = Pattern.compile(
				"^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}
