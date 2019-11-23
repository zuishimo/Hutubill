package Service;

import java.util.Date;

import DAO.RecordDAO;
import Entity.Category;
import Entity.Record;
import Gui.Panel.RecordPanel;

public class RecordService {
	
	RecordDAO recordDAO = new RecordDAO();

	public void add(int spend,Category c,String comment,Date d) {
		Record r = new Record();
		r.spend = spend;
		r.cid = c.id;
		r.comment = comment;
		r.date = d;
		
		recordDAO.add(r);
	}
}
