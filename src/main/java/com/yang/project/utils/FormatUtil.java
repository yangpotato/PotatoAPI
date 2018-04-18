package com.yang.project.utils;

public class FormatUtil {
	/**String转Integer
	 */
	public static Integer toInteger(String s){
		Integer i=null;
		if(s!=null){
			i=Integer.valueOf(s);
		}
		return i;
	}

}
