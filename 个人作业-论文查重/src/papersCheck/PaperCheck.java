package papersCheck;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;


public interface PaperCheck {
	String fileName = "result.txt";
	Path path = Paths.get(fileName);
	
	public static void main(String[] args) throws IOException  {
		//��ȡ�ļ���ַ
		File[] file=new File[2];
		file[0]=new File("copy.txt");
		file[1]=new File("papers.txt");
		writeTxt("------��ʼ�Ա�------","","",1);
		String[][] pages=new String[2][];//�洢ÿһ�ε�����
		String[][][] lines=new String[2][][];//�洢ÿһ�������
		int[] chars=new int[2];//�洢ÿƪ���µ��ַ���
		
		//��ÿƪ���½��бȽ�׼��
		for(int i=0;i<2;i++) {
			//�ж��ļ��Ƿ��ȡ�ɹ�����ȡ���¶�����
			int pagesNum=isFind(file[i]);
			//��ÿ������������洢����
			String[] page=new String[pagesNum];
			page=getTxt(file[i],pagesNum,page);
			pages[i]=page;
			//�����仮��Ϊ��
			String[][] line=new String[pagesNum][];
			line=getLine(pages[i],line);
			lines[i]=line;
			//չʾ������Ϣ��ͳ���ַ�����
			chars[i]=showData(lines[i]);	
		}
		//��ʼ�Ա����£������ظ�����
		writeTxt("------���������ظ�����------","","",2);
		int cou=0;//��¼�ظ����ݵ����ַ���
		if(lines[0].length>lines[1].length) {
			for(int i=0;i<lines[0].length;i++) {
				for(int j=0;j<lines[1].length;j++) {
					cou+=compareTxt(pages[0][i],pages[1][j],lines[0][i],lines[1][j]);
				}
			}
		}else {
			for(int i=0;i<lines[1].length;i++) {
				for(int j=0;j<lines[0].length;j++) {
					cou+=compareTxt(pages[1][i],pages[0][j],lines[1][i],lines[0][j]);
				}
			}
		}
		//���ҽ����ͳ�ƴ���
		float rat;//���ڼ�¼������
		//�ж���ƪ���µ��ַ�������
		if(chars[0]<chars[1]) {
			rat=(float)cou/chars[0];
		}else {
			rat=(float)cou/chars[1];
		}
		//������
		writeTxt("------���յĲ��ؽ��------","","",2);
		String str="��������ͬ�ַ���Ϊ��"+rat;
		writeTxt(str,"","",2);
	}
	//�����д�����ļ�
	public static void writeTxt(String str1,String str2,String str3,int type) throws IOException{
		if(type==1) {
			try (BufferedWriter writer =
		          Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
		   writer.write(str1+str2+str3+"\r\n");
		  }
		}else {
			try (BufferedWriter writer =
			        Files.newBufferedWriter(path,
			            StandardCharsets.UTF_8,
			            StandardOpenOption.APPEND)){
			    writer.write(str1+str2+str3+"\r\n");
			  }
		}
		
	}
	
	//�ȶԺ���
	public static int compareTxt(String page1,String page2,String[] lines1,String[] lines2) throws IOException {
		int num=0;//��¼�ظ��ֶε��ַ���
		//�����ַ����Ա�
		if(page1.length()<=10||page2.length()<10) {
			return 0;
		}
		//���Ա�
		for(String line1:lines1) {
			for(String line2:lines2) {
				if(line1.indexOf(line2)!=-1) {
					writeTxt(line2,"","",2);
					num+=line2.length();
				}else if(line2.indexOf(line1)!=-1) {
					writeTxt(line1,"","",2);
					num+=line1.length();
				}
			}
		}
		
		return num;
	}
	//չʾÿƪ���µĴ�����Ϣ
	public static int showData(String[][] lines) throws IOException {
		int j=0;//������
		int k=0;//��
		int c=0;//�ַ���
		for(;j<lines.length;j++) {
			for(;k<lines[j].length;k++) {
				c+=lines[j][k].length();
			}
		}
		writeTxt("��",String.valueOf(j),"������", 2);
		writeTxt("��",String.valueOf(k),"������", 2);
		writeTxt("��",String.valueOf(c),"���ַ�", 2);
		return c;
		
	}
	//��û��������з־䴦����
	public static String[][] getLine(String[] page,String[][] lines) {
		
		int num=0;//��¼�þ���Ϊ�ö����еĵڼ���
		for(String p : page) {
			String[] temp= {};
			temp=p.split(",|\\.|\\?|��|��|��|��");
			lines[num]=temp;
			num++;
		}
		return lines;
	}
	//�����½��зֶδ�����
	public static String[] getTxt(File file,int num,String[]  pages) throws IOException {
		int i=0;
		String encoding = "utf-8";
		//�������ļ�����
		try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
	             BufferedReader bufferedReader = new BufferedReader(read)) {
			//���ڼ�¼ÿһ�е�����
	         String lineTxt;
	         while ((lineTxt = bufferedReader.readLine()) != null) {
	        	 pages[i]=lineTxt;
	             i++;
	         }   
	     }catch (Exception e) {
	    	 	writeTxt("��ȡ�ļ����ݳ���","","", 2);
	     }
		 return pages;
		
	}
	//�ж��ļ��Ƿ���ں���
	public static int isFind(File file) throws IOException {
		writeTxt("------��ȡ�ļ�",file.getName(),"������------", 2);
		int num=0;//���ڼ�¼��������
		String encoding = "utf-8";
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
             BufferedReader bufferedReader = new BufferedReader(read)) {
            //�ж��ļ��Ƿ����
            if (file.isFile() && file.exists()) {
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                	writeTxt(lineTxt,"","", 2);
                    num++;
                }
               int i=0;
               String[]  lines=new String[num];
               while ((lineTxt = bufferedReader.readLine()) != null) {
                    lines[i]=lineTxt;
                    i++;
                }
              
            } else {
            	writeTxt("�Ҳ���ָ�����ļ�","","", 2);
            }
        } catch (Exception e) {
        	writeTxt("��ȡ�ļ����ݳ���","","", 2);
        }
		return num;
	}

}
