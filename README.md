# websocket-testing-client
Goal of this whole project is something that allows us to create "test execution runs" where a QA or Developer would define the list of steps to run, and the expected outcomes, the same way we can usually do with REST API end-to-end tests.
 - Open connections to arbitrary websockets
 - Push messages to servers
 - Subscribe to topics/queues
 - Receive messages and validate contents based on expectations
 - etc

Functional objectives will be:
 - Validate object format contracts
 - Validate communications work
 - Validate fallback mechanisms (e.g. SockJS)

Targeted protocols are:
 - Plain websockets
 - STOMP
 - SockJS


## Dependencies
- Logback
- Jackson
- tyrus-standalone-client as the jakarta.servlet client implementation.

*This project will not include Spring dependencies despite Spring being the test target, as it intends to be lightweight and, to the most part, framework-agnostic*
