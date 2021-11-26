package beans;

import java.sql.Date;

public class IssueCarBean {
	private String callno, customerid, customername;
	private long customermobile;
	private Date issueddate;
	private String returnstatus;

	public IssueCarBean() {
	}

	public IssueCarBean(String callno, String customerid, String customername, long customermobile) {
		super();
		this.callno = callno;
		this.customerid = customerid;
		this.customername = customername;
		this.customermobile = customermobile;
		
	}

	public String getCallno() {
		return callno;
	}

	public void setCallno(String callno) {
		this.callno = callno;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public long getCustomermobile() {
		return customermobile;
	}

	public void setCustomermobile(long customermobile) {
		this.customermobile = customermobile;
	}

	public Date getIssueddate() {
		return issueddate;
	}

	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	
}
