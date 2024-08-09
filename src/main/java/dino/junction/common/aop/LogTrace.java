package dino.junction.common.aop;


import dino.junction.common.aop.model.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);
    String getTraceId();
}
