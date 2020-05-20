Console implementation of a Hearts card game

##Notes on this version
This was my first attempt at writing an MVP pattern from the ground up

###What Went Well
1. Use of some new patterns: Observer (using Property Change Listeneners), and Singleton (for Deck)
2. Models are fairly stable and reasonably well tested, though perhaps a bit complex

###What Needs Improvement
1. I need to figure out the right level of abstraction for my models, views, and presenters. So far, I have too much complexity

###Next Steps
1. Stronger commitment to the discipline of TDD, which should help with the complexity problem
2. Start with thinking through the implications of passive vs. active views and make a consistent choice
3. Simplify models, create thoroughly tested presenters
4. Stretch goal: Get to the point of completing a full game, including recording tricks and calculating the winner

###Plan
1. Stop designing for the future. Let tests drive behavior
1. Think carefully about everything your presenters need to do (consider renaming to controllers if you decide they should contain most of the logic) and test them out
2. collapse your view into a single thing (don't yet need to think about different component pieces. See #1)