# Victoria Logs Query Guide

This document provides instructions on how to query Victoria Logs for specific trace information, particularly focusing on the `businessTraceId`.

## Accessing Victoria Logs

Victoria Logs is accessible at http://localhost:8889 when running the Docker Compose setup.

## Basic Query Syntax

Victoria Logs uses LogsQL query language, which is similar to PromQL but designed for logs.

### Query Examples

#### 1. Query by businessTraceId

To find all logs with a specific businessTraceId:

```
{businessTraceId="your-business-trace-id"}
```

For example:

```
{businessTraceId="123e4567-e89b-12d3-a456-426614174000"}
```

#### 2. Query by businessTraceId across services

To find logs from a specific service with a particular businessTraceId:

```
{businessTraceId="your-business-trace-id", service_name="service-name"}
```

For example:

```
{businessTraceId="123e4567-e89b-12d3-a456-426614174000", service_name="parent-project"}
```

#### 3. Query by businessTraceId and log level

To find logs with a specific businessTraceId and log level:

```
{businessTraceId="your-business-trace-id", level="ERROR"}
```

#### 4. Query by businessTraceId and time range

To find logs with a specific businessTraceId within a time range:

```
{businessTraceId="your-business-trace-id"} | time > timestamp("2023-01-01T00:00:00Z") and time < timestamp("2023-01-02T00:00:00Z")
```

## Advanced Queries

### Combining with other fields

You can combine businessTraceId with other fields to narrow down your search:

```
{businessTraceId="your-business-trace-id", message=~".*error.*"}
```

### Aggregations

Count the number of logs per service for a specific businessTraceId:

```
count_over_time({businessTraceId="your-business-trace-id"}[1h]) by (service_name)
```

## Tips for Effective Querying

1. **Use the UI filters**: Victoria Logs UI provides filters that can help you narrow down your search.
2. **Start with broader queries**: Begin with a simple query for the businessTraceId, then add more conditions to refine your results.
3. **Check the time range**: Make sure your query is looking at the right time range.
4. **Use regular expressions**: For more flexible matching, use regular expressions with the `=~` operator.

## Troubleshooting

If you're not seeing expected results:

1. Verify that the businessTraceId is being correctly propagated between services
2. Check that the MDC keys in the logback configuration match the keys used in the code
3. Ensure that the OpenTelemetry collector is correctly configured to send logs to Victoria Logs