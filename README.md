# QA Engineer / SDET - Interview Assignment

## 🎯 Objective
Design and implement a test strategy and automation suite to validate the functionality and reliability of a sample backend API.

## 📦 Provided
- A basic REST API (e.g., a small Spring Boot app) that supports creation of `Event` and `Bookings`.

## ✅ Requirements

### 1. **Test Plan**
Write a short test plan (1-2 pages) describing:
- Key functional test scenarios.
- Edge cases and negative tests.
- Suggestions for performance and security testing.
- Tools you'd use and why.

_Note: This can be presented in a concise format, such as a list of bullet points._

- Bookings and Events
- Bookings need to creatable and cancellable
- An error should be returned if a cancellation is attempted of a non-existing booking
- An error should also be returned if an attempt is made to make a booking for a fully-booked event (EventFullyBookedException)
- An error should be returned if there is a missing or null userId header
- Events need to be creatable and listable
- Invalid EventDTO (e.g missing fields)
- I would test this with JUnit and Mockito to mock data
- Performance testing with JMeter, Security testing could take the form of API endpoint testing with tools like Postman and also Burp Suite to interrupt API traffic
  
### 2. **Automated Tests**
Implement a test suite using your preferred language and framework (e.g., Java with JUnit/TestNG and RestAssured, or Python with Pytest and Requests). 
However, since most of our codebase is written in Java, we recommend using Java for consistency.
- Cover basic functionalities: make/cancel a booking for an event.
- Demonstrate test setup/teardown and assertions.
- Include at least one parameterized or data-driven test.

_Note: Not all test cases outlined in the test plan need to be implemented. We are primarily looking for examples for each type of test case._

### 3. **Bug Report (Optional Bonus)**
Run exploratory testing against the app and submit a brief report (1–2 bugs or suggestions).

---

## 📤 Submission
- Code should be in a GitHub repo or zip file.
- Include a short `README.md` with instructions to run the tests.
- Time expected: 2–3 hours max.
