package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClapIt {	
	public static void main(String[] args) {
		int x = 0;
		int y = 0;
		if ( ((x = gets("x", 10, 300)) > 0) && ((y = gets("y", 1, 9)) > 0) ) {
			clapExecute(x, y);
		} else {
			System.out.println("try again(input is invalid).");
		}
	}
	public static void clapExecute(int x, int y) {
		List<String> result = Stream.iterate(1, i -> i + 1)
				.limit(x)
				.map(v -> clap(v, x, y))
				.collect(Collectors.toList());
		
		result.stream()
				.forEach(System.out::println);
		Integer clapCount = (int) result.stream()
				.filter(s -> s == "CLAP")
				.count();
		Integer dbclapCount = (int) result.stream()
				.filter(s -> s == "DOUBLE CLAP")
				.count();
		System.out.println("CLAP:" + clapCount.toString() + " DOUBLE CLAP:" + dbclapCount.toString());
	}
	
	private static String clap(int current, int max, int base) {
		String currentStr = ((Integer)current).toString();
		boolean isMulti = false;
		boolean isContain = false;
		if ((current % base) == 0)	isMulti = true;
		if (currentStr.contains(((Integer)base).toString())) isContain = true;
		
		if (isMulti && isContain)	return "DOUBLE CLAP";
		if (isMulti)				return "CLAP";
		if (isContain)				return "CLAP";
		return currentStr;
	}

	private static int gets(String target, int min, int max){
		int num = 0;
		try {
			System.out.println("input " + target + ":");
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String str = br.readLine();
			num = Integer.parseInt(str, 10);
		} catch (Exception e) {}
		
		if (min <= num && num <= max) {
			return num;
		}
		return 0;
	}
}
