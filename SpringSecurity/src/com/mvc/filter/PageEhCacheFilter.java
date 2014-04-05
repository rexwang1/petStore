package com.mvc.filter;

import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class PageEhCacheFilter extends SimplePageCachingFilter {

	private final static Logger log=Logger.getLogger(PageEhCacheFilter.class);
	private final static String FILTER_URL_PATTERNS="patterns";
	private static String[] cacheURLS;
	
	private void init() throws CacheException{
		String pattern=filterConfig.getInitParameter(FILTER_URL_PATTERNS);
		cacheURLS=StringUtils.split(pattern,",");
		
	} 
	
	@Override
	protected void doFilter(final HttpServletRequest request,
			final HttpServletResponse response,final FilterChain chain)
					throws AlreadyGzippedException,AlreadyCommittedException,FilterNonReentrantException,LockTimeoutException,Exception{
		if(cacheURLS==null){
			init();
		}
		String url=request.getRequestURI();
		boolean flag=false;
		
		if(cacheURLS!=null && cacheURLS.length>0){
			for(String cacheURL:cacheURLS){
				if(url.contains(cacheURL.trim())){
					flag=true;
					break;
				}
			}
		}
		String query=request.getQueryString();
		if(query!=null){
			query="?"+query;
			if(query.contains("update")){
				flag=false;
			}
		}
		//如果包含我们要缓存的url就缓存该页面，否则执行正常页面的转向
		if(flag){
			log.info("当前请求被缓存："+url+query);
			
			super.doFilter(request, response, chain);
		}else{
			chain.doFilter(request, response);
		}
	}
	
	private boolean headerContains(final HttpServletRequest request,final String header,final String value){
		logRequestHeaders(request);
		
		final Enumeration<String> accepted=request.getHeaders(header);
		while(accepted.hasMoreElements()){
			final String headerValue=accepted.nextElement();
			if(headerValue.indexOf(value)!=-1){
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected boolean acceptsGzipEncoding(HttpServletRequest request){
		boolean ie6=headerContains(request,"User-Agent","MSIE 6");
		boolean ie7=headerContains(request,"User-Agent","MSIE 7");
		
		return acceptsEncoding(request,"gzip") || ie6 ||ie7;
	}
}
