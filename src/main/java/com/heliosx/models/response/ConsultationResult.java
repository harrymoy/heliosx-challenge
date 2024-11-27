package com.heliosx.models.response;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Introspected
@Serdeable
public record ConsultationResult(Boolean success, @Schema(nullable = true) List<String> rejectionReasons) {

}
