package com.mycompany.simpleapp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GreedySearch {

	public static Set<String> search(Set<String> all, Map<String, Set<String>> elements) {
		
		Set<String> nededElements = new HashSet<>(all);

		Map<String, Set<String>> mapOfElements = new HashMap<>(elements);

		Set<String> finalresult = new HashSet<>();
		String bestResult = mapOfElements.keySet().iterator().next();
		
		while (!nededElements.isEmpty()) {
			
			Set<String> bestResultNotCover = new HashSet<>(nededElements);
			bestResultNotCover.removeAll(mapOfElements.get(bestResult));
			
			for (Map.Entry<String, Set<String>> element : mapOfElements.entrySet()) {
				
				Set<String> currentElementNotCover = new HashSet<>(nededElements);	
				currentElementNotCover.removeAll(element.getValue());
				
				if (currentElementNotCover.size() < bestResultNotCover.size()) {
					
					bestResult = element.getKey();
					bestResultNotCover = new HashSet<>(nededElements);
					bestResultNotCover.removeAll(mapOfElements.get(bestResult));
				}
			}
			
			finalresult.add(bestResult);
			nededElements.removeAll(mapOfElements.get(bestResult));
		}
		return finalresult;
	}
}
