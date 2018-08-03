"For everyone else: No matter what language you work in, programming in 
a functional style provides benefits. You should do it whenever it is 
convenient, and you should think hard about the decision when it isn't 
convenient. You can learn about lambdas, monads, currying, composing 
lazily evaluated functions on infinite sets, and all the other aspects 
of explicitly functionally oriented languages later if you choose.
"

"Pure functions are trivial to test; the tests look like something right 
out of a textbook, where you build some inputs and look at the output. 
Whenever I come across a finicky looking bit of code now, I split it out 
into a separate pure function and write tests for it. Frighteningly, I 
often find something wrong in these cases, which means I'm probably not 
casting a wide enough net."

"We are a "Get 'er done" sort of industry, and I do not see formal proofs 
of whole program "correctness" becoming a relevant goal, but being able 
to prove that certain classes of flaws are not present in certain parts of 
a codebase will still be very valuable. We could use some more science and 
math in our process. "

"Avoiding the worst in a broader context is generally more important than 
achieving perfection in limited cases. If you consider the most toxic 
functions or systems you have had to deal with, the ones that you know have 
to be handled with tongs and a face shield, it is an almost sure bet that 
they have a complex web of state and assumptions that their behavior relies 
on, and it isn't confined to their parameters. Imposing some discipline in 
these areas, or at least fighting to prevent more code from turning into 
similar messes, is going to have more impact than tightening up some 
low-level math functions."

"If someone modifies a widely used foundation function to be non-pure in 
some evil way, everything that uses the function also loses its purity. This 
sounds disastrous from a formal systems point of view, but again, it isn't 
an all-or-nothing proposition where you fall from grace with the first sin. 
Large scale software development is unfortunately statistical."

// Defending the extra data copies
"A significant mitigating factor is that performance today means parallel 
programming, which usually requires more copying and combining than in a 
single threaded environment even in the optimal performance case, so the 
penalty is smaller, while the complexity reduction and correctness benefits 
are correspondingly larger."