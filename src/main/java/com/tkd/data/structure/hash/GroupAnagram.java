package com.tkd.data.structure.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagram {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = Arrays.asList(strs).stream().collect(Collectors.groupingBy(t -> {
			char[] charArray = t.toCharArray();
			Arrays.sort(charArray);
			return new String(charArray);
		}));
		return new ArrayList<>(map.values());
	}
}
