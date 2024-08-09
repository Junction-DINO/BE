package dino.junction.common.aop.model;

public record TraceStatus(TraceId traceId, Long startTimeMs, String message) {
}
