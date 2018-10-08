package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import Utils.Constants;
import Utils.DateUtil;
import Utils.MD5;
import Bean.ArticleBean;
import Bean.TopicBean;
import Bean.UserBean;
import Dao.UserDao;
import Utils.StringUtil;

public class UserServlet extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		String method=req.getParameter("method");
		if("login".equals(method))
		{
			login(req,resp);
		}
		else if("regist".equals(method))
		{
			regist(req,resp);
		}
		else if("end".equals(method)){
			end(req,resp);
		}
		else if("add".equals(method))
		{
			add(req,resp);
		}
		else if("update".equals(method))
		{
			update(req,resp);
		}
		else if("userself".equals(method))
		{
			userself(req,resp);
		}
	}
	
	private void userself(HttpServletRequest req, HttpServletResponse resp) throws
	IOException,ServletException{
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		List<TopicBean> list1=new ArrayList<TopicBean>();
		List<ArticleBean> list2=new ArrayList<ArticleBean>();
		UserDao userDao = new UserDao();
		int a=userDao.login(username, password);
		if(a==3)
		{
			UserBean userBean=new UserBean();
			userBean=userDao.getByusername(username);
			list1=userDao.getToListById(userBean.getId());
			list2=userDao.getBoListById(userBean.getId());
			int articlenumber=list2.size();
			//req.getSession().setAttribute("userBean", userBean);
			req.getSession().setAttribute(Constants.SESSION_USER_BEAN, userBean); 
			req.getSession().setAttribute("toList", list1);
			req.getSession().setAttribute("boList", list2);
			req.getSession().setAttribute("articlenumber", articlenumber);
			resp.sendRedirect(req.getContextPath()+"/user/userself.jsp?status="+a+"&&b="+articlenumber+"");
		}
		else
		{
			resp.sendRedirect(req.getContextPath()+"/login/login.jsp?status="+a+"");
		}
	}
	private void update(HttpServletRequest req, HttpServletResponse resp) 
	 throws ServletException,IOException
	{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		 int id = StringUtil.StringToInt(req.getParameter("id"));
			String password = req.getParameter("password");
			String nickname=req.getParameter("nickname");
			int age=StringUtil.StringToInt(req.getParameter("age"));
			String sex=req.getParameter("sex");
			String edugrade=req.getParameter("edugrade");
			UserDao userDao=new UserDao();
			UserBean userBean1=userDao.getById(id);
			userBean1.setPassword(password);
			userBean1.setNickname(nickname);
			userBean1.setAge(age);
			userBean1.setSex(sex);
			userBean1.setEdugrade(edugrade);
			UserBean userBean=new UserBean();
			boolean a=userDao.update(userBean1);
			if(a==true)
			{
				userBean=userDao.getById(id);
				 //req.setAttribute("userBean", userBean); 
				req.getSession().setAttribute("userBean", userBean);
				 System.out.println(userBean.getNickname());
				 resp.sendRedirect(req.getContextPath()+"/user/userself.jsp");

			}
	}

	//ע��(ͼƬ)
	 private void add(HttpServletRequest req, HttpServletResponse resp) 
	 throws UnsupportedEncodingException,IOException { 
	     req.setCharacterEncoding("utf-8"); 
	     int id = StringUtil.StringToInt(req.getParameter("id"));
	     System.out.println(id);
	     DiskFileItemFactory diskFileItemFactory = new 
	     DiskFileItemFactory(); 
	     ServletFileUpload upload = new 
	     ServletFileUpload(diskFileItemFactory); 
	     PrintWriter pw=resp.getWriter();
	     try { 
	       List<FileItem>fileItems = upload.parseRequest(req); 
	       UserBean userBean =new UserBean();
	       UserDao userDao=new UserDao();
	       userBean=userDao.getById(id);
	       for(FileItem item : fileItems){ 
	         item.getString("utf-8"); 
	         if(item.isFormField()){ 
	           //��������� 
	           processFormField(item,pw,userBean); 
	         }else{ 
	           //�����ϴ��ļ� 
	           System.out.println("tupian"); 
	           processUploadFile(item,pw,userBean); 
	         } 
	       } 
	       
	      
	       //boolean flag=userDao.checkLogin(userBean.getUsername());
	       if(id==0){
    	   	userDao.add(userBean);
			req.getSession().setAttribute("userBean", userBean);
			resp.sendRedirect(req.getContextPath()+"/user/userself.jsp");
				
			}else{
				
				 userDao.update(userBean);
		    	   req.getSession().setAttribute("userBean", userBean);
					resp.sendRedirect(req.getContextPath()+"/user/userself.jsp");
			}
	     } catch (Exception e) { 
	       e.printStackTrace(); 
	     } 
	     pw.close(); 
	   } 
	 
	////�����ϴ����ļ�
		private void processUploadFile(FileItem item, PrintWriter pw, UserBean userBean) {
			// TODO Auto-generated method stub
			String filename=item.getName();
			int index=filename.lastIndexOf(".");//�����ַ�������.������λ�õ������ַ�������֮����ַ���������ȡ��׺��
			filename=filename.substring(index+1,filename.length());//��ȡ�ļ��ĺ�׺
			String picPath=Constants.PIC_SHOW_PATH+DateUtil.getDateStr()+"/"+DateUtil.getTimeStr()+"."+filename;
			System.out.println(filename);
			long fileSize=item.getSize();//�ϴ��ļ��Ĵ�С
			if("".equals(filename)&&fileSize==0){
				return;
			}
			//�½��ļ��У�����Ϊ�ļ�������ʱ��Ϊ�ļ���
			File file=new File(Constants.PIC_UPLOAD_PATH +DateUtil.getDateStr());
			System.out.println(Constants.PIC_UPLOAD_PATH +DateUtil.getDateStr());
			file.mkdirs();//������·��
			File uploadFile=new File(Constants.PIC_UPLOAD_PATH +DateUtil.getDateStr()+"/"+DateUtil.getTimeStr()+"."+filename);
			try{
				item.write(uploadFile);
				userBean.setPicture(picPath);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
			
		////���������
		private void processFormField(FileItem item, PrintWriter pw, UserBean userBean)throws UnsupportedEncodingException, ServletException,IOException{
			// TODO Auto-generated method stub
			String name=item.getFieldName();
			String value=new String(item.getString("utf-8"));
			switch(name){
			/*case"id":
				int id=StringUtil.StringToInt(value);
				userBean.setId(id);
				break;*/
			case"username":
				userBean.setUsername(value);
				break;
			case"nickname":
				userBean.setNickname(value);
				break;
			case"password":
				userBean.setPassword(value);
				//String salt=StringUtil.getRandomString(10);
				//String md5Pwd=MD5.GetMD5Code(MD5.GetMD5Code(value)+salt);
				//userBean.setSalt(salt);
				//userBean.setPassword(md5Pwd);
				break;
			case"age":
				int age=StringUtil.StringToInt(value);
				userBean.setAge(age);
				break;
			case"sex":
				userBean.setSex(value);
				break;
			case"edugrade":
				userBean.setEdugrade(value);
				break;
			/*case"salt":
				userBean.setSalt(value);
				break;*/
			/*case"status":
				int status=StringUtil.StringToInt(value);
				userBean.setStatus(status);
				break;*/
			/*case"create_date":
				userBean.setCreatDate(value);
				break;*/
			/*case"sex":
				int sex=StringUtil.StringToInt(value);
				userBean.setSex(sex);
				break;*/
			
			/*case"truename":
				userBean.setTruename(value);
				break;*/
				
				default:
				break;
				
			}
			//����ʱ��
			SimpleDateFormat createDate1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userBean.setDatetime(createDate1.format(new Date()));
			//����״̬/////1��ʾ�û�״̬���ã�δ�����᣻0�����û�״̬�������ѱ�����
			//userBean.setStatus(1);
		}
	//ע��		
	private void regist(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String nickname=req.getParameter("nickname");
		int age=StringUtil.StringToInt(req.getParameter("age"));
		String sex=req.getParameter("sex");
		String edugrade=req.getParameter("edugrade");
		
		UserBean userBean = new UserBean();
		userBean.setUsername(username);
		userBean.setPassword(password);
		userBean.setNickname(nickname);
		userBean.setAge(age);
		userBean.setSex(sex);
		userBean.setEdugrade(edugrade);
		
		SimpleDateFormat createDate = new SimpleDateFormat("yyyy-MM-dd");
		userBean.setDatetime(createDate.format(new Date()));
		
		UserDao  userDao= new UserDao();
		
		if(userDao.checkLogin(username))
		{
			//�˺��Ѵ���
			resp.sendRedirect(req.getContextPath()+"/login/register.jsp?status=0");
		}
		else
		{
			//ִ����ӣ����ص�½
			userDao.add(userBean);
			req.getSession().setAttribute("userBean", userBean);
			resp.sendRedirect(req.getContextPath()+"/login/register.jsp?status=1");
		}
		
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		
		UserDao userDao = new UserDao();
		int a=userDao.login(username, password);
		if(a==3)
		{
			UserBean userBean=new UserBean();
			userBean=userDao.getByusername(username);
			req.getSession().setAttribute("userBean", userBean);
			resp.sendRedirect(req.getContextPath()+"/index/homepage.jsp?status="+a+"");
		}
		else
		{
			resp.sendRedirect(req.getContextPath()+"/login/login.jsp?status="+a+"");
		}
		
	}
	
	//�˳���¼
	private void end(HttpServletRequest req,HttpServletResponse resp)
	throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String status=req.getParameter("status");
		if(status!=null&&"1".equals(status)){
			req.getSession().invalidate();//ע���û�
			resp.sendRedirect(req.getContextPath()+"/login/login.jsp");//getContextPath���������Ŀ������
		}
	}	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
