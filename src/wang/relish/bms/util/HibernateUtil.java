package wang.relish.bms.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	public static Session getSession(){

		Configuration conf = new Configuration(); //读取配置文件
		conf.configure();
		//通过SessionFactory来为你创建和缓存(pool)JDBC连接，打开一个session
		//一旦需要进行数据访问时，就会从连接池获得一个JDBC连接
		SessionFactory sf = conf.buildSessionFactory(); 
		return sf.openSession();
	}
}
