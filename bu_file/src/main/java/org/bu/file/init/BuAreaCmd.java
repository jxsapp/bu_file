package org.bu.file.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.bu.file.dao.BuAreaDao;
import org.bu.file.model.BuArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service("buAreaCmd")
public class BuAreaCmd extends BuCmd {

	@Autowired
	private BuAreaDao areaDao;

	public void execute() {
		log.info("来数据了。。。");
		ClassPathResource cr = new ClassPathResource("/data/area_code.txt");
		try {
			InputStreamReader reader = new InputStreamReader(cr.getInputStream(), "UTF-8");
			BufferedReader dr = new BufferedReader(reader);
			String line = "";
			while ((line = dr.readLine()) != null) {
				BuArea area = new BuArea();
				String[] aras = line.split(",");
				if (null != aras && aras.length == 3) {
					area.setName(aras[0]);
					area.setParent(aras[1]);
					area.setCode(aras[2]);
				}
				areaDao.saveOrUpdate(area);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
