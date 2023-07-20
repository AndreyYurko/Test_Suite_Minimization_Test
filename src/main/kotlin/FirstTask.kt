/*
Let us first understand how the proposed ways can resolve mentioned problems.

Proposed ways:
 - Implement a test suite minimization module that will reduce the number of tests after Kex has finished the
   analysis.
 - Improve the analysis process of Kex itself so that it will not explore execution paths that are already covered by
   other test cases

Problems:
1. It is hard to understand what each test case does and why it is needed.
This happens firstly because of the heavy use of reflection, and both proposed ways cannot resolve this problem.
Although there is a chance that better analysis could reduce, for example, the size of each test and this could improve
readability.

2. Compiling and running that amount of test cases requires a lot of time.
Both proposed ways address this problem. The improved analysis will obviously reduce the number of test cases and
increase the importance of each test. Test suite minimization also will reduce the number of tests, but it still will
take a lot of time (and space) for the first generation.

3. Generated test suite has a lot of redundancy: many of the test cases repeatedly cover the same parts of the program.
Both proposed ways designed to resolve this issue.

Now let us discuss the pros and cons of each method.

1. Test suite minimization.

Pros:
 - Significantly reduces the number of test cases - in the provided article the final result was about 10-40% from the
   original number.
 - With the already implemented Symbolic Execution tree, it seems like this method is easier to implement using the
   Greedy algorithm compared to other methods.
 - The user will have parameters he wants to tune: line coverage, mutant score, and losses compared to the whole set of
   tests.

Cons:
 - As mentioned before, it is still needed to generate the first set of tests and then analyze it. It still takes
   considerable time.
 - In addition to the previous one, as the project will expand, it is needed to implement some criteria that will decide
   when we need to generate tests, which test should be generated and how to use test suit minimization after
   generation. Because we first generate all tests and then reduce their number, each iteration still may take a lot
   of time.
 - Even if the accuracy loss is minimal, it is still maybe crucial. There is a possibility of accidentally deleting
   important test because of Symbolic Execution boundaries or not sufficient mutant generation coverage.

2. Improve the analysis process.

Pros:
 - Also significantly reduces the number of test cases. In fact, if we talk about abstract "best solution", it seems
   like test suit minimization is not necessary at all. In other words, we address the causes of problems rather than
   trying to reduce the effects.
 - With better analysis, it is possible to get more readable tests. Also, if we have fewer tests count that checks the
   same code, on fail user will try to understand fewer test cases. Because of the 
Cons:
 - It seems a lot more difficult than test suite minimization implementation. From my point of view, it is harder to
   improve the existing algorithm, compare to implementing Greedy Test Suit Minimization.
 - Can be more memory and time-consuming.
 - Harder to evaluate accuracy - because of complicated functions (or boundaries of symbolic execution), we cannot build
   the complete path tree, so the only mean of comparison I could think of is a mutant score. But calculating it for two
   big test suits, again and again, is really time-consuming.
 */