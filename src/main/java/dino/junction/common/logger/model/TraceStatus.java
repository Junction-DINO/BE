package dino.junction.common.logger.model;

public record TraceStatus(TraceId traceId, Long startTimeMs, String message) {
}
