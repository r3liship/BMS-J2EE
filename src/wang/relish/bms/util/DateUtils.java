package wang.relish.bms.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("all")
public class DateUtils {
	
	/**
	 * 将日期转化成“yyyy-MM-dd”格式
	 * @param date 日期（如：new date）
	 * @return string类型数据(yyyy-MM-dd)
	 */
	public static String DateToYymd(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * 将日期转化成“yyyy年MM月dd日 HH时mm分ss秒”格式
	 * @param date 日期（如：new date）
	 * @return string类型数据(yyyy年MM月dd日 HH时mm分ss秒)
	 */
	public static String DateToyMdHms(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		String formatDate = sdf.format(date);
		return formatDate;
	}
	
	/**
	 * 获取当前时间的时间戳，返回的是毫秒数。
	 * 推荐此种方法，执行速度快。
	 * @return 毫秒数
	 */
	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 将日期转换成时间戳格式
	 * @param date 日期（如：new date）
	 * @return 毫秒数
	 */
	public static long getDateTimeMillis(Date date){
		return date.getTime();
	}
	
	/**
	 * 将long类型的时间格式转化成数据库的时间戳格式
	 * @param timeLong 毫秒级的时间数据
	 * @return 数据库的时间戳格式(yyyy-MM-dd HH:mm:ss.SSS)
	 */
	public static Timestamp longToTimestamp(long timeLong){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = new Date(timeLong);
		String dataStr = formatter.format(date);
		Timestamp timestamp = Timestamp.valueOf(dataStr);
		
		return timestamp;
	}
	
	/**
	 * 获取当前天时间的long
	 * @return
	 */
	public static long longTimeByYyMMdd(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis());
		String dataStr = formatter.format(date);
		
		long dateLong = 0;
		try {
			Date newDate = formatter.parse(dataStr);
			dateLong = newDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return dateLong;
	}
	
	/**
	 * 将long类型的时间格式转化成时间格式
	 * @param timeLong 毫秒级的时间数据
	 * @return 格式(yyyy-MM-dd HH:mm:ss)
	 */
	public static String longToTimeFormat(long timeLong){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(timeLong > 0){
			Date date = new Date(timeLong);
			String dataStr = formatter.format(date);
			
			return dataStr;
		}else{
			return "";
		}
	}
	
	/**
	 * 将long类型的时间格式转化成时间格式
	 * @param timeLong 毫秒级的时间数据
	 * @return 格式(yyyy-MM-dd HH:mm)
	 */
	public static String longToTimeFormatM(long timeLong){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		if(timeLong > 0){
			Date date = new Date(timeLong);
			String dataStr = formatter.format(date);
			
			return dataStr;
		}else{
			return "";
		}
	}
	
	/**
	 * 将日期时间格式转化成long
	 * @param timeStr  yyyy-MM-dd HH:mm:ss格式的日期
	 * @return 成功：返回long值的毫秒数，失败：返回-1
	 */
	public static long timeStrToLong(String timeStr){
		if(TextUtils.isNotEmpty(timeStr)){
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = sdf.parse(timeStr);
			} catch (ParseException e) {
				e.printStackTrace();
				return -1;
			}
			long timeL = date.getTime();
			return timeL;
		}else{
			return -1;
		}
	}
	
}
