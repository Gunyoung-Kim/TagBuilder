package com.gunyoung.tagbuilder.node;

import java.util.ArrayList;
import java.util.List;

public class TagNode {
    private String tagName = "";
    private String value = "";
    private StringBuffer attributes;
    private List<TagNode> children;

    public TagNode(String tagName) {
        this.tagName = tagName;
        this.attributes = new StringBuffer("");
        this.children = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public StringBuffer getAttributes() {
        return attributes;
    }

    public List<TagNode> getChildren() {
        return children;
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
        children.add(childNode);
    }

    public void addValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        addStartTag(result);
        addElement(result);
        addEndTag(result);
        return result.toString();
    }

    private void addStartTag(StringBuilder sb) {
        sb.append("<")
                .append(tagName)
                .append(attributes)
                .append(">");
    }

    private void addElement(StringBuilder sb) {
        for(TagNode child: children) {
            sb.append(child.toString());
        }
        sb.append(value);
    }

    private void addEndTag(StringBuilder sb) {
        sb.append("</")
                .append(tagName)
                .append(">");
    }
}
