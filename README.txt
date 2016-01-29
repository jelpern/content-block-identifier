README

Program goal
----------------
Takes a URL as input, and outputs the most commonly occuring element on the page that contains content. A content element is defined as any element whose child nodes include an image, a link, and text. Passing "debug" as an argument to main.java on execution will print out a list of all elements on the page and how many of them are content elements, sorted by the number of times each element appears.


Context
-------
This was a coding test for a software engineering interview. It was designed to fulfill the goal, nothing more, nothing less.

How It Works
------------

JSoup is the DOM/HTML parser 

Main is primarily for scrubbing input and creating output.

A link is identified by a tag with an href attribute (i.e. a selector matching "a[href]"). An image is identified by the presence of one or more of the following: 
- an <img> tag
- an element with a data-background attribute whose value ends in ".jpg", ".gif", or ".png"
- an element with a style attribute whose value starts with "background:url"

This is clearly not the full list of ways that an image can be represented in HTML5, but seemed like a good 80/20 point to stop.

Counter counts how many time each CSS selector occurs. This includes unique tag and tag.class combinations. In other words, elements with multiple classes are counted as both the tag with each class separately, as well as the tag with all the classes together (e.g. an element with the tag <div class="class1 class2"> will get counted as div.class1, div.class2, *and* div.class1.class2). This seemed like the safest thing to do. It would be easy to change this in the Counter.countContentElements method. Note that it is assumed that the order of the classes is the same in every element with the same combination of classes.

Counter.countContentElements uses an unsorted dictionary structure because the order could change with every insertion. Further, the order of the sort we want is by number of times the element contains content, but the dictionary uses the selector as keys.

Known Bugs
----------

It does not do a depth-wise check of whether the elements are nested or not. So, if a site has a series of nested div.class1 elements (i.e. <div class="class1"><div class="class1"><div class="class1"></div class="class1"></div class="class1"></div class="class1">), and the innermost element contained content, it would count that as three occurrences of that object having content. On non-content sites or sites with only a small amount of content, this can produce unexpected results; however on true content sites it is rare for such a set of nested elements to be more frequent than actual content elements. In the event that the most frequently occurring content element is a pair (or more) of divs that are always nested (which means that the count of each must be the same), either element may be returned.

It is unable to read sites that JSoup cannot read, e.g. rantchic.com. I played around with different user agents to see if I could fool the site into thinking my program was a browser, unsuccessfully.
