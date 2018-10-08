package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import Bean.ArticleBean;
import Bean.ContainBean;
import Bean.PagingBean;
import Utils.Constants;
import Bean.TopicBean;
import Dao.ArticleDao;
import Dao.ContainDao;
import Dao.TopicDao; 
import Utils.StringUtil; 

@WebServlet("/TopicServlet")
public class TopicServlet extends HttpServlet{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
		req.setCharacterEncoding("utf-8");
		String method=req.getParameter("method");
		if("search".equals(method))
		{
			search(req,resp);
		}
		else if("details".equals(method))
		{
			details(req,resp);
		}
		else if("list".equals(method))
		{
			list(req,resp);
		}
		else if("readList".equals(method))
		{
			readList(req,resp);
		}
		else if("lists".equals(method))
		{
			lists(req,resp);
		}else if("add".equals(method))
		{
			add(req,resp);
		}else if("addcontain".equals(method))
		{
			addcontain(req,resp);
		}
	}
	
	//�������
	private void addcontain(HttpServletRequest req, HttpServletResponse resp)throws 
	IOException,ServletException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		int userid=StringUtil.StringToInt(req.getParameter("userid"));
		int topicid=StringUtil.StringToInt(req.getParameter("topicid"));
		String content=req.getParameter("content");
		SimpleDateFormat createDate = new SimpleDateFormat("yyyy-MM-dd");
		ContainBean containBean = new ContainBean();
		containBean.setUserid(userid);
		containBean.setTopicid(topicid);
		containBean.setContent(content);
		containBean.setDatetime(createDate.format(new Date()));
		
		ContainDao containDao = new ContainDao();
		containDao.add(containBean);
		
		//��ʾ������Ϣ
		TopicDao topicDao=new TopicDao();
		TopicBean topicBean=topicDao.getById(userid);
		req.getSession().setAttribute("topicBean",topicBean);
		
		//��ʾд���µ��˵�����
		List<TopicBean> list=new ArrayList<TopicBean>();
		//ArticleDao articalDao=new ArticleDao();
		list=topicDao.getReadList();
		req.getSession().setAttribute("newtopicBeans", list);
		
		//��ʾ���˵�����
		List<ContainBean> contains=new ArrayList<ContainBean>();
		//ContainDao containDao=new ContainDao();
		contains=containDao.gettopicContainbyId(userid);
		req.getSession().setAttribute("contains", contains);
		resp.sendRedirect(req.getContextPath()+"/user/usertie.jsp?status=1");
		
		
	}
	//��ӻ���
	private void add(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		TopicBean topicBean = new TopicBean();
		
		String title=req.getParameter("title");
		String content=req.getParameter("intro");
		//String detail=req.getParameter("information");
		//�˴���Ҫ�޸�
		int userid=StringUtil.StringToInt(req.getParameter("userid"));
		//����ʱ��
		SimpleDateFormat createDate = new SimpleDateFormat("yyyy-MM-dd");
		topicBean.setTitle(title);
		topicBean.setContent(content);
		//articleBean.setDetail(detail);
		topicBean.setDatetime(createDate.format(new Date()));
		topicBean.setUserid(userid);
		
		TopicDao topicDao = new TopicDao();
		
		if(topicDao.add(topicBean))
		{
			
			resp.sendRedirect(req.getContextPath()+"/user/addbowen.jsp?status=0");
		}
		else
		{
			resp.sendRedirect(req.getContextPath()+"/user/addbowen.jsp?status=1");
		}
		
	}


	//topic��ҳ��Ϣ
	private void lists(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{ 
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		//List<TopicBean> list=new ArrayList<TopicBean>();
		TopicDao topicDao=new TopicDao();
		int currentPage=StringUtil.StringToInt(req.getParameter("currentPage"));
		int countSize=TopicDao.getCount();
		//Constants.PAGE_SIZE_1: �ó�����ʾ��	һҳ����������,����size1Ϊһҳһ������
		PagingBean pagingBean=new PagingBean(currentPage,countSize,Constants.PAGE_SIZE_5);
		List<TopicBean> topicBeans=topicDao.getListByPage(currentPage * 
				Constants.PAGE_SIZE_5,Constants.PAGE_SIZE_5);
		pagingBean.setPrefixUrl(req.getContextPath()+"/topicServlet?method=list");
		pagingBean.setAnd(true);
		req.getSession().setAttribute("topicBeans", topicBeans); 
		req.getSession().setAttribute("pagingBean", pagingBean);
		
		
		//list=topicDao.getList();
		//req.setAttribute("topicBeans", list);
		//req.getRequestDispatcher("index.jsp").forward(req,resp);
		
		req.setCharacterEncoding("utf-8");
		List<TopicBean> list=new ArrayList<TopicBean>();
		//TopicDao topicDao=new TopicDao();
		list=topicDao.getReadList();
		req.getSession().setAttribute("newTopicBeans", list);
		req.getRequestDispatcher("topic.jsp").forward(req,resp);
	}



	///Ѱ�ҽ���δ���
	private void search(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException,IOException{
		// TODO Auto-generated method stub
		String chars=req.getParameter("key");
		TopicDao topicDao=new TopicDao();
		List<TopicBean> topicBeans=new ArrayList<>();
		topicBeans=topicDao.getLists(chars);
		req.setAttribute("topicBeans", topicBeans);
		if(topicBeans.size()>0){
			try
			{
				req.getRequestDispatcher("topic.jsp").forward(req, resp);
			}catch(ServletException | IOException e){
				e.printStackTrace();
			}
		}
		else
		{
			try{
				req.getRequestDispatcher("topic.jsp?status=1").forward(req, resp);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	//��ʾ��������
	private void details(HttpServletRequest req,HttpServletResponse resp)
		throws ServletException,IOException{
		req.setCharacterEncoding("UTF-8");
		//��ʾ������Ϣ
		int id=StringUtil.StringToInt(req.getParameter("id"));
		TopicDao topicDao=new TopicDao();
		TopicBean topicBean=topicDao.getById(id);
		req.getSession().setAttribute("topicBean",topicBean);
		
		//��ʾ���еĻ���
		int userid=topicBean.getUserid();
		List<TopicBean> list=new ArrayList<TopicBean>();
		//ArticleDao articalDao=new ArticleDao();
		list=topicDao.getReadList();
		req.getSession().setAttribute("newtopicBeans", list);
		
		//��ʾ���˵�����
		List<ContainBean> contains=new ArrayList<ContainBean>();
		ContainDao containDao=new ContainDao();
		contains=containDao.gettopicContainbyId(id);
		req.getSession().setAttribute("contains", contains);
		resp.sendRedirect(req.getContextPath()+"/user/usertie.jsp");
	}
	
	//��ʾ�����б����������棩
	private void list(HttpServletRequest req,HttpServletResponse resp)
		throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		//List<TopicBean> list=new ArrayList<TopicBean>();
		TopicDao topicDao=new TopicDao();
		int currentPage=StringUtil.StringToInt(req.getParameter("currentPage"));
		int countSize=TopicDao.getCount();
		//Constants.PAGE_SIZE_1: �ó�����ʾ��	һҳ����������,����size1Ϊһҳһ������
		PagingBean pagingBean=new PagingBean(currentPage,countSize,Constants.PAGE_SIZE_5);
		List<TopicBean> topicBeans=topicDao.getListByPage(currentPage * 
				Constants.PAGE_SIZE_5,Constants.PAGE_SIZE_5);
		pagingBean.setPrefixUrl(req.getContextPath()+"/topicServlet?method=list");
		pagingBean.setAnd(true);
		req.setAttribute("topicBeans", topicBeans); 
		req.setAttribute("pagingBean", pagingBean);
		
		
		//list=topicDao.getList();
		//req.setAttribute("topicBeans", list);
		req.getRequestDispatcher("topic.jsp").forward(req,resp);
	}
	
	//�Ķ����а�
	private void readList(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException,IOException{
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		List<TopicBean> list=new ArrayList<TopicBean>();
		TopicDao topicDao=new TopicDao();
		list=topicDao.getReadList();
		req.setAttribute("newTopicBeans", list);
		req.getRequestDispatcher("topic.jsp").forward(req,resp);
	}
}
