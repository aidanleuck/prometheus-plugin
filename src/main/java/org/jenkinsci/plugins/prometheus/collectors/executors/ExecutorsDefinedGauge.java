package org.jenkinsci.plugins.prometheus.collectors.executors;

import hudson.model.LoadStatistics;
import io.prometheus.client.Gauge;
import org.jenkinsci.plugins.prometheus.collectors.BaseMetricCollector;
import org.jenkinsci.plugins.prometheus.collectors.CollectorType;

public class ExecutorsDefinedGauge extends BaseMetricCollector<LoadStatistics.LoadStatisticsSnapshot, Gauge> {


    protected ExecutorsDefinedGauge(String[] labelNames, String namespace, String subsystem, String namePrefix) {
        super(labelNames, namespace, subsystem, namePrefix);
    }

    @Override
    protected Gauge initCollector() {
        return Gauge.build()
                .name(calculateName(CollectorType.EXECUTORS_DEFINED_GAUGE.getName()))
                .subsystem(subsystem).namespace(namespace)
                .labelNames(labelNames)
                .help("Executors Defined")
                .create();
    }

    @Override
    public void calculateMetric(LoadStatistics.LoadStatisticsSnapshot jenkinsObject, String[] labelValues) {
        if (jenkinsObject == null) {
            return;
        }
        this.collector.labels(labelValues).set(jenkinsObject.getDefinedExecutors());
    }
}
