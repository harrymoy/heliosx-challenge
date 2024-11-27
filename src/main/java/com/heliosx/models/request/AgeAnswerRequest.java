package com.heliosx.models.request;

import com.heliosx.types.UserInfoQuestion;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record AgeAnswerRequest(UserInfoQuestion ageAnswer, Boolean answer) {

}
