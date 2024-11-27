package com.heliosx;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
  info = @Info(
    title = "Consultation API",
    version = "1.0",
    description = "An API for getting consultation results",
    contact = @Contact(name = "Harry Moy", email = "harrymoy@me.com")
  )
)
public class Application {

  public static void main(String[] args) {
    Micronaut.run(Application.class, args);
  }
}