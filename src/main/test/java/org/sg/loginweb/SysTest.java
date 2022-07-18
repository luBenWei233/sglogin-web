package org.sg.loginweb;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import com.sun.management.OperatingSystemMXBean;

@SuppressWarnings("all")
public class SysTest {

	private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    public static void main(String[] args) throws Exception {

        /**
         * 物理内存 byte
         */
        long totalPhysicalMemorySize = osmxb.getTotalPhysicalMemorySize();
        System.out.println("物理内存:" + totalPhysicalMemorySize /(1024*1024));
        /**
         * 空闲物理内存 byte
         */
        long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();
        System.out.println("空闲物理内存:" + freePhysicalMemorySize /(1024*1024));
        /**
         * 
         */
        long committedVirtualMemorySize = osmxb.getCommittedVirtualMemorySize();
        System.out.println("getCommittedVirtualMemorySize:" + committedVirtualMemorySize /(1024*1024));
        /**
         *
         */
        String arch = osmxb.getArch();
        System.out.println("arch:" + arch);
        String name = osmxb.getName();
        System.out.println("name:" + name);
        /**
         *当前JVM占用的CPU负载
         */
        double processCpuLoad = osmxb.getProcessCpuLoad();
        System.out.println("getProcessCpuLoad:" + processCpuLoad);
        /**
         * cpu 使用率
         */
        while (true){
            System.out.println("cpu 使用率:" + osmxb.getSystemCpuLoad()*100);
            Thread.sleep(1000);
        }
    }
}
