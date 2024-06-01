# websocket-testing-client


Goal is to create a simple to use websocket client, with on-demand support for:
 - Plain websockets
 - STOMP
 - SockJS

I intend for this to also include a framework, as a prototype, to be able to run automatic, end-to-end tests against Spring websockets, so we can:
 - Validate object format contracts
 - Validate communications work
 - Validate fallback mechanisms (e.g. SockJS)

Ideally, this will allow us to create "test execution runs" where a QA or Developer would define the list of steps to run, and the expected outcomes, the same way we can usually do with REST API end-to-end tests.
 - Open connections to arbitrary websockets
 - Push messages to servers
 - Subscribe to topics/queues
 - Receive messages and validate contents based on expectations
 - etc


## Dependencies
- Logback
- Jackson
- tyrus-standalone-client as the jakarta.servlet client implementation.

*This project will not include Spring dependencies despite Spring being the test target, as it intends to be lightweight and, to the most part, framework-agnostic*
