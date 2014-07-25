package com.timgroup.statsd;

/**
 * Describes a client connection to a StatsD server, which may be used to post metrics
 * in the form of counters, timers, and gauges.
 * 
 * <p>Three key methods are provided for the submission of data-points for the application under
 * scrutiny:
 * <ul>
 *   <li>{@link #incrementCounter} - adds one to the value of the specified named counter</li>
 *   <li>{@link #recordGaugeValue} - records the latest fixed value for the specified named gauge</li>
 *   <li>{@link #recordExecutionTime} - records an execution time in milliseconds for the specified named operation</li>
 * </ul>
 * 
 * @author Tom Denley
 *
 */
public interface StatsDClient {

    /**
     * Cleanly shut down this StatsD client. This method may throw an exception if
     * the socket cannot be closed.
     */
    void stop();

    /**
     * Adjusts the specified counter by a given delta.
     * 
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     * 
     * @param aspect
     *     the name of the counter to adjust
     * @param delta
     *     the amount to adjust the counter by
     */
    void count(String aspect, long delta);

    /**
     * Increments the specified counter by one.
     * 
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     * 
     * @param aspect
     *     the name of the counter to increment
     */
    void incrementCounter(String aspect);

    /**
     * Convenience method equivalent to {@link #incrementCounter(String)}.
     */
    void increment(String aspect);

    /**
     * Decrements the specified counter by one.
     * 
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     * 
     * @param aspect
     *     the name of the counter to decrement
     */
    void decrementCounter(String aspect);

    /**
     * Convenience method equivalent to {@link #decrementCounter(String)}.
     */
    void decrement(String aspect);

    /**
     * Records the latest fixed value for the specified named gauge.
     * 
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     * 
     * @param aspect
     *     the name of the gauge
     * @param value
     *     the new reading of the gauge
     */
    void recordGaugeValue(String aspect, long value);

    /**
     * Records a change in the value of the specified named gauge.
     *
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     *
     * @param aspect
     *     the name of the gauge
     * @param delta
     *     the +/- delta to apply to the gauge
     */
    void recordGaugeDelta(String aspect, long delta);

    /**
     * Convenience method equivalent to {@link #recordGaugeValue(String, long)}.
     */
    void gauge(String aspect, long value);

    /**
     * StatsD supports counting unique occurrences of events between flushes, Call this method to records an occurrence
     * of the specified named event.
     * 
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     * 
     * @param aspect
     *     the name of the set
     * @param eventName
     *     the value to be added to the set
     */
    void recordSetEvent(String aspect, String eventName);

    /**
     * Convenience method equivalent to {@link #recordSetEvent(String, String)}.
     */
    void set(String aspect, String eventName);

    /**
     * Records an execution time in milliseconds for the specified named operation.
     * 
     * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
     * 
     * @param aspect
     *     the name of the timed operation
     * @param timeInMs
     *     the time in milliseconds
     */
    void recordExecutionTime(String aspect, long timeInMs);

    /**
     * Convenience method equivalent to {@link #recordExecutionTime(String, long)}.
     */
    void time(String aspect, long value);

}