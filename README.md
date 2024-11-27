# HeliosX Consultation API

### Info on how to run:

- Clone this repository
- Navigate to Application.java
- Run the main method
- Import the postman-collection.json into Postman
- Postman doesn't generate full body in the example for the POST request, here's a full example:

``` json
{
    "userAge": {
        "ageAnswer": "AGE",
        "answer": true
    },
    "viagraAnswers": [
        {
            "answerType": "HIGH_BLOOD_PRESSURE",
            "answer": false
        },
        {
            "answerType": "LOW_BLOOD_PRESSURE",
            "answer": false
        },
        {
            "answerType": "AVOID_STRENUOUS_EXERCISE",
            "answer": false
        },
        {
            "answerType": "DIFFICULTY_IN_WALKING",
            "answer": false
        },
        {
            "answerType": "DEPRESSION",
            "answer": null
        },
        {
            "answerType": "VIAGRA_ALLERGY",
            "answer": false
        },
        {
            "answerType": "LISTED_PROBLEMS",
            "answer": false
        }
    ]
}
```

### Thoughts on API

- There's some extensibility in place, but there could be more
- Use generics for validating that all `MandatoryQuestion` are answered
- Implement this validation in a custom validator method such as `@Mandatory` that can sit at the request level