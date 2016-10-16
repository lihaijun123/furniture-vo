package com.focustech.focus3d.agent.ibator.generate;

import com.focustech.focus3d.Focus3dGenerate;

/**
 * 代码生成
 *
 * 首先去ibatorconfig.xml文件里面修改需要生成的数据库表，然后运行此程序。
 * @author lihaijun
 *
 */
public class Generate {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		//String projectPath = System.getProperty("user.dir");
		String projectPath = "F:\\furniture_workspace\\furniture-vo";
		Focus3dGenerate.generate(projectPath);
	}
}
