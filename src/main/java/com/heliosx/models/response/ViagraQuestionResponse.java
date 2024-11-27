package com.heliosx.models.response;

import com.heliosx.types.ViagraQuestion;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record ViagraQuestionResponse(ViagraQuestion questionType, String question) {

}
