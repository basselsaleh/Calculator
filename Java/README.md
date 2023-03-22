# The Java Version

This is the first implementation I set out to complete. I haven't done any non-matplotlib GUI programming in years, and back in the day I was a whizz with the `awt` and `swing` libraries in Java. So I tried to make this version of the app in the most Java-y way possible, emphasizing things like:
- inheritance, with the app itself being a class inheriting from `JFrame`, while my `Evaluator` interface is implemented in both the `CustomEvaluator` and `JSEngineEvaluator` classes, in a separate file
- proper designation of `private` vs `public` data and methods
- utilizing the OOP principle of encapsulation to handle window components like `JPanel`s, `JTextField`s, etc.
- even though there is a built-in "JavaScript Engine" accessible with `javax.script`, it felt a bit like cheating, so I implemented my own custom parser/evaluator for arithmetic expressions; "doing it yourself" is certainly Java-y

Java is of course a language that prioritizes rule-following above all else. There are ways to break out of those rules, but doing so would be against the spirit of Java programming. So this is probably the implementation with the most dotted i's and crossed t's. Also, kind of a hot take but I like the look and feel of `swing` GUIs.
