# USE CASE: 4 Produce a Report on the Salary of Employees of a Given Role

## CHARACTERISTIC INFORMATION

### Goal in Context

As an HR *advisor* I want to *view and employee's details* so that *the employee's promotion request can be supported.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the employee name.  Database contains current employee details.

### Success End Condition

A report is available for HR.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request from HR.

## MAIN SUCCESS SCENARIO

1. HR advisor  request details for a given employee.
2. HR advisor captures name of employee to get employee details.
3. HR advisor provides report to HR.

## EXTENSIONS

2**Employee does not exist**:
    1. HR advisor informs HR no employee exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0