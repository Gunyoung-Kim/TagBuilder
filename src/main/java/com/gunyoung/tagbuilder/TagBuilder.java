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
        TagNode parentNode = currentNode.getParent();
        TagNode siblingNode = new TagNode(siblingTagName);
        parentNode.add(siblingNode);
        currentNode = siblingNode;
    }

    public void addToParent(String parentTagName, String childTagName) {
        TagNode parentNode = findParentNode(parentTagName);
        TagNode childNode = new TagNode(childTagName);
        parentNode.add(childNode);
        currentNode = childNode;
    }

    private TagNode findParentNode(String parentTagName) {
        TagNode parentNode = currentNode;
        while(parentNode != null) {
            if(parentNode.getTagName().equals(parentTagName))
                return parentNode;
            parentNode = parentNode.getParent();
        }
        throw new NodeNotFoundedException("Such parent is not founded");
    }

    public String toXml() {
        return rootNode.toString();
    }
}
