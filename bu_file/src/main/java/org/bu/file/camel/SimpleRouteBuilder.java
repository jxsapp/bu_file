package org.bu.file.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @author Bruno Dusausoy
 */
@Component
public class SimpleRouteBuilder extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		errorHandler(defaultErrorHandler().maximumRedeliveries(5).backOffMultiplier(2).retryAttemptedLogLevel(LoggingLevel.WARN));

		from("file:src/data?noop=true")//
				.log("")//
				.to("sftp://${hostname}:${properties.port}/${properties.inputDir}?username=${properties.user}&password=${properties.password}&fileName=${header.CamelFileNameOnly}")//
				.log("");

	}
}
