package be.maximvdw.qaplugin.modules.google.parsing;


import be.maximvdw.qaplugin.modules.google.URLCONSTANTS;
import be.maximvdw.qaplugin.modules.google.text.Text;
import be.maximvdw.qaplugin.modules.google.text.TextTranslate;
import be.maximvdw.qaplugin.modules.google.utils.WebUtils;

public class ParseTextTranslate implements Parse {
	private TextTranslate textTranslate;
	private StringBuilder url;

	public ParseTextTranslate(TextTranslate textTranslate) {
		this.textTranslate = textTranslate;
	}

	public void parse() {
		appendURL();
		String result = WebUtils.source(url.toString());
		String split[] = result.replace("[", "").replace("]", "").replace("\"",
				"").split(",");
		Text output = textTranslate.getOutput();
		output.setText(split[0]);

	}

	public TextTranslate getTextTranslate() {
		return textTranslate;
	}

	public void appendURL() {
		Text input = textTranslate.getInput();
		Text output = textTranslate.getOutput();
		url = new StringBuilder(URLCONSTANTS.GOOGLE_TRANSLATE_TEXT);
		url.append("&dt=t&q=" + input.getText().replace(" ", "%20"));
		if (!input.getLanguage().equals("")) {
			url.append("&sl=" + input.getLanguage());
		}else{
		    url.append("&sl=auto");
        }
		url.append("&tl=" + output.getLanguage());

	}
}
