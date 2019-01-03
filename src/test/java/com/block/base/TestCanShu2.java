package com.block.base;

import java.util.ArrayList;
import java.util.List;

/*
 * 形参是引用类型的话 如果参数传给另一个对象作为属性 那么改对象对改属性的修改直接会影响到该实参
 * 如例子中 l1作为实参传给TestCanShu2的setList方法 那么TestCanShu2里的myList属性和l1实际上是公用同一个对象
 * 所以在调用TestCanShu2的change方法时 也会直接影响l1的值 最终遍历l1输出 123haha
 * 同样的 改变了l1 TestCanShu2里的myList属性也会改变
 * 
 * @author niezhenjie
 * 
 */
public class TestCanShu2 {
	private List<String> myList;

	public void setList(List<String> list) {
		myList = list;
	}

	public void changeList() {
		if (null == myList) {
			return;
		}
		myList.add("haha");
	}

	public static void main(String[] args) {
		List<String> l1 = new ArrayList<String>();
		l1.add("1");
		l1.add("2");
		l1.add("3");

		TestCanShu2 t = new TestCanShu2();
		t.setList(l1);
		t.changeList();
		for (int i = 0; i < l1.size(); i++) {
			System.out.println(l1.get(i));
		}
		l1.add("bilibili");
		for (int i = 0; i < t.myList.size(); i++) {
			System.out.println(t.myList.get(i));
		}
	}
}
