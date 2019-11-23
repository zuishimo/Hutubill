package Service;

import java.util.Collections;
import java.util.List;

import DAO.CategoryDAO;
import DAO.RecordDAO;
import Entity.Category;
import Entity.Record;

public class CategoryService {

	CategoryDAO categoryDAO = new CategoryDAO();
	RecordDAO recordDAO = new RecordDAO();
	
	public List<Category> list(){
		List<Category> cs = categoryDAO.list();
		for (Category c : cs) {
			List<Record> rs = recordDAO.list(c.id);
			c.recordNumber = rs.size();
		}
		Collections.sort(cs,(c1,c2)->c2.recordNumber-c1.recordNumber);
		
		return cs;
	}
	
	public void add(String name) {
		Category c = new Category();
		c.setName(name);
		categoryDAO.add(c);
		
	}
	
	public void update(int id,String name) {
		Category c = new Category();
		c.setId(id);
		c.setName(name);
		
		categoryDAO.update(c);
	}
	
	public void delete(int id) {
		categoryDAO.delete(id);
	}
}
