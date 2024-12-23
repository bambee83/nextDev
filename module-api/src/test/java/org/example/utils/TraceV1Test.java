package org.example.utils;

import org.junit.jupiter.api.Test;

class TraceV1Test {

    @Test
    void begin_end() {
        //given
        TraceV1 trace = new TraceV1();
        TraceStatus status = trace.begin("begin");
        trace.end(status);
    }

    @Test
    void begin_exception() {
        TraceV1 trace = new TraceV1();
        TraceStatus status = trace.begin("begin");
        trace.exception(status, new IllegalStateException());
    }

}