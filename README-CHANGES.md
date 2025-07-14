# Changes Made to Enable businessTraceId Querying in Victoria Logs

## Issue
The original issue was: "In the victoria logs, how can i select the businessTraceId"

## Solution

To address this issue, I made the following changes:

### 1. Standardized MDC Key Names Across Services

I identified inconsistencies in how MDC keys were named in the logback configuration files:

- In some places, trace IDs were referenced as `trace_id` and `span_id`
- In other places, they were referenced as `traceId` and `spanId`
- The `businessTraceId` key was consistent, but needed to be used with the correct pattern

I updated the logback configuration files in all services to use consistent MDC key names:

- Changed `trace_id` to `traceId`
- Changed `span_id` to `spanId`
- Kept `businessTraceId` as is

Files modified:
- `/parent-project/src/main/resources/logback-spring.xml`
- `/person-service/src/main/resources/logback-spring.xml`

### 2. Created Victoria Logs Query Guide

I created a comprehensive guide on how to query Victoria Logs for the businessTraceId:

- Basic query syntax for finding logs by businessTraceId
- Examples of combining businessTraceId with other fields
- Advanced query techniques
- Troubleshooting tips

File created:
- `/VICTORIA_LOGS_QUERIES.md`

## How to Use

1. Ensure all services are using the updated logback configuration files
2. Start the application and generate some logs
3. Access Victoria Logs at http://localhost:8889
4. Use the queries provided in the Victoria Logs Query Guide to search for logs by businessTraceId

For example, to find all logs with a specific businessTraceId:

```
{businessTraceId="your-business-trace-id"}
```

## Benefits

- Consistent MDC key naming makes it easier to query logs across services
- The Victoria Logs Query Guide provides clear instructions on how to query for businessTraceId
- Users can now effectively trace requests across services using the businessTraceId