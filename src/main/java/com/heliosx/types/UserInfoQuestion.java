package com.heliosx.types;

public enum UserInfoQuestion implements MandatoryQuestion {
  AGE("Are you aged between 18-75?", "You must be between 18-75 for this medication", true);

  private final String question;
  private final String rejectionReason;
  private final Boolean correctAnswer;

  UserInfoQuestion(String question, String rejectionReason, Boolean correctAnswer) {
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
