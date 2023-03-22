# The Java Version

This is the first implementation I set out to complete. I haven't done any non-matplotlib GUI programming in years, and back in the day I was a whizz with the `awt` and `swing` libraries in Java. So I tried to make this version of the app in the most Java-y way possible, emphasizing things like:
- inheritance, with the app itself being a class inheriting from `JFrame`, while my `Evaluator` interface is implemented in both the `CustomEvaluator` and `JSEngineEvaluator` classes, in a separate file
- proper designation of `private` vs `public` data and methods, 
