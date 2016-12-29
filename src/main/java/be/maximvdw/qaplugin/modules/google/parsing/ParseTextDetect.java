package be.maximvdw.qaplugin.modules.google.parsing;

import be.maximvdw.qaplugin.modules.google.URLCONSTANTS;
import be.maximvdw.qaplugin.modules.google.text.Text;
import be.maximvdw.qaplugin.modules.google.utils.WebUtils;

public class ParseTextDetect implements Parse {
	private StringBuilder url;
	private Text input;

	public ParseTextDetect(Text input) {
		this.input = input;
	}

	public void appendURL() {

		url = new StringBuilder(URLCONSTANTS.GOOGLE_TRANSLATE_DETECT);
		url.append("v=1.0&");
		url.append("q=" + input.getLanguage().replace(" ", "%20"));

	}

	public void parse() {

		appendURL();
		String result = WebUtils.source(url.toString());
		input.setLanguage(result.split(",")[0].split(":")[2].replace("\"", ""));

	}

}
