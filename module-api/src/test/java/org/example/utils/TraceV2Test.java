package org.example.utils;

import org.junit.jupiter.api.Test;

class TraceV2Test {

    @Test
    void begin_end() {
        //given
        TraceV2 trace = new TraceV2();
        TraceStatus status1 = trace.begin("begin");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "begin2");
        trace.end(status2);
        trace.end(status1);
    }

    @Test
    void begin_exception() {
        TraceV2 trace = new TraceV2();
        TraceStatus status1 = trace.begin("begin");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "begin2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }

}