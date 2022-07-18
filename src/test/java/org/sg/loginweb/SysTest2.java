package org.sg.loginweb;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("all")
public class SysTest2 {

	public static void main(String[] args) throws IOException {
        getDiskInfo();
        getMemoryInfo();
        String name=System.getProperty("os.name");
        System.out.println(name);
    }
    /**
     * 获取系统各个硬盘的总容量、已经使用的容量、剩余容量和使用率
     * @throws IOException
     */
    public static void getDiskInfo() throws IOException {
        DecimalFormat df = new DecimalFormat("#0.00");
        File[] disks = File.listRoots();
        for (File file : disks) {
            // 获取盘符
            System.out.print(file.getCanonicalPath() + "   ");
            // 获取总容量
            long totalSpace = file.getTotalSpace();
            // 获取剩余容量
            long usableSpace = file.getUsableSpace();
            // 获取已经使用的容量
            long freeSpace = totalSpace - usableSpace;
            // 获取使用率
            float useRate = (float)((freeSpace * 1.0 / totalSpace) * 100);
            System.out.print("总容量： " + transformation(totalSpace));
            System.out.print("已经使用： " + transformation(freeSpace));
            System.out.print("剩余容量： " + transformation(usableSpace));
            System.out.println("使用率： " + Double.parseDouble(df.format(useRate)) + "%   ");
        }
    }
    /**
     * 获取内存使用情况
     */
    public static void getMemoryInfo() {
        OperatingSystemMXBean mem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        // 获取内存总容量
        long totalMemorySize = mem.getTotalPhysicalMemorySize();
        // 获取可用内存容量
        long freeMemorySize = mem.getFreePhysicalMemorySize();
        System.out.println("内存总容量：" + transformation(totalMemorySize) );
        System.out.println("可用容量：" + transformation(freeMemorySize));
    }
    /**
     * 将字节容量转化为GB
     */
    public static String transformation(long size){
    	if(size / 1024 / 1024 / 1024>0) {
    		long gb = size / 1024 / 1024 / 1024;
    		if(size / 1024 / 1024 % 1024>0) {
    			String mb=String.valueOf((size / 1024 / 1024-gb*1024*1.0)/1024*1.0*100).split("\\.")[0];
    			return gb+"."+mb+"GB";
    		}else {
    			return gb+"GB";
    		}
    	} else {
    		return size / 1024 / 1024 + "MB";
    	}
    }

}
