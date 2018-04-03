package com.nebo_spider.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDGenerator {  
	/** 自定义进制(0,1没有加入,容易与o,l混淆) */
	private static final char[] r = new char[] { 'Q', 'w', 'E', '8', 'a', 'S',
			'2', 'd', 'Z', 'x', '9', 'c', '7', 'p', 'O', '5', 'i', 'K', '3',
			'm', 'j', 'U', 'f', 'r', '4', 'V', 'y', 'L', 't', 'N', '6', 'b',
			'g', 'H' };
	/** 自动补全组(不能与自定义进制有重复) */
	private static final char[] b = new char[] { 'q', 'W', 'e', 'A', 's', 'D',
			'z', 'X', 'C', 'P', 'o', 'I', 'k', 'M', 'J', 'u', 'F', 'R', 'v',
			'Y', 'T', 'n', 'B', 'G', 'h' };
	/** 进制长度 */
	private static final int l = r.length;
	/** 序列最小长度 */
	private static final int s = 8;

	/**
	 * 根据ID生成六位随机码
	 * 
	 *            ID
	 * @return 随机码
	 */
	public static String toSerialNumber() {
		//随机生成r数组字符的位置
		long num = (long) ((Math.random())*34);
		char[] buf = new char[32];
		int charPos = 32;

		while ((num / l) > 0) {
			buf[--charPos] = r[(int) (num % l)];
			num /= l;
		}
		buf[--charPos] = r[(int) (num % l)];
		
		String str = new String(buf, charPos, (32 - charPos));
		// 不够长度的自动随机补全
		if (str.length() < s) {
			StringBuffer sb = new StringBuffer();
			Random rnd = new Random();
			for (int i = 0; i < s - str.length(); i++) {
				sb.append(b[rnd.nextInt(24)]);
			}
			str += sb.toString();
		}
		return str;
	}
    /** 
     * 这是典型的随机洗牌算法。 
     * 流程是从备选数组中选择一个放入目标数组中，将选取的数组从备选数组移除（放至最后，并缩小选择区域） 
     * 算法时间复杂度O(n) 
     * @return 随机8为不重复数组 
     */  
    public static String generateNum(int len) {  
        String no="";  
        //初始化备选数组  
        int[] defaultNums = new int[10];  
        for (int i = 0; i < defaultNums.length; i++) {  
            defaultNums[i] = i;  
        }  
  
        Random random = new Random();  
        int[] nums = new int[len];  
        //默认数组中可以选择的部分长度  
        int canBeUsed = 10;  
        //填充目标数组  
        for (int i = 0; i < nums.length; i++) {  
            //将随机选取的数字存入目标数组  
            int index = random.nextInt(canBeUsed);  
            nums[i] = defaultNums[index];  
            //将已用过的数字扔到备选数组最后，并减小可选区域  
            swap(index, canBeUsed - 1, defaultNums);  
            canBeUsed--;  
        }  
        if (nums.length>0) {  
            for (int i = 0; i < nums.length; i++) {  
                no+=nums[i];  
            }  
        }  
  
        return no;  
    }    
  
    private static void swap(int i, int j, int[] nums) {  
        int temp = nums[i];  
        nums[i] = nums[j];  
        nums[j] = temp;  
    }  

    /**
     * yMM + 6位数字，共9位
     * @return
     */
    public static String generateOrderID(){
    	String id = generateNum(6);
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
    	String date=dateFormat.format(new Date());
    	String[] timeStr=date.split("-");
    	char year = timeStr[0].charAt(3);
    	String month = timeStr[1];
    	String orderID = year+month+id;
    	return orderID;
    }
    
    // 返回正整数
 	public static int getInt(String str) {
 		int num = 0;
 		if(str!=null){
 			String regex = "^[1-9]\\d*$";// 校验正整数
 	 		Pattern p = Pattern.compile(regex);
 	 		Matcher m = p.matcher(str);
 	 		boolean b = m.matches();
 	 		if (b == true) {
 	 			num = Integer.parseInt(str);
 	 		}
 		}		
 		return num;
 	}
    
    public static void main(String[] args) {  
        for (int i = 0; i < 100; i++) {   
            //System.out.println(generateOrderID());  
        }  
    }  
}  
