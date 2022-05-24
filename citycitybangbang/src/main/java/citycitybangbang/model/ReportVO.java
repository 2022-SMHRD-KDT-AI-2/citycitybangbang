package citycitybangbang.model;

public class ReportVO {
	private int re_num;
	private String mem_id;
	private String re_date;
	private String acc_date;
	private String acc_place;
	private String image_file;
	private String re_comment;
	private char re_complete;
	
	public ReportVO() { };
	

	public ReportVO(int re_num, String mem_id, String re_date, String acc_date, String acc_place, String image_file,
			String re_comment, char re_complete) {
		super();
		this.re_num = re_num;
		this.mem_id = mem_id;
		this.re_date = re_date;
		this.acc_date = acc_date;
		this.acc_place = acc_place;
		this.image_file = image_file;
		this.re_comment = re_comment;
		this.re_complete = re_complete;
	}

	public int getRe_num() {
		return re_num;
	}

	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getRe_date() {
		return re_date;
	}

	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}

	public String getAcc_date() {
		return acc_date;
	}

	public void setAcc_date(String acc_date) {
		this.acc_date = acc_date;
	}

	public String getAcc_place() {
		return acc_place;
	}

	public void setAcc_place(String acc_place) {
		this.acc_place = acc_place;
	}

	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}

	public String getRe_comment() {
		return re_comment;
	}

	public void setRe_comment(String re_comment) {
		this.re_comment = re_comment;
	}

	public char getRe_complete() {
		return re_complete;
	}

	public void setRe_complete(char re_complete) {
		this.re_complete = re_complete;
	};
	
	
	

}
