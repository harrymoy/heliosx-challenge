package com.heliosx.types;

public enum ViagraQuestion implements MandatoryQuestion {
  HIGH_BLOOD_PRESSURE("Do you have high blood pressure?", "Your blood pressure is too high for this medication.", false),
  LOW_BLOOD_PRESSURE("Do you have low blood pressure?", "Your blood pressure is too low for this medication.", false),
  AVOID_STRENUOUS_EXERCISE("Have you been told to avoid strenuous exercise?", "Cardiovascular issues may cause complications using this medication", false),
  DIFFICULTY_IN_WALKING("Do you have difficulty walking?", "Mobility issues may cause complications using this medication", false),
  DEPRESSION("Do you suffer from depression?", "Depression may cause complications using this medication", false),
  VIAGRA_ALLERGY("Are you allergic to Viagra?", "We cannot prescribe a medication you are allergic to.", false),
  LISTED_PROBLEMS("Do you have any of the listed problems?", "We cannot provide a medication for any of the listed problems.", false),;

  private final String question;
  private final String rejectionReason;
  private final Boolean correctAnswer;

  ViagraQuestion(String question, String rejectionReason, Boolean correctAnswer) {
    this.question = question;
    this.rejectionReason = rejectionReason;
    this.correctAnswer = correctAnswer;
  }

  @Override
  public String getQuestion() {
    return question;
  }

  @Override
  public String getRejectionReason() {
    return rejectionReason;
  }

  @Override
  public Boolean correctAnswer() {
    return correctAnswer;
  }
}
