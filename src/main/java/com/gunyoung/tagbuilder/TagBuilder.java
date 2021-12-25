package com.gunyoung.tagbuilder;

import com.gunyoung.tagbuilder.node.TagNode;

public class TagBuilder {
    private TagNode rootNode;
    private TagNode currentNode;

    public TagBuilder(String rootTagName) {
        rootNode = new TagNode(rootTagName);
        currentNode = rootNode;
    }

    public void addChild(String childTagName) {
        TagNode childNode = new TagNode(childTagName);
        currentNode.add(childNode);
        currentNode = childNode;
    }

    public void addSibling(String siblingTagName) {

    }

    public void addTo(TagNode parentNode, String tagName) {

    }

    public String toXml() {
        return rootNode.toString();
    }
}
