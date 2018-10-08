package Bean;

public class ArticleBean {

	private int id;
	
	
	private String title;
	private String content;
	private String datetime;
	private String detail;
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	private int  agree;
	private String status;
	private int userid;
	private UserBean userBean;
	
	//private int talkid;
	private int saveid;
	
	public ArticleBean(){};
	public ArticleBean(int id, String title, String content, String datetime, String detail, int agree, String status,
			UserBean userBean) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.datetime = datetime;
		this.detail = detail;
		this.agree = agree;
		this.status = status;
		this.userBean = userBean;
	}
	public ArticleBean(int id, String title, String content, String datetime, String detail, int agree, String status,
			int userid) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.datetime = datetime;
		this.detail = detail;
		this.agree = agree;
		this.status = status;
		this.userid = userid;
	}
	
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public int getAgree() {
		return agree;
	}
	public void setAgree(int agree) {
		this.agree = agree;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getSaveid() {
		return saveid;
	}
	public void setSaveid(int saveid) {
		this.saveid = saveid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
