package com.readmain.task.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by yuehao on 2017/9/17.
 */
public class ContainerThread {

    //线程安全的 --- -- -- - - -- - -- -- - -- -  list容器

    //适合多读少写的环境使用，读无锁，并发高
    private static List<Integer> integers = new CopyOnWriteArrayList<>();

    //适合写多读少环境使用，因为读也是加了锁滴
    private static List<Integer> integerList = Collections.synchronizedList(new ArrayList<>());

    //适合写多读少环境使用
    private static List<String> strings = new Vector<>();

    //线程安全的 -- - - - - - --- - - - - - - - -- -map容器

    //性能一般的map，读写都加了互斥锁
    private static Map<String,Integer> stringIntegerMap =  Collections.synchronizedMap(new HashMap<String,Integer>());

    //性能最高的map没有之一，读无锁，写用的分段锁，性能最高的map
    private static Map<String,Integer> getStringIntegerMap = new ConcurrentHashMap<>();

    //性能一般的线程安全map,读写互斥锁
    private static Map<String,Integer> hashTable = new Hashtable<>();

    //线程安全的 -- --- -- - -- - - -- - - - - - -- --set

    //性能还不错的线程安全的set,读无锁，写有锁
    private static Set<Integer> integerSet = new CopyOnWriteArraySet<>();

    //性能一般，读写互斥锁
    private static Set<String> stringSet = Collections.synchronizedSet(new HashSet<>());
}
