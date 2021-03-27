package com.example.gateway.core.context;

import com.google.common.collect.Lists;
import com.sun.jndi.toolkit.url.Uri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 路由上下文
 *
 * @author Yiren (<a href="mailto:yiren.dev@gmail.com">Send Email.<a/>)
 * @date 2021/3/22
 */
@Slf4j
@Component
public class RouteContext {
    private static final Map<String, RouteDefinition> ROUTE_DEFINITION_MAP = new ConcurrentHashMap<>(1 << 16);

    static {
        RouteDefinition routeDefinition = new RouteDefinition();
        routeDefinition.setId(UUID.randomUUID().toString());
        try {
            routeDefinition.setUri(new URI("lb://demo-rest"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        //定义一个断言
        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName("Path");
        Map<String, String> predicateParams = new HashMap<>(8);
        predicateParams.put("pattern", "/cc/cc");
        predicate.setArgs(predicateParams);
        routeDefinition.setPredicates(Lists.newArrayList(predicate));
        // 设置过滤器
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName("RewritePath");
        Map<String, String> filterParams = new HashMap<>();
        filterParams.put("regexp", "/cc/cc");
        filterParams.put("replacement", "/test");
        filterDefinition.setArgs(filterParams);
        routeDefinition.setFilters(Lists.newArrayList(filterDefinition));

        ROUTE_DEFINITION_MAP.put(routeDefinition.getId(), routeDefinition);

    }


    public Collection<RouteDefinition> getRouteDefinitions() {
        log.info("route loading: ROUTE_DEFINITION_MAP: {}", ROUTE_DEFINITION_MAP);
        return ROUTE_DEFINITION_MAP.values();
    }

    public void save(String id, RouteDefinition routeDefinition) {
        ROUTE_DEFINITION_MAP.put(id, routeDefinition);
    }

    public void remove(String id) {
        ROUTE_DEFINITION_MAP.remove(id);
    }
}
