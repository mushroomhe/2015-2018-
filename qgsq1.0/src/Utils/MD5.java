package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//MD5���ܷ�ʽ
//byte�ֽڵ���˼
//�ܱ��� 2017 5 6
public class MD5 {
	//ȫ������(���ɸ���)
	private final static String[]strDigit={
		"0" ,"1" ,"2" ,"3" ,"4" ,"5" ,"6" ,"7",
		"8" ,"9" ,"a" ,"b" ,"c" ,"d" ,"e" ,"f" 
	};
	//���캯��
	public MD5(){
		
	}
	
	//������ʽΪ�ַ���
	private static String byteToArrayString(byte bByte)
	{
		int iRet = bByte;
		//System.out.print("iRet="+iRet);
		if(iRet<0)
		{
			iRet += 256;
		}
		int iD1 = iRet /16 ;
		int iD2 = iRet %16 ;
		return strDigit[iD1]+strDigit[iD2];
	}
	
	//������ʽֻΪ����
	//����ע�Ĵ���Ԫ���ڲ���ĳЩ���汣�־�Ĭ_(û���õ���)
	@SuppressWarnings("unused")
	private static String byteToNum(byte bByte)
	{
		int iRet = bByte;
		//���Ժ���
		//System.out.print("iRet1=" + iRet);
		if(iRet<0)
		{
			iRet +=256;
		}
		return String.valueOf(iRet);
	}
	
	//ת���ֽ�����Ϊ16����
	private static String byteToString(byte[] bByte)
	{
		StringBuffer  sBuffer=new StringBuffer();
		for(int i =0; i < bByte.length; i++){
			sBuffer.append(byteToArrayString(bByte[i]));
		}
		return sBuffer.toString();
	}
	
	public static String GetMD5Code(String strObj)
	{
		String resultString = null;
		try
		{
			resultString = new String(strObj);
			//ΪӦ�ó����ṩ��ϢժҪ�㷨�Ĺ��ܣ������������С�����ݣ�������̶����ȵĹ�ϣֵ
			MessageDigest md = MessageDigest.getInstance("MD5");
			//md.digst()�ú�������ֵΪ��Ź�ϣֵ�����byte����
			resultString = byteToString(md.digest(strObj.getBytes()));
		}
		catch(NoSuchAlgorithmException  ex)
		{
			ex.printStackTrace();
		}
		return resultString;
	}
	
}
