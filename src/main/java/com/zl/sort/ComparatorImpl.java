package com.zl.sort;

import java.util.Comparator;

public class ComparatorImpl implements Comparator<News>{
	
	  public int compare(News o1, News o2) {
	        if(null!=o1 && null!=o2){
	            if(o1.getHits() >o2.getHits()){
	                return 1;
	            }else if(o1.getHits() ==o2.getHits()){
	                return 0;
	            }
	        }
	        return -1;
	  }
}
