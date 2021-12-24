package com.gunyoung.tagbuilder.node;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagNodeTest {

    @Test
    @DisplayName("특성 추가 -> 정상")
    void addAttribute_test() {
        //Given
        TagNode node = getTestNode("test");
        String attribute = "attribute";
        String value = "value";

        //When
        node.addAttribute(attribute, value);

        //Then
        String stringOfAttributes = node.getAttributes().toString();
        assertTrue(stringOfAttributes.contains(" " + attribute + "=" + "'" + value + "'"));
    }

    @Test
    @DisplayName("자식 노드 추가 -> 자기 자신 추가하는 경우, 예외 발생")
    void add_test_selfAdd() {
        //Given
        TagNode node = getTestNode("add_test_selfAdd");

        //When, Then
        assertThrows(WrongAddException.class,() -> node.add(node));
    }

    @Test
    @DisplayName("자식 노드 추가 -> 정상")
    void add_test() {
        //Given
        TagNode node = getTestNode("add_test");
        TagNode childNode = getTestNode("childNode");

        //When
        node.add(childNode);

        //Then
        assertTrue(node.getChildren().contains(childNode));
    }

    @Test
    @DisplayName("값 추가 -> 정상")
    void addValue_test() {
        //Given
        TagNode node = getTestNode("addValue");
        String value = "newValue";

        //When
        node.addValue(value);

        //Then
        assertEquals(value, node.getValue());
    }

    @Test
    @DisplayName("toString -> 생성만된 TagNode")
    void toString_test_fromConstructor() {
        //Given
        String name = "country";
        TagNode node = getTestNode(name);

        String expected =
                "<" + name + ">" +
                    "" +
                "</" + name + ">";

        //When
        String result = node.toString();

        //Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("toString -> value 추가된 노드")
    void toString_test_withValue() {
        //Given
        String name = "name";
        String value = "valueForNode";
        TagNode node = getTestNode(name);
        node.addValue(value);

        String expected =
                "<" + name + ">" +
                        value +
                "</" + name + ">";

        //When
        String result = node.toString();

        //Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("toString -> child 추가된 노드")
    void toString_test_withChild() {
        //Given
        String name = "withChild";
        TagNode node = getTestNode(name);

        String childName = "child";
        TagNode child = getTestNode(childName);

        node.add(child);

        String expected =
                "<" + name + ">" +
                    "<" + childName + ">" +
                    "</" + childName + ">" +
                "</" + name + ">";

        //When
        String result = node.toString();

        //Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("toString -> attributes 추가된 노드")
    void toString_test_withAttributes() {
        //Given
        String name = "withAttributes";
        TagNode node = getTestNode(name);

        String attribute = "attri";
        String value = "val";

        node.addAttribute(attribute, value);

        String expected =
                "<" + name + " " + attribute + "='" + value + "'>" +
                        "" +
                "</" + name + ">";

        //When
        String result = node.toString();

        //Then
        assertEquals(expected, result);
    }

    private TagNode getTestNode(String name) {
        return new TagNode(name);
    }
}
