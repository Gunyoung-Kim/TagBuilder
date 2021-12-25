package com.gunyoung.tagbuilder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @DisplayName("Xml 반환 -> 루트와 그 형제")
    void toXml_test_rootAndSibling() {
        //Given
        TagBuilder tagBuilder = new TagBuilder("root");
        tagBuilder.addSibling("brother");

        String expected =
                "<root></root>" +
                "<brother></brother>";

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
}
