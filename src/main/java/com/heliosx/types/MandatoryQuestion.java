package com.heliosx.types;

public interface MandatoryQuestion extends Question {
  Boolean correctAnswer();
  String getRejectionReason();
}
