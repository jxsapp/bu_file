package org.bu.core.pact;

import java.nio.charset.Charset;

import org.apache.http.NameValuePair;

public class PostParamsUtils {

	private static final char QP_SEP_A = '&';
	private static final String NAME_VALUE_SEPARATOR = "=";

	/**
	 * Returns a String that is suitable for use as an
	 * {@code application/x-www-form-urlencoded} list of parameters in an HTTP
	 * PUT or HTTP POST.
	 *
	 * @param parameters
	 *            The parameters to include.
	 * @param charset
	 *            The encoding to use.
	 * @return An {@code application/x-www-form-urlencoded} string
	 *
	 * @since 4.2
	 */
	public static String format(final Iterable<? extends NameValuePair> parameters, final Charset charset) {
		return format(parameters, QP_SEP_A, charset);
	}

	/**
	 * Returns a String that is suitable for use as an
	 * {@code application/x-www-form-urlencoded} list of parameters in an HTTP
	 * PUT or HTTP POST.
	 *
	 * @param parameters
	 *            The parameters to include.
	 * @param parameterSeparator
	 *            The parameter separator, by convention, {@code '&'} or
	 *            {@code ';'}.
	 * @param charset
	 *            The encoding to use.
	 * @return An {@code application/x-www-form-urlencoded} string
	 *
	 * @since 4.3
	 */
	public static String format(final Iterable<? extends NameValuePair> parameters, final char parameterSeparator,
			final Charset charset) {
		final StringBuilder result = new StringBuilder();
		for (final NameValuePair parameter : parameters) {
			final String encodedName = parameter.getName();
			final String encodedValue = parameter.getValue();
			if (result.length() > 0) {
				result.append(parameterSeparator);
			}
			result.append(encodedName);
			if (encodedValue != null) {
				result.append(NAME_VALUE_SEPARATOR);
				result.append(encodedValue);
			}
		}
		return result.toString();
	}

}