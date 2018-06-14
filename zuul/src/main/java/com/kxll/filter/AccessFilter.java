package com.kxll.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

@Component
public class AccessFilter extends ZuulFilter {
    Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run()  {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Object token = request.getParameter("token");

        //校验token
        if (token == null) {
            logger.info("token为空，禁止访问!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            JSONObject jo=new JSONObject();
            jo.put("code",HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            jo.put("msg","无权限访问");
            ctx.setResponseBody(new String(jo.toJSONString().getBytes(Charset.forName("UTF-8"))));
            return null;
        } else {
            //TODO 根据token获取相应的登录信息，进行校验（略）
            ctx.setSendZuulResponse(true);
            ctx.setResponseStatusCode(HttpStatus.OK.value());
            ctx.set("isSuccess", true);// 设值，可以在多个过滤器时使用
        }

        //添加Basic Auth认证信息
        ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("app01", "*****"));

        return null;
    }
    private String getBase64Credentials(String username, String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }
}
