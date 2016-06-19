package fptree;

import java.util.HashMap;
import java.util.Map;

public class TagToInteger {
	 /** 所有的标签对数字的映射，这可以在处理时加快速度也节省了空间. */
	private Map<Integer, String> integertotag = new HashMap<Integer, String>();
	/** 所有的数字对标签的映射，这可以在处理时加快速度也节省了空间. */
	private Map<String, Integer> tagtointeger = new HashMap<String, Integer>();
	
	
	/**
	   * 把自己定义的数字与标签加入到映射中
	   * 
	   * @param 标签
	   * @param 与标签所对应的数字
	   * @return 成功返回ture,否则false
	   */
	public boolean addIntegerToTagMap(int tagno, String tag) {
		if(integertotag.containsKey(tagno)) {
			System.out.println("tag 重复出现请确保映射的唯一性");
			return false;
		} else {
			this.integertotag.put(tagno, tag);
			this.tagtointeger.put(tag, tagno);
			return true;
		}	
	}
	
	
	/**
	   * 清除所有映射
	   * 
	   * @param void
	   * @return void
	   */
	public void clearMap() {
		this.integertotag.clear();
		this.tagtointeger.clear();
	}
	/**
	   * 检测是否存在
	   * 
	   * @param String
	   * @return void
	   */
	public boolean contains(String strtag) {
		if(this.tagtointeger.containsKey(strtag)) {
			return true;
		} return false;
	}
	
	/**
	   * 给标签返回数字
	   * 
	   * @param 标签
	   * @return 与标签所对应的数字
	   */
	public int getTagDigit(String tag) {
		return this.tagtointeger.get(tag).intValue();
	}
	
	
	/**
	   * 给数字返回标签
	   * 
	   * @param 数字
	   * @return 与数字所对应的标签
	   */
	public String getDigitTag(int tagno) {
		return this.integertotag.get(tagno);
	}
}
