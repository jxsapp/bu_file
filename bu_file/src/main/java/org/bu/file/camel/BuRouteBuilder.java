package org.bu.file.camel;

import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java DSL Router
 */
public class BuRouteBuilder extends RouteBuilder {

	public void configure() {
//		from("ftp://jxs@10.18.24.101:9999/test?password=123&binary=true&recursive=true&move=../test_succ/${file:name}&ftpClient.controlEncoding=utf8&ftpClientConfig.controlEncoding=utf8")// 下载
//				.log("Downloaded file ${file:name} start.")//
//				.to("file:///sharefile/abc/")//
//				.log("Downloaded file ${file:name} complete.")//
//		;// 下载
	}

}
