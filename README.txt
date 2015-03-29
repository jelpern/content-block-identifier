README

Counter counts how many time each CSS selector occurs. This includes unique tag and tag.class combinations. Elements with multiple classes (e.g. <div class="class1 class2">) are entered into the Counter with selector "class1.class2" which is proper CSS. Note that it is assumed that the order of the classes is the same in every element with the same combination of classes.

ClassCounter is not currently implemented with a sorted class. This is because the order can change with every insertion. It would be easy to change this however in the implementation.

If there are multiple spaces between class names in a tag, it will get the selector right. However it is possible that other types of whitespace 

Should counts have to be equal for the selector objects to be the same? I don't think so.