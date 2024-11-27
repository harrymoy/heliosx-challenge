package com.heliosx.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.heliosx.models.request.AgeAnswerRequest;
import com.heliosx.models.request.ViagraAnswerRequest;
import com.heliosx.models.request.ViagraConsultationRequest;
import com.heliosx.models.response.ConsultationResult;
import com.heliosx.types.UserInfoQuestion;
import com.heliosx.types.ViagraQuestion;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Test;

@MicronautTest
public class ConsultationControllerTest {

  @Inject
  ConsultationController underTest;

  @Test
  void whenConsultationRequestHasCorrectAnswers_thenReturnSuccess() {
    ViagraConsultationRequest consultationRequest = new ViagraConsultationRequest(
      new AgeAnswerRequest(UserInfoQuestion.AGE, true),
      List.of(
        new ViagraAnswerRequest(ViagraQuestion.HIGH_BLOOD_PRESSURE, false),
        new ViagraAnswerRequest(ViagraQuestion.LOW_BLOOD_PRESSURE, false),
        new ViagraAnswerRequest(ViagraQuestion.AVOID_STRENUOUS_EXERCISE, false),
        new ViagraAnswerRequest(ViagraQuestion.DIFFICULTY_IN_WALKING, false),
        new ViagraAnswerRequest(ViagraQuestion.DEPRESSION, false),
        new ViagraAnswerRequest(ViagraQuestion.VIAGRA_ALLERGY, false),
        new ViagraAnswerRequest(ViagraQuestion.LISTED_PROBLEMS, false)
      )
    );

    ConsultationResult result = underTest.sendViagraConsultation(consultationRequest);

    assertThat(result).satisfies(consultationResult -> {
      assertThat(consultationResult.success()).isTrue();
      assertThat(consultationResult.rejectionReasons()).isEmpty();
    });
  }

  @Test
  void whenConsultationRequestHasIncorrectAnswers_thenReturnFailure() {
    ViagraConsultationRequest consultationRequest = new ViagraConsultationRequest(
      new AgeAnswerRequest(UserInfoQuestion.AGE, true),
      List.of(
        new ViagraAnswerRequest(ViagraQuestion.HIGH_BLOOD_PRESSURE, false),
        new ViagraAnswerRequest(ViagraQuestion.LOW_BLOOD_PRESSURE, false),
        new ViagraAnswerRequest(ViagraQuestion.AVOID_STRENUOUS_EXERCISE, false),
        new ViagraAnswerRequest(ViagraQuestion.DIFFICULTY_IN_WALKING, false),
        new ViagraAnswerRequest(ViagraQuestion.DEPRESSION, true),
        new ViagraAnswerRequest(ViagraQuestion.VIAGRA_ALLERGY, false),
        new ViagraAnswerRequest(ViagraQuestion.LISTED_PROBLEMS, false)
      )
    );

    ConsultationResult result = underTest.sendViagraConsultation(consultationRequest);

    assertThat(result).satisfies(consultationResult -> {
      assertThat(consultationResult.success()).isFalse();
      assertThat(consultationResult.rejectionReasons()).containsExactly(ViagraQuestion.DEPRESSION.getRejectionReason());
    });
  }

  @Test
  void whenConsultationRequestHasMissingAnswers_thenReturnFailure() {
    ViagraConsultationRequest consultationRequest = new ViagraConsultationRequest(
      new AgeAnswerRequest(UserInfoQuestion.AGE, true),
      List.of(
        new ViagraAnswerRequest(ViagraQuestion.HIGH_BLOOD_PRESSURE, false),
        new ViagraAnswerRequest(ViagraQuestion.LOW_BLOOD_PRESSURE, false),
        new ViagraAnswerRequest(ViagraQuestion.AVOID_STRENUOUS_EXERCISE, false),
        new ViagraAnswerRequest(ViagraQuestion.DIFFICULTY_IN_WALKING, false),
        new ViagraAnswerRequest(ViagraQuestion.DEPRESSION, true),
        new ViagraAnswerRequest(ViagraQuestion.LISTED_PROBLEMS, false)
      )
    );

    assertThatThrownBy( () -> underTest.sendViagraConsultation(consultationRequest))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Missing answers for the following questions:");
  }

  @Test
  void whenConsultationRequestHasNullAnswer_thenReturnFailure() {
    ViagraConsultationRequest consultationRequest = new ViagraConsultationRequest(
      new AgeAnswerRequest(UserInfoQuestion.AGE, true),
      List.of(
        new ViagraAnswerRequest(ViagraQuestion.HIGH_BLOOD_PRESSURE, false),
        new ViagraAnswerRequest(ViagraQuestion.LOW_BLOOD_PRESSURE, false),
        new ViagraAnswerRequest(ViagraQuestion.AVOID_STRENUOUS_EXERCISE, false),
        new ViagraAnswerRequest(ViagraQuestion.DIFFICULTY_IN_WALKING, false),
        new ViagraAnswerRequest(ViagraQuestion.DEPRESSION, false),
        new ViagraAnswerRequest(ViagraQuestion.VIAGRA_ALLERGY, null),
        new ViagraAnswerRequest(ViagraQuestion.LISTED_PROBLEMS, false)
      )
    );

    assertThatThrownBy( () -> underTest.sendViagraConsultation(consultationRequest))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Answer cannot be null for question: ");
  }
}
