package reports;

import java.util.ArrayList;

public class ReportNotice {
	String _noticeType;
	ArrayList<NoticeData> _notices;
	
	/**
	 * @return the _noticeType
	 */
	public String getNoticeType() {
		return _noticeType;
	}
	/**
	 * @param _noticeType the _noticeType to set
	 */
	public void setNoticeType(String noticeType) {
		_noticeType = noticeType;
	}
	/**
	 * @return the _notices
	 */
	public ArrayList<NoticeData> getNotices() {
		return _notices;
	}
	/**
	 * @param _notices the _notices to set
	 */
	public void setNotices(ArrayList<NoticeData> notices) {
		_notices = notices;
	}
	
	public void addNotice(NoticeData notice){
		_notices.add(notice);
	}
	
}
