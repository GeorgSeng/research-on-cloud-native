package org.acme.microprofile.metrics;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.quarkus.micrometer.runtime.MeterFilterConstraint;
import org.eclipse.microprofile.config.inject.ConfigProperty;


import javax.inject.Singleton;
import javax.ws.rs.Produces;
import java.util.Arrays;

//@Singleton
//public class CustomConfiguration {
//
//    @ConfigProperty(name = "deployment.env")
//    String deploymentEnv;
//
//    /** Define common tags that apply only to a Prometheus Registry */
//    @Produces
//    @Singleton
//    @MeterFilterConstraint(applyTo = PrometheusMeterRegistry.class)
//    public MeterFilter configurePrometheusRegistries() {
//        return MeterFilter.commonTags(Arrays.asList(
//                Tag.of("registry", "prometheus")));
//    }
//
//    /** Define common tags that apply globally */
//    @Produces
//    @Singleton
//    public MeterFilter configureAllRegistries() {
//        return MeterFilter.commonTags(Arrays.asList(
//                Tag.of("env", deploymentEnv)));
//    }
//
//}