package Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DAO.RecordDAO;
import Entity.Record;
import Util.DateUtil;

public class ReportService {
	
	//获取某一天的消费金额
	public int getDaySpend(Date d,List<Record> monthRawData) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String eachDay = sdf.format(d);
		int daySpend = 0;
		for (Record record : monthRawData) {
			String recordDate = sdf.format(record.date);
			if(eachDay.equals(recordDate)) {
				daySpend += record.spend;
			}
		}
		
		return daySpend;
	}

	//获取一个月的消费集合
	public List<Record> listThisMonthRecords(){
		RecordDAO dao = new RecordDAO();
		List<Record> monthRawData = dao.listThisMonth();
		List<Record> result = new ArrayList<Record>();
		Date monthBegin = DateUtil.monthBegin();
		int monthTotalDay = DateUtil.thisMonthTotalDay();
		Calendar c = Calendar.getInstance();
		
		for (int i = 1; i <=monthTotalDay; i++) {
			Record r = new Record();
			c.setTime(monthBegin);
			c.add(Calendar.DATE, i);
			Date eachDayOfThisMonth = c.getTime();
			int daySpend = getDaySpend(eachDayOfThisMonth, monthRawData);
			r.spend = daySpend;
			result.add(r);
		}
		return result;
	}
}
