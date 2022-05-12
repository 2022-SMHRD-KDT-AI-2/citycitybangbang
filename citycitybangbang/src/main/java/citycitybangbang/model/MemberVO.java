package citycitybangbang.model;

public class MemberVO {
	private String mem_id;
	private String mem_pwd;
	private String mem_name;
	private String mem_email;
	private char sex;
	private String mem_joindate;
	private char mem_type;
	
	public MemberVO() { }
	
	public MemberVO(String mem_id, String mem_pwd, String mem_name, String mem_email, char sex, String mem_joindate,
			char mem_type) {
		super();
		this.mem_id = mem_id;
		this.mem_pwd = mem_pwd;
		this.mem_name = mem_name;
		this.mem_email = mem_email;
		this.sex = sex;
		this.mem_joindate = mem_joindate;
		this.mem_type = mem_type;
	}


	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pwd() {
		return mem_pwd;
	}

	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public char getSex() {
		return sex;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public String getMem_joindate() {
		return mem_joindate;
	}

	public void setMem_joindate(String mem_joindate) {
		this.mem_joindate = mem_joindate;
	}

	public char getMem_type() {
		return mem_type;
	}

	public void setMem_type(char mem_type) {
		this.mem_type = mem_type;
	};

	
	
	
	
	
}
