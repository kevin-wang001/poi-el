package com.kvn.poi.exp.function;

import com.google.common.collect.Lists;
import com.kvn.poi.exp.context.PoiExporterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

/**
 * 函数注册器
 * @author wzy
 * @date 2017年7月11日 下午7:57:23
 */
public class FunctionRegister {
	private static final Logger logger = LoggerFactory.getLogger(FunctionRegister.class);
	private static final String FUNCTION_DIRECTORY = "META-INF/poi-el/poi-el-functions";


	public static void registerInternalFunction(){
		List<Class> funClasses = getFunctionClasses();
		logger.info("|------------------------------PoiEl注册的内部函数--------------------------------|");
		for (Class funClass : funClasses) {
			doRegister(funClass);
		}
		logger.info("|--------------------------------------------------------------------------------|");
	}

	private static List<Class> getFunctionClasses() {
		List<Class> list = Lists.newArrayList();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		BufferedReader reader = null;
		URL url = null;
		try {
			Enumeration<URL> urls = classLoader.getResources(FUNCTION_DIRECTORY);
			while (urls.hasMoreElements()) {
				url = urls.nextElement();
				reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
				String line = null;
				while ((line = reader.readLine()) != null) {
					final int ci = line.indexOf('#'); // 支持#注释
					if (ci >= 0) line = line.substring(0, ci);
					line = line.trim();
					if (line.length() <= 0) {
						continue;
					}
					Class<?> clazz = Class.forName(line, true, classLoader);
					list.add(clazz);
				}
			}
		} catch (Throwable e) {
			logger.error("Exception when register function class in " + url, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	private static void doRegister(Class functionClass){
		Method[] methods = functionClass.getDeclaredMethods();
		for(Method m : methods){
			String funName = m.getName();
			logger.info("|-----------T(" + functionClass.getName() + ")." + funName + "------------------|");
			PoiExporterContext.EVAL_CONTEXT.registerFunction(funName, m);
		}
	}
}
