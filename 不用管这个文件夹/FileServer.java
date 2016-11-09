package hei;

import java.io.*; 
import java.net.*;  
 
public class FileServer { 
	
   private ServerSocket server = null; 
   Socket socket = null; 

   public void getData(String savePath,int port)
    { 
	   int progress=0;//�������
	   try
	    {
		  //����socket����
	       server=new ServerSocket(port);
	        while((socket=server.accept())!=null)
	        {
	           //����socket������
	        	DataInputStream inputStream=new DataInputStream(
	        		new BufferedInputStream(socket.getInputStream()));
	        	//���û�������С
	        	  int BufferSize=8192;
	        	//������
	        	 byte[] buf=new byte[BufferSize];
	        	 int passedlen=0;//�ѽ����ļ���С
	        	 long len=0;//�ļ���С
	        	//��ȡ�ļ�����
	        	  if(!savaPath.contains("."))
	        	  savaPath+=inputStream.readUTF();
	        	  DataOutputStream fileout=new DataOutputStream(
	        		  new BufferedOutputStream(new BufferedOutputStream(
	        		    	new fileOutputStream(savaPath))));
	        	//��ȡ�ļ�����
	        	  len=inputStream.readLong();
	        		
	        	  System.out.println("�ļ��ĳ���Ϊ"+len+" KB");
	        	  System.out.println("��ʼ�����ļ�...");
	        		
	        	//��ȡ�ļ��������ǽ�����
	        	 System.out.println("#>>>>>>>>#>>>>>>>>>#>>>>>>>>>#>>>>>>>>>#>>>>>>>>>#");
	        	 System.out.println("#>>>>>>>>#>>>>>>>>>#>>>>>>>>>#>>>>>>>>>#>>>>>>>>>#");	
	        	
	        	 while(true)
	        	 {
	        		 int read=0;
	        		 if(inputStream!=null)
	        		 {
	        			 read=inputStream.read(buf);
	        		 }
	        		 passedlen+=len;
	        		 if(read=-1) break;
	        		 if((int)(passedlen*100.0/len)-progress>0)
	        		 {
	        			 progress=(int)(passedlen*100.0/len);
	        			 System.out.print("�ļ�������"+progress+"%");
	        			 System.out.println(">");
	        		 }
	        		 fileOut.write(buf,0,read);
	        	 }
	        	 System.out.println("\n");
	        	 System.out.println("������ɣ��ļ���Ϊ��"+savaPath);
	        	 fileOut.close();
	          }		
	       }catch(Exception e){
	    	   System.out.print("FileServer Exception is:"+e);
	    	   e.printStackTrace();
	       }
	}
   public static void main(String[] args){
	   //��������֮ǰҪָ���������ļ��ĵ�ַ
	   if(args.length!=2)
	   {
		   System.err.println("Usage: FileServer <save path> <port>");
		   System.exit(-1);
	   }
	   new FileServer().getData("D:\\\\", 9600);
	   new FileServer().getData(args[0],Integer.parseInt(args[1]));
   }
}
