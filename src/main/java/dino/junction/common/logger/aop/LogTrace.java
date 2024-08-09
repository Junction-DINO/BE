package dino.junction.common.logger.aop;


import dino.junction.common.logger.model.TraceId;
import dino.junction.common.logger.model.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
    String getTraceId();
}
