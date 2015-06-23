package org.bu.file.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.bu.file.misc.Error;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转拦截器
 * 
 * @author Jiang XuSheng
 */
@Controller
public class RedirectPage extends BasicController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView redirectWebIndex() {
		return new ModelAndView("login", defaultModel());
	}

	@RequestMapping(value = "/error404", method = RequestMethod.GET)
	public void error404(HttpServletResponse response) {
		try {
			exception(response, Error.EXCEPTION_404);
		} catch (IOException e) {
		}
	}

	@RequestMapping(value = "/error500", method = RequestMethod.GET)
	public void error500(HttpServletResponse response) {
		try {
			exception(response, Error.EXCEPTION_500);
		} catch (IOException e) {
		}
	}

}
