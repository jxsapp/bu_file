package org.bu.file.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.bu.file.dao.BuMenuDao;
import org.bu.file.model.BuMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service("buMenuCmd")
public class BuMenuCmd extends BuCmd {

	@Autowired
	private BuMenuDao BuMenuDao;

	public void execute() {
		log.info("来数据了。。。");
		ClassPathResource cr = new ClassPathResource("/data/menus.txt");
		try {
			InputStreamReader reader = new InputStreamReader(cr.getInputStream(), "UTF-8");
			BufferedReader dr = new BufferedReader(reader);
			String line = "";
			while ((line = dr.readLine()) != null) {
				BuMenu type = new BuMenu();
				String[] types = line.split(",");
				if (null != types && types.length == 4) {
					type.setBasePath(types[0]);
					type.setMenuId(types[1]);
					type.setTpName(types[2]);
					type.setTpDesc(types[3]);
				}
				BuMenuDao.saveOrUpdate(type);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
