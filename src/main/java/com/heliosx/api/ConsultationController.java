package com.heliosx.api;

import com.heliosx.models.request.ViagraConsultationRequest;
import com.heliosx.models.response.ConsultationResult;
import com.heliosx.models.response.ViagraConsultationResponse;
import com.heliosx.service.ConsultationService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;

@Controller("/consultation")
public class ConsultationController {

  private final Logger log = org.slf4j.LoggerFactory.getLogger(ConsultationController.class);

  private final ConsultationService consultationService;

  public ConsultationController(ConsultationService consultationService) {
    this.consultationService = consultationService;
  }

  @Get(value = "/viagra", produces = MediaType.APPLICATION_JSON)
  public ViagraConsultationResponse getHealthConsultation() {
    return consultationService.getViagraConsultation();
  }

  @Post(value = "/viagra", consumes = MediaType.APPLICATION_JSON)
  public ConsultationResult sendViagraConsultation(@Body ViagraConsultationRequest consultationRequest) {
    log.info("Received Viagra Consultation Request: {}", consultationRequest);
    return consultationService.getViagraConsultationResponse(consultationRequest);
  }
}
