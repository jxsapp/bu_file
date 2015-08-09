package org.bu.file.init;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.bu.file.misc.SpringContextUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service("dataImport")
public class DataImportCommand extends Command {

	@Autowired
	private EntityManagerFactory emf;

	@Resource(name = "springContextUtil")
	protected SpringContextUtil springContextUtil;
	private LinkedList<Command> commands = new LinkedList<Command>();

	public void addDataImportCommand(Command command) {
		this.commands.add(command);
	}

	public void execute() {
		init();
		this.log.info("开始导入数据...，" + SystemInfo.getVersion());
		for (Command command : this.commands) {
			TransactionSynchronizationManager.bindResource(this.emf,
					new EntityManagerHolder(this.emf.createEntityManager()));
			this.log.info("为命令 " + command.getName() + " 打开EntityManager");
			try {
				command.execute();
				this.log.info("命令 " + command.getName() + " 执行成功");
			} catch (Exception e) {
				e.printStackTrace();
				this.log.info("命令 " + command.getName() + " 执行失败");
			}

			EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager
					.unbindResource(this.emf);
			EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
			this.log.info("为命令 " + command.getName() + " 关闭EntityManager");
		}
		this.log.info("完成数据导入...，" + SystemInfo.getVersion());
	}

	private void init() {
		InputStreamReader reader = null;
		InputStream in = null;
		LinkedList<String> datas = new LinkedList<String>();
		datas.add("/data/data.xml");
		datas.add("data.local.xml");

		for (String data : datas) {
			try {
				ClassPathResource cr = new ClassPathResource(data);
				in = cr.getInputStream();
				if (in == null)
					break;
				reader = new InputStreamReader(in, "UTF-8");
				if (reader != null) {
					Document document = new SAXReader().read(reader);
					parseDataImportCommand(document.selectNodes("//command"));
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

	private void parseDataImportCommand(List commands) {
		for (Iterator localIterator = commands.iterator(); localIterator.hasNext();) {
			Object obj = localIterator.next();
			Element element = (Element) obj;
			String commandName = element.attributeValue("name");
			try {
				Command command = (Command) SpringContextUtil.getBean(commandName);
				command.setName(commandName);
				addDataImportCommand(command);
			} catch (Exception e) {
				this.log.info("Spring容器中没有命令: " + commandName);
			}
		}
	}

	private void printCommand() {
		for (Command command : this.commands)
			this.log.info(command.getName());
	}
}
