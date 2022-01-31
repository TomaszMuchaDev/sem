# USE CASE: 4 Produce a Report on the Salary of Employees of a Given Role

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want to *delete an employee's details* so that *the company is compliant with data retention legislation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the employee name.  Database contains given employee data.

### Success End Condition

A employee details are deleted.

### Failed End Condition

A employee details are not deleted

### Primary Actor

HR Advisor.

### Trigger

A request from HR.

## MAIN SUCCESS SCENARIO

1. HR requests to delete given employee details.
2. HR advisor captures name employee to get his details.
3. HR advisor deletes the given employee details.


## EXTENSIONS

2**Employee does not exist**:
    1. HR advisor informs HR that the given employee not exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0