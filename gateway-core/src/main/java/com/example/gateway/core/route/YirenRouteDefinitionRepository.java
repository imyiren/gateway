package com.example.gateway.core.route;

import com.example.gateway.core.context.RouteContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * Redis中获取路由信息
 *
 * @author Yiren (<a href="mailto:yiren.dev@gmail.com">Send Email.<a/>)
 * @date 2021/3/20
 */
@Slf4j
@Component
public class YirenRouteDefinitionRepository implements RouteDefinitionRepository {
    @Resource
    private RouteContext routeContext;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeContext.getRouteDefinitions());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.handle((routeDefinition, voidSynchronousSink) -> {
                    routeContext.save(routeDefinition.getId(), routeDefinition);
                    voidSynchronousSink.complete();
                }
        );
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.handle((id, voidSyncSink) -> {
            routeContext.remove(id);
            voidSyncSink.complete();
        });
    }
}
