package bms;

import java.util.ArrayList;

public class numbers {
	private ArrayList<String> numbers;
	public numbers() {
		numbers = new ArrayList<String>();
	}
	public void Add(String number)
	{
		numbers.add(number);
	}
	public String getNumberI(int i)
	{
		return numbers.get(i);
	}
}
