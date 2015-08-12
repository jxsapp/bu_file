package org.bu.file.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.bu.file.dao.BuMenuTypeDao;
import org.bu.file.model.BuMenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service("buMenuTypeCmd")
public class BuMenuTypeCmd extends BuCmd {

	@Autowired
	private BuMenuTypeDao buMenuTypeDao;

	public void execute() {
		log.info("来数据了。。。");
		ClassPathResource cr = new ClassPathResource("/data/menu_type.txt");
		try {
			InputStreamReader reader = new InputStreamReader(cr.getInputStream(), "UTF-8");
			BufferedReader dr = new BufferedReader(reader);
			String line = "";
			while ((line = dr.readLine()) != null) {
				BuMenuType type = new BuMenuType();
				String[] types = line.split(",");
				if (null != types && types.length == 4) {
					type.setBasePath(types[0]);
					type.setMenuId(types[1]);
					type.setTpName(types[2]);
					type.setTpDesc(types[3]);
				}
				buMenuTypeDao.saveOrUpdate(type);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
