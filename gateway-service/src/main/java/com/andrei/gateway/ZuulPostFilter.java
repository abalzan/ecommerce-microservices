package com.andrei.gateway;

import com.netflix.zuul.ZuulFilter;

public class ZuulPostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
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
    public Object run() {
        System.out.println("Using Post Filter");

        return null;
    }

}