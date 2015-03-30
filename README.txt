README

Main is primarily for scrubbing input and creating output.

Counter counts how many time each CSS selector occurs. This includes unique tag and tag.class combinations. In other words, elements with multiple classes are counted as both the tag with each class separately, as well as the tag with all the classes together (e.g. an element with the tag <div class="class1 class2"> will get counted as div.class1, div.class2, *and* div.class1.class2). This seemed like the safest thing to do. It would be easy to change this in the Counter.countContentElements method. Note that it is assumed that the order of the classes is the same in every element with the same combination of classes.

Counter.countContentElements uses an unsorted dictionary structure because the order could change with every insertion. Further, the order of the sort we want is by number of times the element contains content, but the dictionary uses the selector as keys.

A content element is defined as any element whose child nodes include a tag with an href attribute (i.e. a selector matching "a[href]"), an <img> tag, and text of any kind. It does not work with sites that display images using tags other than <img> (e.g. fansided.com uses a data-background attribute with a value that points to an image file).

It is unable to read site that JSoup cannot read, e.g. rantchic.com. I played around with different useragents to see if I could fool the site into thinking my program was a browser, unsuccessfully.
