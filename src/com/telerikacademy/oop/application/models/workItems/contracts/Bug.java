package com.telerikacademy.oop.application.models.workItems.contracts;

import com.telerikacademy.oop.application.models.workItems.enums.Priority;
import com.telerikacademy.oop.application.models.workItems.enums.Severity;
import com.telerikacademy.oop.application.models.workItems.enums.Status;

import java.util.List;

public interface Bug {

    List<String> getSteps();

    Severity getSeverity();

    Priority getPriority();
}
