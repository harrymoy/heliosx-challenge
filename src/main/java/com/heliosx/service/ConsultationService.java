package com.heliosx.service;

import com.heliosx.models.request.ViagraAnswerRequest;
import com.heliosx.models.request.ViagraConsultationRequest;
import com.heliosx.models.response.AgeQuestionResponse;
import com.heliosx.models.response.ConsultationResult;
import com.heliosx.models.response.ViagraConsultationResponse;
import com.heliosx.models.response.ViagraQuestionResponse;
import com.heliosx.types.UserInfoQuestion;
import com.heliosx.types.MandatoryQuestion;
import com.heliosx.types.ViagraQuestion;
import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class ConsultationService {

  public ViagraConsultationResponse getViagraConsultation() {
    return new ViagraConsultationResponse(new AgeQuestionResponse(UserInfoQuestion.AGE, UserInfoQuestion.AGE.getQuestion()), getUserHealthQuestions());
  }

  public ConsultationResult getViagraConsultationResponse(ViagraConsultationRequest consultationRequest) {
    validateAllQuestionsAnswered(consultationRequest.viagraAnswers());
    HashMap<MandatoryQuestion, Boolean> answersToQuestions = getAnswersToQuestions(consultationRequest);
    List<String> rejectionReasons = getRejectionReasons(answersToQuestions);
    Boolean success = rejectionReasons.isEmpty();
    return new ConsultationResult(success, rejectionReasons);
  }

  private List<ViagraQuestionResponse> getUserHealthQuestions() {
    return Arrays.stream(ViagraQuestion.values()).toList()
      .stream()
      .map(viagraQuestion -> new ViagraQuestionResponse(viagraQuestion, viagraQuestion.getQuestion()))
      .toList();
  }

  private HashMap<MandatoryQuestion, Boolean> getAnswersToQuestions(ViagraConsultationRequest consultationRequest) {
    HashMap<MandatoryQuestion, Boolean> answersMap = new HashMap<>();
    answersMap.put(consultationRequest.userAge().ageAnswer(), consultationRequest.userAge().answer());

    // Add all viagra-related answers
    for (ViagraAnswerRequest viagraAnswer : consultationRequest.viagraAnswers()) {
      if (viagraAnswer.answer() != null) {
        answersMap.put(viagraAnswer.answerType(), viagraAnswer.answer());
      } else {
        throw new IllegalArgumentException("Answer cannot be null for question: " + viagraAnswer.answerType());
      }
    }

    return answersMap;
  }

  private List<String> getRejectionReasons(HashMap<MandatoryQuestion, Boolean> answersToQuestions) {
    List<String> rejectionReasons = new ArrayList<>();
    answersToQuestions.forEach((mandatoryQuestion, answer) -> {
      if (mandatoryQuestion.correctAnswer() != answer) {
        rejectionReasons.add(mandatoryQuestion.getRejectionReason());
      }
    });
    return rejectionReasons;
  }

  private void validateAllQuestionsAnswered(List<ViagraAnswerRequest> answers) {
    Set<ViagraQuestion> allPossibleQuestions = new HashSet<>(Arrays.asList(ViagraQuestion.values()));

    Set<ViagraQuestion> answeredQuestions = answers.stream()
      .map(ViagraAnswerRequest::answerType)
      .collect(Collectors.toSet());

    Set<ViagraQuestion> missingQuestions = new HashSet<>(allPossibleQuestions);
    missingQuestions.removeAll(answeredQuestions);

    if (!missingQuestions.isEmpty()) {
      throw new IllegalArgumentException("Missing answers for the following questions: " + missingQuestions);
    }

    Set<ViagraQuestion> duplicateQuestions = answers.stream()
      .map(ViagraAnswerRequest::answerType)
      .filter(question -> Collections.frequency(answers.stream()
        .map(ViagraAnswerRequest::answerType)
        .collect(Collectors.toList()), question) > 1)
      .collect(Collectors.toSet());

    if (!duplicateQuestions.isEmpty()) {
      throw new IllegalArgumentException("Duplicate answers found for questions: " + duplicateQuestions);
    }
  }
}
