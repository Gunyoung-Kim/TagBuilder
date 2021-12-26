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
        addTo(currentNode, childTagName);
    }

    public void addSibling(String siblingTagName) {
        TagNode parentNode = currentNode.getParent();
        addTo(parentNode, siblingTagName);
    }

    public void addToParent(String parentTagName, String childTagName) {
        TagNode parentNode = findParentNode(parentTagName);
        addTo(parentNode, childTagName);
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

    private void addTo(TagNode parentNode, String childTagName) {
        currentNode = new TagNode(childTagName);
        parentNode.add(currentNode);
    }

    public String toXml() {
        return rootNode.toString();
    }
}
