package Bean;

public class TopicBean {

	private int id;
	
	private String title;
	private String content;
	private String datetime;
	private int  agree;
	private String status;
	private int userid;//一对一（可以做外键）
	private UserBean userBean;
	
	//private int talkid;//多对一（删掉 可以在contain中设计评论的Id）
	private int saveid;//收藏
	

	public TopicBean(int id,String title,String content,String datetime,int agree,String status,int userid)
	{
		this.setId(userid);
		this.setTitle(title);
		this.setContent(content);
		this.setDatetime(datetime);
		this.setAgree(agree);
		this.setStatus(status);
		this.setUserid(userid);
	}
	
	public TopicBean(int id,String title,String content,String datetime,int agree,String status,UserBean userBean)
	{
		this.setId(id);
		this.setTitle(title);
		this.setContent(content);
		this.setDatetime(datetime);
		this.setAgree(agree);
		this.setStatus(status);
		this.setUserBean(userBean);
	}
	
    public TopicBean(){
    	
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
