package com.heliosx.models.request;

import com.heliosx.types.ViagraQuestion;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record ViagraAnswerRequest(ViagraQuestion answerType, Boolean answer) {

}
