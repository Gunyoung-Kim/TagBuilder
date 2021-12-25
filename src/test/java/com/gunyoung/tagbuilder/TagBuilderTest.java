package com.gunyoung.tagbuilder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TagBuilderTest {

    @Test
    @DisplayName("Xml 반환 -> root Node 만 존재")
    void toXml_test_rootOnly() {
        //Given
        String rootTagName = "root";
        TagBuilder tagBuilder = new TagBuilder(rootTagName);

        String expected =
                "<" + rootTagName + ">" +
                        "" +
                "</" + rootTagName + ">";
        //When
        String result = tagBuilder.toXml();

        //Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Xml 반환 -> 루트 - 1 자식")
    void toXml_test_rootAndChild() {
        //Given
        TagBuilder tagBuilder = new TagBuilder("root");
        tagBuilder.addChild("child");

        String expected =
                "<root>" +
                    "<child></child>" +
                "</root>";

        //When
        String result = tagBuilder.toXml();

        //Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Xml 반환 -> 루트 - 2 자식")
    void toXml_test_rootAndTwoChildren() {
        //Given
        TagBuilder tagBuilder = new TagBuilder("root");
        tagBuilder.addChild("childOne");
        tagBuilder.addSibling("childTwo");

        String expected =
                "<root>" +
                    "<childOne></childOne>" +
                    "<childTwo></childTwo>" +
                "</root>";

        //When
        String result = tagBuilder.toXml();

        //Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Xml 반환 -> 루트 - 1 자식 - 1자식 - 1자식 - 1자식")
    void toXml_test_rootAndFourLevelDown() {
        //Given
        TagBuilder tagBuilder = new TagBuilder("root");
        tagBuilder.addChild("one");
        tagBuilder.addChild("two");
        tagBuilder.addChild("three");
        tagBuilder.addChild("four");

        String expected =
                "<root>" +
                    "<one>" +
                        "<two>" +
                            "<three>" +
                                "<four>" +
                                "</four>" +
                            "</three>" +
                        "</two>" +
                    "</one>" +
                "</root>";

        //When
        String result = tagBuilder.toXml();

        //Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Xml 반환 -> 루트 - 2자식 - 1자식, 리프 노드 추가 후 논 리프에 추가 by addToParent")
    void toXml_test_for_addToParent() {
        //Given
        TagBuilder tagBuilder = new TagBuilder("root");
        tagBuilder.addChild("first");
        tagBuilder.addChild("second");
        tagBuilder.addToParent("root", "one");

        String expected =
                "<root>" +
                    "<first>" +
                        "<second></second>" +
                    "</first>" +
                    "<one>" +
                    "</one>" +
                "</root>";
        //When
        String result = tagBuilder.toXml();

        //Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("특정 노드에 자식 노드 추가 -> 해당 노드가 존재하지 않음")
    void addToParent_test_nonExistParent() {
        //Given
        TagBuilder tagBuilder = new TagBuilder("root");

        //When, Then
        assertThrows(NodeNotFoundedException.class, () -> tagBuilder.addToParent("nonExist", "child"));
    }
}
