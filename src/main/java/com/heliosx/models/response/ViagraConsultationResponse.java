package com.heliosx.models.response;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import java.util.List;

@Introspected
@Serdeable
public record ViagraConsultationResponse(AgeQuestionResponse userAge, List<ViagraQuestionResponse> viagraQuestions) implements
  ConsultationResponse {

}
