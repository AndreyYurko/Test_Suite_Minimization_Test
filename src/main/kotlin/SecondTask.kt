/*
I think it is wrong to compare these two methods because as far as I understand this subject, we can combine them to
get better results. Every improvement in the algorithm itself will result in improvements in test suits, but it is hard
to predict how much space for improvements is there, and because of this it is hard to estimate efficiency and
quality. And every improvement in test suites will affect test suit minimization in a better way, or at least will not
harm the final performance.

The efficiency and quality of the test suite minimization also obviously depend on implementation. As I said earlier,
in the first task, we need some criteria for understanding how to reapply this method after the code has been changed.
If we run full test generation and then test suit minimization, after any changes in the code, it will be worse than the
situation right now.

So to make use of this method, we should implement some algorithm that will understand which tests
should be generated again and how to set a subset of requirements to pick a smaller suit after generation. After the
implementation, we need to compare this approach with the old one. I don't know how it works right now, but I think it
exploits a similar idea of understanding which test should be generated again. I think it will work better because
I presume that right now there is already implemented algorithm which checks that we should generate again. We only need
to understand how to merge the old (already minimized) test suit and new test suit and perform minimization after
merging or generate tests for changed code, perform minimization and then perform merging. And this merging shouldn't
take too long.

But as mentioned in the first question, test suit minimization sacrifices quality over time consumption. We should keep
that in mind then we will evaluate time efficiency.
*/