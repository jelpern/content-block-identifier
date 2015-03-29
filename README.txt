README

Counter counts how many time each CSS selector occurs. This includes unique tag and tag.class combinations. Elements with multiple classes (e.g. <div class="class1 class2">) are entered into the Counter with selector "class1.class2" which is proper CSS. Note that it is assumed that the order of the classes is the same in every element with the same combination of classes.

ClassCounter is not currently implemented with a sorted class. This is because the order can change with every insertion. It would be easy to change this however in the implementation.

Counts as a content element any CSS selector (as defined above) whose child elements include an a[href], an <img> tag, and text of any kind. It does not work with sites that display images using tags other than <img> (e.g. fansided.com uses a data-background attribute with a value that
points to an img).

It is unable to read site that JSoup cannot read, e.g. rantchic.com. I played around with different useragents to see if I could fool the site into thinking my program was a browser, unsuccessfully.
