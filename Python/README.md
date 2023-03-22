# The Python Version

In Python, it's so easy to just get something working. I could have made this more object oriented and perhaps a bit more readable, but in the spirit of Pythonic programming I just shoved everything into a `main` function and added things til it was working with expected behavior, then simplified redundant bits to be as compact as possible. In this way, we capture the spirit of programming in Python, taking advantage of many of the powerful (if abused) features of the language, such as:
- `lambda` expressions and `functools.partial` everywhere
- using `enumerate` to save on iterator fluff in for loops
- defining needed external functions within the `main` function, to keep needed variables in scope instead of passing them around
- using `eval` to evaluate the arithmetic expressions (security risk? shhhh)

All in all, this is probably going to be the easiest implementation, and also probably one of the easiest to break if someone really wanted to. Such is the way of the Python.
