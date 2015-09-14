/**
 * 
 */
package org.bu.file.web.cli;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bu.core.misc.BuGenerallyHolder;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.web.ControllerSupport;
import org.bu.file.model.BuHardware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jxs
 */
@Controller
@RequestMapping("/client/monitor")
public class BuCliMonitorController extends ControllerSupport {

	/**
	 * 测试联通性 "connect"
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/connect", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst connect(HttpServletRequest request, HttpServletResponse response) {
		return getBuRst(request, response, authService, new BuRstObject() {

			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				return BuGenerallyHolder.nextSerialNumber();
			}
		});
	}

	@RequestMapping(value = "/hardware", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst hardware(HttpServletRequest request, HttpServletResponse response) {
		final String path = request.getParameter("path");
		return getBuRst(request, response, authService, new BuRstObject() {

			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				return BuHardware.getData(path);
			}
		});
	}

}
