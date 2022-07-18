package org.sg.loginweb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * 自动创建Mybatis-plus的实体类
 * @author 刘丰
 * @data 2021-05-23 16:12
 */
public class AutoCreateClass {
	
	private static final String author="帅哥";

	public static void main(String[] args) throws Exception {
		
		String databasse="sglogin";
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://192.168.1.8:3306/"+databasse+"?characterEncoding=UTF-8&testConnectionOnCheckout=true&autoReconnect=true&tcpRcvBuf=10240000&serverTimezone=Hongkong&useSSL=false";
		String user="root";
		String password="708474C.c";
		Connection conn=DriverManager.getConnection(url,user,password);
		//生成实体类
		Scanner input=new Scanner(System.in);
		System.out.println("请输入表名：");
		String tableName=input.next();
		System.out.println("请输入要生成的实体类名称：");
		String EntityName=input.next();
		
		//要生成实体类的位置
		String classLocation="org.sg.loginweb.entity.sys";
		createEntity(classLocation, conn,tableName,EntityName,databasse);
		input.close();
		
		//生成Dao
		String daoLocation="org.sg.loginweb.mapper.sys";
		createDao(daoLocation,classLocation,EntityName);
		
		//生成Service
		String serviceLocation="org.sg.loginweb.service.sys";
		createService(serviceLocation,classLocation,EntityName);
		
		//生成ServiceImpl
		String serviceImplLoc="org.sg.loginweb.service.impl.sys";
		createServiceImpl(serviceImplLoc,serviceLocation,daoLocation,classLocation,EntityName);
		
		//生成Controller
		String controllerLoc="org.sg.loginweb.controller.sys";
		createController(controllerLoc,serviceLocation,EntityName);
		
		//生成MapperXml
		String xmlLoc="mybatis.mapper";
		createMapperXml(xmlLoc,daoLocation,classLocation,conn,tableName,EntityName,databasse);
		
		closeAll(conn,null,null);
		
	}
	
	private static void closeAll(Connection c,Statement s,ResultSet r) {
		try {
			if(c!=null) {c.close();}
			if(s!=null) {s.close();}
			if(r!=null) {r.close();}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SafeVarargs
	private static void writeToText(String path,List<String>... list) throws IOException {
        // 生成的文件路径
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        // write 解决中文乱码问题
        // FileWriter fw = new FileWriter(file, true);
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        BufferedWriter bw = new BufferedWriter(fw);
        for(List<String> array:list) {
        	for(String item:array) {
            	bw.write(item);
            	bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        fw.close();

    }
	
	private static String getType(String type) {
		if(type.equals("int")) {
			return "Integer";
		}else if(type.equals("bigint")) {
			return "Long";
		}else if(type.contains("char")) {
			return "String";
		}else if(type.contains("decimal")) {
			return "BigDecimal";
		}else if(type.contains("date")) {
			return "Date";
		}else {
			return "Object";
		}
	}
	
	private static void getAddTop(List<String> list,String type) {
		String value=null;
		if(type.contains("decimal")) {
			value="import java.math.BigDecimal;";
		}else if(type.contains("date")) {
			value="import java.util.Date;";
		}
		if(value!=null) {
			boolean flag=true;
			for(String item:list) {
				if(item.equals(value)) {
					flag=false;
					break;
				}
			}
			if(flag) {
				list.add(value);
			}
		}
	}
	
	//首字母大写
	private static String captureName(String name) {
		char[] cs=name.toCharArray();
		cs[0]-=32;
		return String.valueOf(cs);
	}
	//首字母小写
	private static String toLow(String name) {
       name = name.substring(0, 1).toLowerCase() + name.substring(1);//UpperCase大写
       return  name;
    }
	//驼峰式转换
    private static String tuoFeng(String name) {
    	for(int i=0;i<name.length();i++) {
    		if("_".equals(name.substring(i, i+1))) {
    			name = name.substring(0, i) + name.substring(i+1, i+2).toUpperCase()+name.substring(i+2,name.length());
    		}
    	}
    	return name;
    }
    
	
	private static String createNowTime() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	private static void createEntity(String classLocation,Connection conn,String tableName,String EntityName,String database) throws Exception {
		
		String columns="select distinct column_name,column_comment, column_type,column_key from information_schema.columns where table_schema = '"+database+"' and table_name = '"+tableName+"'";
		PreparedStatement pst=conn.prepareStatement(columns);
		ResultSet rs=pst.executeQuery();
		List<String> top=new ArrayList<>();
		top.add("package "+classLocation+";");
		top.add("");
		top.add("import java.io.Serializable;");
		top.add("import com.baomidou.mybatisplus.annotation.IdType;");
		top.add("import com.baomidou.mybatisplus.annotation.TableField;");
		top.add("import com.baomidou.mybatisplus.annotation.TableId;");
		top.add("import com.baomidou.mybatisplus.annotation.TableName;");
		List<String> middle=new ArrayList<>();
		middle.add("");
		middle.add("/**");
		middle.add("* "+tableName+"表实体类");
		middle.add("* @author "+author);
		middle.add("* @data " + createNowTime());
		middle.add(" */");
		middle.add("@TableName(\""+tableName+"\")");
		middle.add("public class "+EntityName+" implements Serializable {");
		middle.add("");
		middle.add("private static final long serialVersionUID = 1L;");
		middle.add("");
		List<String> bottom=new ArrayList<>();
		while (rs.next()) {
			getAddTop(top,rs.getString("column_type"));
			middle.add("	// "+rs.getString("column_comment"));
			if(rs.getString("column_key").equals("PRI")) {
				middle.add("	@TableId(value=\""+rs.getString("column_name")+"\",type=IdType.AUTO)");
			}else {
				middle.add("	@TableField(value = \""+rs.getString("column_name")+"\")");
			}
			middle.add("	private" + " " + getType(rs.getString("column_type"))+" "+tuoFeng(rs.getString("column_name"))+";");
			bottom.add("");
			bottom.add("	public " +getType(rs.getString("column_type"))+ " get"+captureName(tuoFeng(rs.getString("column_name")))+"() {");
			bottom.add("		return "+tuoFeng(rs.getString("column_name"))+";");
			bottom.add("	}");
			bottom.add("	public void set"+captureName(tuoFeng(rs.getString("column_name")))+"("+getType(rs.getString("column_type"))+" "+ tuoFeng(rs.getString("column_name")) +") {");
			bottom.add("		this."+tuoFeng(rs.getString("column_name"))+"="+ tuoFeng(rs.getString("column_name")) +";");
			bottom.add("	}");
		}
		bottom.add("}");
		writeToText(System.getProperty("user.dir")+"/src/main/java/"+classLocation.replace(".", "//")+"/"+EntityName+".java", top,middle,bottom);
		closeAll(null,pst,rs);
		System.out.println("你的实体类生成成功了！！！");
	}
	
	//生成Dao层
	private static void createDao(String daoLocation,String entityLocation,String EntityName) throws IOException {
		List<String> top=new ArrayList<>();
		top.add("package "+daoLocation+";");
		top.add("");
		top.add("import org.apache.ibatis.annotations.Mapper;");
		top.add("import com.baomidou.mybatisplus.core.mapper.BaseMapper;");
		top.add("import "+entityLocation+"."+EntityName+";");
		top.add("");
		top.add("/**");
		top.add("* "+EntityName+"数据访问");
		top.add("* @author "+author);
		top.add("* @data " + createNowTime());
		top.add(" */");
		top.add("");
		top.add("@Mapper");
		top.add("public interface "+EntityName+"Mapper extends BaseMapper<"+EntityName+">{");
		top.add("");
		top.add("}");
		writeToText(System.getProperty("user.dir")+"/src/main/java/"+daoLocation.replace(".", "//")+"/"+EntityName+"Mapper.java", top);
		System.out.println("数据访问层生成成功了！！！");
	}
	//生成Service层
	private static void createService(String serviceLocation,String entityLocation,String EntityName) throws IOException {
		List<String> top=new ArrayList<>();
		top.add("package "+serviceLocation+";");
		top.add("");
		top.add("import com.baomidou.mybatisplus.extension.service.IService;");
		top.add("import "+entityLocation+"."+EntityName+";");
		top.add("");
		top.add("/**");
		top.add("* "+EntityName+"业务逻辑接口");
		top.add("* @author "+author);
		top.add("* @data " + createNowTime());
		top.add(" */");
		top.add("");
		top.add("public interface "+EntityName+"Service extends IService<"+EntityName+">{");
		top.add("");
		top.add("}");
		writeToText(System.getProperty("user.dir")+"/src/main/java/"+serviceLocation.replace(".", "//")+"/"+EntityName+"Service.java", top);
		System.out.println("业务逻辑接口层生成成功了！！！");
	}
	
	//生成ServiceImpl实现层
	private static void createServiceImpl(String serviceImplLoc,String serviceLocation,String daoLocation,
			String entityLocation,String EntityName) throws IOException {
		List<String> top=new ArrayList<>();
		top.add("package "+serviceImplLoc+";");
		top.add("");
		top.add("import org.springframework.beans.factory.annotation.Autowired;");
		top.add("import org.springframework.stereotype.Service;");
		top.add("import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;");
		top.add("import "+entityLocation+"."+EntityName+";");
		top.add("import "+daoLocation+"."+EntityName+"Mapper;");
		top.add("import "+serviceLocation+"."+EntityName+"Service;");
		top.add("");
		top.add("/**");
		top.add("* "+EntityName+"业务逻辑接口");
		top.add("* @author "+author);
		top.add("* @data " + createNowTime());
		top.add(" */");
		top.add("");
		top.add("@Service");
		top.add("public class "+EntityName+"ServiceImpl extends ServiceImpl<"+EntityName+"Mapper,"+EntityName+"> implements "+EntityName+"Service{");
		top.add("");
		top.add("	@Autowired");
		top.add("	private "+EntityName+"Mapper "+toLow(EntityName)+"Mapper;");
		top.add("");
		top.add("}");
		writeToText(System.getProperty("user.dir")+"/src/main/java/"+serviceImplLoc.replace(".", "//")+"/"+EntityName+"ServiceImpl.java", top);
		System.out.println("业务逻辑实现层生成成功了！！！");
	}
	
	//Controller生成
	private static void createController(String controllerLoc,String serviceLocation,String EntityName) throws IOException {
		List<String> top=new ArrayList<>();
		top.add("package "+controllerLoc+";");
		top.add("");
		top.add("import org.springframework.beans.factory.annotation.Autowired;");
		top.add("import org.springframework.stereotype.Controller;");
		top.add("import org.springframework.web.bind.annotation.RequestMapping;");
		top.add("");
		top.add("import "+serviceLocation+"."+EntityName+"Service;");
		top.add("");
		top.add("/**");
		top.add("* "+EntityName+"控制器层");
		top.add("* @author "+author);
		top.add("* @data " + createNowTime());
		top.add(" */");
		top.add("");
		top.add("@Controller");
		top.add("@RequestMapping(\"/"+EntityName+"\")");
		top.add("public class "+EntityName+"Controller {");
		top.add("");
		top.add("	@Autowired");
		top.add("	private "+EntityName+"Service "+toLow(EntityName)+"Service;");
		top.add("");
		top.add("}");
		writeToText(System.getProperty("user.dir")+"/src/main/java/"+controllerLoc.replace(".", "//")+"/"+EntityName+"Controller.java", top);
		System.out.println("控制器层生成成功了！！！");
	}
	
	//mapperXml生成
	private static void createMapperXml(String xmlLoc,String daoLocation,String classLocation,Connection conn,String tableName,String EntityName,String database) throws Exception {
		String columns="select distinct column_name,column_comment, column_type,column_key from information_schema.columns where table_schema = '"+database+"' and table_name = '"+tableName+"'";
		PreparedStatement pst=conn.prepareStatement(columns);
		ResultSet rs=pst.executeQuery();
		List<String> top=new ArrayList<>();
		top.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		top.add("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
		top.add("<mapper namespace=\""+daoLocation+"."+EntityName+"Mapper\">");
		List<String> middle=new ArrayList<>();
		middle.add("");
		middle.add("	<resultMap id=\"BaseResultMap\" type=\""+classLocation+"."+EntityName+"\">");
		boolean flag=true;
		String sql="";
		while (rs.next()) {
			if(flag) {
				middle.add("		<id column=\""+rs.getString("column_name")+"\" property=\""+tuoFeng(rs.getString("column_name"))+"\"/>");
			}else {
				middle.add("		<result column=\""+rs.getString("column_name")+"\" property=\""+tuoFeng(rs.getString("column_name"))+"\"/>");
			}
			sql=sql+rs.getString("column_name")+",";
		}
		middle.add("	</resultMap>");
		List<String> bottom=new ArrayList<>();
		bottom.add("");
		bottom.add("	<sql id=\"Base_column_List\">");
		bottom.add("		"+sql.substring(0,sql.length() - 1));
		bottom.add("	</sql>");
		bottom.add("");
		bottom.add("");
		bottom.add("</mapper>");
		writeToText(System.getProperty("user.dir")+"/src/main/resources/"+xmlLoc.replace(".", "//")+"/"+EntityName+"Mapper.xml", top,middle,bottom);
		closeAll(null,pst,rs);
		System.out.println("MapperXml生成成功了！！！");
	}
}
