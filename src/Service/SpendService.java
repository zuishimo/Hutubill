package Service;

import java.util.List;

import DAO.RecordDAO;
import Entity.Record;
import Page.SpendPage;
import Util.DateUtil;

public class SpendService {

	public SpendPage getSpendPage() {
		RecordDAO dao = new RecordDAO();
		
		//��������
		List<Record> thisMonthRecords = dao.listThisMonth();
		//��������
		List<Record> todayRecords = dao.listToday();
		//����������
		int thisMonthTotalDay = DateUtil.thisMonthTotalDay();
		
		int monthSpend = 0;
		int todaySpend = 0;
		int avgSpendDay = 0;
		int monthAvailable = 0;
		int dayAvgAvailable = 0;
		int monthLeftDay = 0;
		int usagePercentage = 0;
		
		//Ԥ��
		int monthBudget = new ConfigService().getIntBudget();
		//ͳ�Ʊ�������
		for (Record record : thisMonthRecords) {
			monthSpend +=record.getSpend();
		}
		//ͳ�ƽ�������
		for (Record record : todayRecords) {
			todaySpend += record.getSpend();
		}
		//�����վ�����
		avgSpendDay = monthSpend / thisMonthTotalDay;
		//���㱾��ʣ��
		monthAvailable = monthBudget - monthSpend;
		//������ĩ
		monthLeftDay = DateUtil.thisMonthLeftDay();
		//�����վ�����
		dayAvgAvailable = monthAvailable / monthLeftDay;
		//����ʹ�ñ���
		usagePercentage = monthSpend*100 / monthBudget;
		
		//������Щ��Ϣ������SpendPage����
		return new SpendPage(monthSpend, todaySpend, avgSpendDay, monthAvailable, dayAvgAvailable, monthLeftDay, usagePercentage);
		
	}
	
	
}
