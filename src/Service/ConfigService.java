package Service;

import DAO.ConfigDAO;
import Entity.Config;

public class ConfigService {

	public static final String budget = "budget";
	public static final String mysqlPath = "mysqlPath";
	public static final String default_budget = "500";
	
	static ConfigDAO dao = new ConfigDAO();
	
	public ConfigService() {
		init();
	}
	
	public static void init() {
		init(budget,default_budget);
		init(mysqlPath,"");
	}   
	
	public static void init(String key ,String value) {
		Config config = dao.getByKey(key);
		if(config==null) {
			Config c = new Config();
			c.setKey(key);
			c.setValue(value);
			dao.add(c);		
		}
		
	}
	
	public String get(String key) {
		Config c = dao.getByKey(key);
		return c.getValue();
	}
	
	public void update(String key,String value) {
		
		Config c = dao.getByKey(key);
		c.setValue(value);
		dao.update(c);
	}
	
	public int getIntBudget() {
		return Integer.parseInt(get(budget));
	}
	
}
