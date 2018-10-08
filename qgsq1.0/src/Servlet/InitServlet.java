package Servlet; 
 
 
import java.io.FileInputStream; 
import java.util.Properties; 
 
import javax.servlet.ServletConfig; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
 
import Utils.Constants; 
 
 
public class InitServlet extends HttpServlet{ 
 
  @Override 
  public void init(ServletConfig config) throws ServletException { 
    super.init(config); 
    System.out.println("后台配置初始化开始"); 
    String path; 
    FileInputStream fis; 
    try { 
      //读取conf.properties文件 
      path = InitServlet.class.getResource("/").getPath(); 
      fis = new FileInputStream(path+"conf.properties"); 
      Properties properties = new Properties(); 
      properties.load(fis); 
      fis.close(); 
       
      //覆盖Constants里和图片上传相关的属性 
      String picUploadPath = properties.getProperty("pic_upload_path"); 
      if(picUploadPath!=null) { 
        Constants.PIC_UPLOAD_PATH = picUploadPath; 
      } 
      String picShowPath = properties.getProperty("pic_show_path"); 
      if(picShowPath!=null) { 
        Constants.PIC_SHOW_PATH = picShowPath; 
      } 
    } catch (Exception e) { 
      e.printStackTrace(); 
    } 
  } 
} 
