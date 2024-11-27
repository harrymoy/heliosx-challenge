package com.heliosx.models.response;

import com.heliosx.types.UserInfoQuestion;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Introspected
@Serdeable
public record AgeQuestionResponse(UserInfoQuestion userInfoQuestion, String question) {

}
