package com.gunyoung.tagbuilder.node;

import java.util.ArrayList;
import java.util.List;

public class TagNode {
    private String tagName = "";
    private String value = "";
    private StringBuffer attributes;
    private TagNode parent;
    private List<TagNode> children = new ArrayList<>();

    public TagNode(String tagName) {
        this.tagName = tagName;
        this.attributes = new StringBuffer();
    }

    public String getTagName() {
        return tagName;
    }

    public String getValue() {
        return value;
    }

    public StringBuffer getAttributes() {
        return attributes;
    }

    public TagNode getParent() {
        return parent;
    }

    public List<TagNode> getChildren() {
        return children;
    }

    public void setParent(TagNode parent) {
        this.parent = parent;
    }

    public void addAttribute(String attribute, String value) {
        this.attributes.append(" ")
                .append(attribute)
                .append("='")
                .append(value)
                .append("'");
    }

    public void add(TagNode childNode) {
        if(this == childNode)
            throw new WrongAddException("Can't add self");
        childNode.setParent(this);
        children.add(childNode);
    }

    public void addValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        addContentTo(result);
        return result.toString();
    }

    private void addContentTo(StringBuilder result) {
        addStartTag(result);
        addElement(result);
        addEndTag(result);
    }

    private void addStartTag(StringBuilder sb) {
        sb.append("<")
                .append(tagName)
                .append(attributes)
                .append(">");
    }

    private void addElement(StringBuilder sb) {
        for(TagNode child: children) {
            child.addContentTo(sb);
        }
        sb.append(value);
    }

    private void addEndTag(StringBuilder sb) {
        sb.append("</")
                .append(tagName)
                .append(">");
    }
}
