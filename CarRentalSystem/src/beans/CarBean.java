package beans;

public class CarBean {
	private String callno, carname, company, condition;
	private int quantity, issued;

	public CarBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public CarBean(String callno, String carname, String company, String condition, int quantity) {
		super();
		this.callno = callno;
		this.carname = carname;
		this.company = company;
		this.condition = condition;
		this.quantity = quantity;
		
	}


	public String getCallno() {
		return callno;
	}

	public void setCallno(String callno) {
		this.callno = callno;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getIssued() {
		return issued;
	}

	public void setIssued(int issued) {
		this.issued = issued;
	}

	
}
