package papersCheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;



public interface PaperCheck {
	public static void main(String[] args) {
		File[] file=new File[2];
		file[0]=new File("D:\\schoolstudy\\�������\\papersCheck\\copy.txt");
		file[1]=new File("D:\\schoolstudy\\�������\\papersCheck\\papers.txt");
		System.out.println("------��ʼ�Ա�------");
		String[][] pages=new String[2][];
		for(int i=0;i<2;i++) {
			int pagesNum=isFind(file[i]);
			String[] page=new String[pagesNum];
			page=readTxt(file[i],pagesNum,page);
			pages[i]=page;
		}
		
	}
	public static String[] readTxt(File file,int num,String[]  pages) {
		int i=0;
		String encoding = "utf-8";
		try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
	             BufferedReader bufferedReader = new BufferedReader(read)) {
	         String lineTxt;
	         while ((lineTxt = bufferedReader.readLine()) != null) {
	        	 pages[i]=lineTxt;
	             i++;
	         }   
	     }catch (Exception e) {
	            System.out.println("��ȡ�ļ����ݳ���");
	     }
		 return pages;
		
	}
	
	public static int isFind(File file) {
		System.out.println("------��ȡ�ļ�"+file.getName()+"������------");
		int num=0;
		String encoding = "utf-8";
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
             BufferedReader bufferedReader = new BufferedReader(read)) {
            //�ж��ļ��Ƿ����
            if (file.isFile() && file.exists()) {
                String lineTxt;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                    num++;
                }
               int i=0;
               String[]  lines=new String[num];
               while ((lineTxt = bufferedReader.readLine()) != null) {
                    lines[i]=lineTxt;
                    i++;
                }
              
            } else {
                System.out.println("�Ҳ���ָ�����ļ�");
            }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ����ݳ���");
        }
		return num;
	}

}
