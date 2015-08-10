package org.bu.file.init;

import org.bu.file.log.LogHolder;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service("buAreaCmd")
public class BuAreaCmd extends Cmd {

	private Logger logger = LogHolder.getLogger(BuAreaCmd.class);

	public void execute() {

	}
}
