package com.example.gateway.core.context;

import com.example.gateway.common.utils.UuidUtils;
import com.example.gateway.core.route.definition.RouteDefinitionBuilder;
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
        RouteDefinition definition = RouteDefinitionBuilder.builder().id(UuidUtils.generate())
                .uri("lb://demo-rest")
                .addPredicate("Path", () -> {
                    Map<String, String> params = new HashMap<>(8);
                    params.put("pattern", "/cc/cc");
                    return params;
                })
                .addFilter("RewritePath", () -> {
                    Map<String, String> params = new HashMap<>();
                    params.put("regexp", "/cc/cc");
                    params.put("replacement", "/test");
                    return params;
                }).build();
        save(definition);
    }


    public static Collection<RouteDefinition> getRouteDefinitions() {
        log.info("route loading: ROUTE_DEFINITION_MAP: {}", ROUTE_DEFINITION_MAP);
        return ROUTE_DEFINITION_MAP.values();
    }

    public static void save(RouteDefinition routeDefinition) {
        ROUTE_DEFINITION_MAP.put(routeDefinition.getId(), routeDefinition);
    }

    public static void remove(String id) {
        ROUTE_DEFINITION_MAP.remove(id);
    }
}
