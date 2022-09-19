package papersCheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;



public interface PaperCheck {
	public static void main(String[] args) {
		File[] file=new File[2];
		file[0]=new File("D:\\schoolstudy\\软件工程\\papersCheck\\copy.txt");
		file[1]=new File("D:\\schoolstudy\\软件工程\\papersCheck\\papers.txt");
		System.out.println("------开始对比------");
		String[][] pages=new String[2][];//存储每一段的内容
		String[][][] lines=new String[2][][];//存储每一句的内容
		int[] chars=new int[2];//存储每篇文章的字符数
		for(int i=0;i<2;i++) {
			int pagesNum=isFind(file[i]);
			String[] page=new String[pagesNum];
			page=getTxt(file[i],pagesNum,page);
			pages[i]=page;
			String[][] line=new String[pagesNum][];
			line=getLine(pages[i],line);
			lines[i]=line;
			chars[i]=showData(lines[i]);	
		}
		System.out.println("------搜索到的重复内容------");
		int cou=0;
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
		float rat;
		if(chars[0]<chars[1]) {
			rat=(float)cou/chars[0];
		}else {
			rat=(float)cou/chars[1];
		}
		System.out.println("------最终的查重结果------");
		System.out.println("该文章相同字符比为："+rat);
	}
	
	public static int compareTxt(String page1,String page2,String[] lines1,String[] lines2) {
//		System.out.println("page1:"+page1);
//		System.out.println("page2:"+page2);
		String[] res= {};
		int num=0;
		if(page1.length()<=10||page2.length()<10) {
			return 0;
		}
		for(String line1:lines1) {
			for(String line2:lines2) {
				if(line1.indexOf(line2)!=-1) {
					System.out.println(line2);
//					res[num]=line2;
					num+=line2.length();
				}else if(line2.indexOf(line1)!=-1) {
					System.out.println(line1);
//					res[num]=line1;
					num+=line1.length();
				}
			}
		}
		return num;
//		int cou = 0;
//		for(String line:res) {
//			cou+=line.length();
//		}
//		float rat;
//		if(page1.length()<page2.length()) {
//			rat=(float)cou/page1.length();
//		}else {
//			rat=(float)cou/page2.length();
//		}
//		if(cou>10||rat>0.1) {
//			System.out.println("发现相同内容");
//			System.out.println(Arrays.toString(res));
//		}
	}
	public static int showData(String[][] lines) {
		int j=0;//段落数
		int k=0;//句
		int c=0;//字符数
	
			for(;j<lines.length;j++) {
				for(;k<lines[j].length;k++) {
					c+=lines[j][k].length();
				}
			}
			System.out.println("共"+j+"个段落");
			System.out.println("共"+k+"个句子");
			System.out.println("共"+c+"个字符");
			return c;
		
	}
	public static String[][] getLine(String[] page,String[][] lines) {
		int num=0;
		for(String p : page) {
			String[] temp= {};
			temp=p.split(",|\\.|\\?|，|。|？|！");
			
			lines[num]=temp;
			num++;
		}
//		System.out.println(Arrays.toString(lines));
		return lines;
	}
	public static String[] getTxt(File file,int num,String[]  pages) {
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
	            System.out.println("读取文件内容出错");
	     }
		 return pages;
		
	}
	
	public static int isFind(File file) {
		System.out.println("------读取文件"+file.getName()+"的内容------");
		int num=0;
		String encoding = "utf-8";
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
             BufferedReader bufferedReader = new BufferedReader(read)) {
            //判断文件是否存在
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
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
        }
		return num;
	}

}
