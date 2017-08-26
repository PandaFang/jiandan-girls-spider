package com.github.pandafang.jiandan_girls_spider;

/**
 * App Entry
 * @author panda fang
 * @date 2017-08-24
 * @version 1.0
 */
public class App 
{
    public static void main( String[] args )
    {
    	Spider spider = new Spider("/home/panda/jiandan-mm/");
    	for(int i = 1; i <= 280; i++) {
    		spider.run(i);
    	}
    	
    }

}
