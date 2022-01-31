# USE CASE: 4 Produce a Report on the Salary of Employees of a Given Role

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want to *add a new employee's details* so that *I can ensure the new employee is paid.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the emploee details.

### Success End Condition

New emploee added to Database.

### Failed End Condition

New emploee is not added.

### Primary Actor

HR Advisor.

### Trigger

A request from HR is sent to finance.

## MAIN SUCCESS SCENARIO

1. HR request to add new employee
2. HR advisor checks if name of employee already is in Databse.
3. HR advisor provides details of new employee.
4. New emploee added to Database.

## EXTENSIONS

2**Emploee alerady exist**:
    1. HR advisor informs finance that emploee already exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0