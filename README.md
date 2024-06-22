# websocket-testing-client

## Current features

### Clients
 - Plain Websocket
 - Stomp
 - **!!No SockJS support for now!!**
### TestCase builder
   - Connect to an arbitrary Stomp-WS endpoint
   - Subscribe to a topic
   - Waiting (time-based)
   - Validate received messages

The test ```org.websockettestingclient.testframework.BasicTestCasesUnitTest``` contains an example on how to use these.

## Goal
Goal is to create a simple to use websocket client for testing websocket interactions, with on-demand support for:
 - Plain websockets
 - STOMP
 - SockJS

The basic testing goals are:
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
