package com.athenahealth.collector.rules.imports.base;

import org.springframework.batch.core.Step;

public interface StepBuilder {
    Step getStep();
}
