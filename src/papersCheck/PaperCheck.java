package papersCheck;

import java.io.File;

public interface PaperCheck {
	public static void main(String[] args) {
		File[] file=new File[2];
		file[0]=new File("D:\\schoolstudy\\软件工程\\papersCheck\\copy.txt");
		file[1]=new File("D:\\schoolstudy\\软件工程\\papersCheck\\papers.txt");
		System.out.println("------开始对比------");
	}
	
}
