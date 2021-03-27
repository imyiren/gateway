package com.example.gateway.core.route.definition;

import com.example.gateway.common.utils.UuidUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;

import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 路由构建器
 *
 * @author Yiren (<a href="mailto:lf253130@alibaba-inc.com">Send Email.<a/>)
 * @date 2021/3/27
 */
public class RouteDefinitionBuilder {
    private final RouteDefinition routeDefinition;

    public static final RouteDefinitionBuilder builder() {
        return new RouteDefinitionBuilder();
    }

    private RouteDefinitionBuilder() {
        this.routeDefinition = new RouteDefinition();
    }

    public RouteDefinitionBuilder id(String id) {
        StringUtils.defaultIfBlank(id, UuidUtils.generate());
        routeDefinition.setId(id);
        return this;
    }

    public RouteDefinitionBuilder addPredicate(String name, Supplier<Map<String, String>> paramsSupplier) {
        Objects.requireNonNull(name);
        PredicateDefinition predicateDefinition = new PredicateDefinition();
        predicateDefinition.setName(name);
        predicateDefinition.setArgs(paramsSupplier.get());
        routeDefinition.getPredicates().add(predicateDefinition);
        return this;
    }

    public RouteDefinitionBuilder addFilter(String name, Supplier<Map<String, String>> paramsSupplier) {
        Objects.requireNonNull(name);
        FilterDefinition filterDefinition = new FilterDefinition();
        filterDefinition.setName(name);
        filterDefinition.setArgs(paramsSupplier.get());
        routeDefinition.getFilters().add(filterDefinition);
        return this;
    }

    public RouteDefinitionBuilder uri(String uri) {
        try {
            routeDefinition.setUri(new URI(uri));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return this;
    }

    public RouteDefinitionBuilder metadata(Supplier<Map<String, Object>> metadataSupplier) {
        routeDefinition.setMetadata(metadataSupplier.get());
        return this;
    }

    public RouteDefinitionBuilder order(int order) {
        routeDefinition.setOrder(order);
        return this;
    }

    public RouteDefinition build() {
        return routeDefinition;
    }

}
