package org.bu.file.init;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.bu.file.misc.SpringContextUtil;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service("dataImport")
public class DataImportCmd extends BuCmd {

	@Autowired
	private EntityManagerFactory emf;

	@Resource(name = "springContextUtil")
	protected SpringContextUtil springContextUtil;
	private LinkedList<BuCmd> commands = new LinkedList<BuCmd>();

	public void addDataImportCommand(BuCmd command) {
		this.commands.add(command);
	}

	public void execute() {
		init();
		this.log.info("开始导入数据...，" + SystemInfo.getVersion());
		for (BuCmd command : this.commands) {
			TransactionSynchronizationManager.bindResource(this.emf, new EntityManagerHolder(this.emf.createEntityManager()));
			this.log.info("为命令 " + command.getName() + " 打开EntityManager");
			try {
				command.execute();
				this.log.info("命令 " + command.getName() + " 执行成功");
			} catch (Exception e) {
				e.printStackTrace();
				this.log.info("命令 " + command.getName() + " 执行失败");
			}

			EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager.unbindResource(this.emf);
			EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
			this.log.info("为命令 " + command.getName() + " 关闭EntityManager");
		}
		this.log.info("完成数据导入...，" + SystemInfo.getVersion());
	}

	public static List<BuCmd> getObj(String json) {
		List<BuCmd> list = null;
		java.lang.reflect.Type type = new TypeToken<List<BuCmd>>() {
		}.getType();
		Gson gson = new Gson();
		list = gson.fromJson(json, type);
		return list;

	}

	private static byte[] readFile(InputStream in) throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		while ((len = in.read(buffer)) >= 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.flush();
		buffer = out.toByteArray();
		return buffer;
	}

	private void init() {
		InputStreamReader reader = null;
		InputStream in = null;
		LinkedList<String> datas = new LinkedList<String>();
		datas.add("/data/data.conf");
		datas.add("data.local.conf");

		for (String data : datas) {
			try {
				ClassPathResource cr = new ClassPathResource(data);
				in = cr.getInputStream();
				if (in != null) {
					String str = new String(readFile(in));
					JSONObject json = new JSONObject(str);
					List<BuCmd> cmds = getObj(json.getString("cmds"));
					parseDataImportCommand(cmds);
				}
				this.log.info("读取【" + data + "】文件成功！");
			} catch (Exception e) {
				this.log.info("读取【" + data + "】文件失败,原因：" + e.getMessage());
			} finally {
				if (reader != null) {
					try {
						reader.close();
						reader = null;
						in = null;
					} catch (IOException e) {
						this.log.info("关闭【" + data + "】文件失败,原因：" + e.getMessage());
					}
				}
			}
		}
		this.log.info("解析数据导入命令文件成功，需要导入的命令有：");
		printCommand();
	}

	private void parseDataImportCommand(List<BuCmd> commands) {
		for (BuCmd cmd : commands) {
			String commandName = cmd.getName();
			try {
				BuCmd command = (BuCmd) SpringContextUtil.getBean(commandName);
				command.setName(commandName);
				addDataImportCommand(command);
			} catch (Exception e) {
				this.log.info("Spring容器中没有命令: " + commandName);
			}
		}
	}

	private void printCommand() {
		for (BuCmd command : this.commands)
			this.log.info(command.getName());
	}
}
