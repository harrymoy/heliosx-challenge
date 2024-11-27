package com.heliosx.models.request;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import java.util.List;

@Introspected
@Serdeable
public record ViagraConsultationRequest(AgeAnswerRequest userAge, List<ViagraAnswerRequest> viagraAnswers) {

}
