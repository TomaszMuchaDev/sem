# USE CASE: 4 Produce a Report on the Salary of Employees of a Given Role

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want to *update an employee's details* so that *employee's details are kept up-to-date.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the empoyee name.  Database contains current employee details.

### Success End Condition

A report is available for HR.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request is sent to HR.

## MAIN SUCCESS SCENARIO

1. A request is sent to HR for a given employee.
2. HR advisor captures name of the employee to get actual details.
3. HR advisor updates current details information of the given employee.

## EXTENSIONS

2**Employee does not exist**:
    1. HR advisor informs HR the given employee not exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0