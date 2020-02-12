package sorttest;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;
import by.training.service.Service;
import by.training.service.factory.ServiceFactory;
import by.training.service.parser.factory.HandlerFactory;
import by.training.service.parser.handler.Handler;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class SortTest {

    private Service service = ServiceFactory.getInstance().getService();
    private Handler textParser = HandlerFactory.getInstance().getTextHandler();
    private Handler paragraphParser = HandlerFactory.getInstance().getParagraphHandler();
    private Handler sentenceParser = HandlerFactory.getInstance().getSentenceParser();
    private Handler lexemeParser = HandlerFactory.getInstance().getLexemeParser();
    private Handler wordParser = HandlerFactory.getInstance().getWordParser();
    private Handler symbolParser = HandlerFactory.getInstance().getSymbolParser();
    private Component component;

    @BeforeTest
    public void setNextParser() {
        wordParser.setNext(symbolParser);
        lexemeParser.setNext(symbolParser);
        sentenceParser.setNext(lexemeParser);
        paragraphParser.setNext(sentenceParser);
        textParser.setNext(paragraphParser);
    }

    @DataProvider(name = "sortParagraphByCountOfSentence")
    public Object[][] sortParagraphByCountOfSentence() {
        return new Object[][]{
                {"\tFirst sentence. Second sentence.\n\tFirst sentence.\n\tFirst sentence. Second sentence. Third sentence.",
                        "\tFirst sentence. \n\n\tFirst sentence. \nSecond sentence. \n\n\tFirst sentence. \nSecond sentence. \nThird sentence. \n\n"}
        };
    }

    @DataProvider(name = "sortSentenceByCountOfLexeme")
    public Object[][] sortSentenceByCountOfLexeme() {
        return new Object[][]{
                {"\tThis sentence have 5 lexeme. And this sentence have 6 lexeme. One more 4 lexeme.",
                        "\tOne more 4 lexeme. \nThis sentence have 5 lexeme. \nAnd this sentence have 6 lexeme. \n\n"}
        };
    }

    @DataProvider(name = "sortWordInSentenceByLength")
    public Object[][] sortWordInSentenceByLength() {
        return new Object[][]{
                {"\tWe all know that large parrots live long.",
                        "\tWe all know that live large long. parrots \n\n"}
        };
    }

    @DataProvider(name = "sortLexemeInSentenceByChar")
    public Object[][] sortLexemeInSentenceByChar() {
        return new Object[][]{
                {"\tLive love also glass welcome.", "\talso glass love welcome. Live \n\n"}, // L - upper case
                {"\tWell reliable hello hell help plull.", "\tplull. Well hell hello reliable help \n\n"}
        };
    }


    @Test(dataProvider = "sortParagraphByCountOfSentence")
    public void test1(String expected, String found) {
        component = new Composite(Type.TEXT);
        textParser.parse(component, expected);
        component = service.sortParagraphByCountOfSentence(component);
        String actual = component.operation();
        Assert.assertEquals(actual, found);
    }

    @Test(dataProvider = "sortSentenceByCountOfLexeme")
    public void test2(String expected, String found) {
        component = new Composite(Type.TEXT);
        textParser.parse(component, expected);
        component = service.sortSentenceByCountOfLexeme(component);
        String actual = component.operation();
        Assert.assertEquals(actual, found);
    }

    @Test(dataProvider = "sortWordInSentenceByLength")
    public void test3(String expected, String found) {
        component = new Composite(Type.TEXT);
        textParser.parse(component, expected);
        component = service.sortWordInSentenceByLength(component);
        String actual = component.operation();
        Assert.assertEquals(actual, found);
    }

    @Test(dataProvider = "sortLexemeInSentenceByChar")
    public void test4(String expected, String found) {
        component = new Composite(Type.TEXT);
        textParser.parse(component, expected);
        component = service.sortLexemeInSentenceByChar(component, 'l');
        String actual = component.operation();
        Assert.assertEquals(actual, found);
    }

}
