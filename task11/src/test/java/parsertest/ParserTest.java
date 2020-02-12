package parsertest;

import by.training.entity.Type;
import by.training.entity.composite.Component;
import by.training.entity.composite.Composite;
import by.training.service.parser.factory.HandlerFactory;
import by.training.service.parser.handler.Handler;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
public class ParserTest {

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

    @DataProvider(name = "positiveText")
    public Object[][] createPositiveText() {
        return new Object[][]{
                {"\tFirst sentence.", "\tFirst sentence. \n\n"},
                {"\tA.", "\tA. \n\n"},
                {"\tFirst sentence first paragraph. Second sentence.\n\tFirst sentence second paragraph.",
                        "\tFirst sentence first paragraph. \nSecond sentence. \n\n\tFirst sentence second paragraph. \n\n"},
                {"", ""},
                {"\t", ""},
                {"\tSentence with a dot at the end.", "\tSentence with a dot at the end. \n\n"},
                {"\tSentence without a dot at the end", "\t\n"}, // \t\n - paragraph
                {"\tSentence begins with a capital letter.", "\tSentence begins with a capital letter. \n\n"},
                {"\tsentence begins with a lowercase letter.", "\t\n"},
                {"\tSentence ends!", "\tSentence ends! \n\n"},
                {"\tSentence ends?", "\tSentence ends? \n\n"},
                {"\tSentence ends...", "\tSentence ends... \n\n"}
        };
    }

    @DataProvider(name = "positiveParseTextPromptlyByChar")
    public Object[][] createPositiveParseTextPromptlyByChar() {
        return new Object[][]{
                {"\tText test text.", "Texttesttext."},
                {"\tTest 12 43.\n\tOne more test.", "Test1243.Onemoretest."}
        };
    }


    @Test(dataProvider = "positiveText")
    public void test(String expected, String found) {
        this.component = new Composite(Type.TEXT);
        textParser.parse(this.component, expected);
        String actual = this.component.operation();
        Assert.assertEquals(actual, found);
    }

    @Test(dataProvider = "positiveParseTextPromptlyByChar")
    public void test2(String expected, String found) {
        this.component = new Composite(Type.TEXT);
        textParser.setNext(symbolParser);
        textParser.parse(component, expected);
        String actual = this.component.operation();
        Assert.assertEquals(actual, found);
    }


}
