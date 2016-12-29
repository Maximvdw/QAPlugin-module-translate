package be.maximvdw.qaplugin.modules.google;

import be.maximvdw.qaplugin.modules.google.parsing.Parse;
import be.maximvdw.qaplugin.modules.google.parsing.ParseTextDetect;
import be.maximvdw.qaplugin.modules.google.parsing.ParseTextTranslate;
import be.maximvdw.qaplugin.modules.google.text.Text;
import be.maximvdw.qaplugin.modules.google.text.TextTranslate;

public class Translator {
	private static Translator translator;

	private Translator() {
	}

	public synchronized static Translator getInstance() {

		if (translator == null) {
			translator = new Translator();
		}
		return translator;

	}

	public void translate(TextTranslate textTranslate) {

		Parse parse = new ParseTextTranslate(textTranslate);
		parse.parse();

	}

	public String translate(String text, String languageInput,
			String languageOutput) {

		Text input = new Text(text, languageInput);
		TextTranslate textTranslate = new TextTranslate(input, languageOutput);
		Parse parse = new ParseTextTranslate(textTranslate);
		parse.parse();
		return textTranslate.getOutput().getText();

	}

	public String detect(String text) {

		Text input = new Text(text);
		Parse parse = new ParseTextDetect(input);
		parse.parse();
		return input.getLanguage();

	}

}
