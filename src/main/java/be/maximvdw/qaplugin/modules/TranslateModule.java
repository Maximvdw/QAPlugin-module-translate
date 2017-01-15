package be.maximvdw.qaplugin.modules;

import be.maximvdw.qaplugin.api.AIModule;
import be.maximvdw.qaplugin.api.AIQuestionEvent;
import be.maximvdw.qaplugin.api.QAPluginAPI;
import be.maximvdw.qaplugin.api.ai.Context;
import be.maximvdw.qaplugin.api.ai.Intent;
import be.maximvdw.qaplugin.api.ai.IntentResponse;
import be.maximvdw.qaplugin.api.ai.IntentTemplate;
import be.maximvdw.qaplugin.api.annotations.*;
import be.maximvdw.qaplugin.api.exceptions.FeatureNotEnabled;
import be.maximvdw.qaplugin.modules.google.Translator;

import java.util.Locale;
import java.util.Map;

/**
 * TranslateModule
 * Created by maxim on 29-Dec-16.
 */
@ModuleName("Translate")
@ModuleAuthor("Maximvdw")
@ModuleVersion("1.1.0")
@ModuleDescription("Ask the assistant to translate something.")
public class TranslateModule extends AIModule {
    private Translator translator = null;

    public TranslateModule() {
        translator = Translator.getInstance();

        Intent translate = new Intent("Translate")
                .addTemplate("can you translate something?")
                .addTemplate("can you help me translate something?")
                .addTemplate(new IntentTemplate()
                        .addPart("how do you translate it to ")
                        .addPart(new IntentTemplate.TemplatePart("dutch")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true)))
                .addTemplate(new IntentTemplate()
                        .addPart("how do you say it in ")
                        .addPart(new IntentTemplate.TemplatePart("dutch")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true)))
                .addTemplate(new IntentTemplate()
                        .addPart("translate \"")
                        .addPart(new IntentTemplate.TemplatePart("Ik ben maxim")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\" to ")
                        .addPart(new IntentTemplate.TemplatePart("english")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true)))
                .addTemplate(new IntentTemplate()
                        .addPart("can you translate \"")
                        .addPart(new IntentTemplate.TemplatePart("Ik ben maxim")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\" from ")
                        .addPart(new IntentTemplate.TemplatePart("english")
                                .withAlias("source")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart(" to ")
                        .addPart(new IntentTemplate.TemplatePart("german")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addTemplate(new IntentTemplate()
                        .addPart("how do you say \"")
                        .addPart(new IntentTemplate.TemplatePart("Ik ben maxim")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\" in ")
                        .addPart(new IntentTemplate.TemplatePart("english")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addTemplate(new IntentTemplate()
                        .addPart("how do you say \"")
                        .addPart(new IntentTemplate.TemplatePart("Greetings")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\" in ")
                        .addPart(new IntentTemplate.TemplatePart("english")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addTemplate(new IntentTemplate()
                        .addPart("how do you say \"")
                        .addPart(new IntentTemplate.TemplatePart("Greetings")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\" to ")
                        .addPart(new IntentTemplate.TemplatePart("english")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addTemplate(new IntentTemplate()
                        .addPart("how to translate \"")
                        .addPart(new IntentTemplate.TemplatePart("Ik ben maxim")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\" from ")
                        .addPart(new IntentTemplate.TemplatePart("english")
                                .withAlias("source")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart(" to ")
                        .addPart(new IntentTemplate.TemplatePart("german")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addTemplate(new IntentTemplate()
                        .addPart("how do you say \'")
                        .addPart(new IntentTemplate.TemplatePart("Ik ben maxim")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\' from ")
                        .addPart(new IntentTemplate.TemplatePart("english")
                                .withAlias("source")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart(" to ")
                        .addPart(new IntentTemplate.TemplatePart("german")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addTemplate(new IntentTemplate()
                        .addPart("how do you say \'")
                        .addPart(new IntentTemplate.TemplatePart("I love you")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\' in ")
                        .addPart(new IntentTemplate.TemplatePart("french")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addTemplate(new IntentTemplate()
                        .addPart("what is \'")
                        .addPart(new IntentTemplate.TemplatePart("Ik ben maxim")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\' in ")
                        .addPart(new IntentTemplate.TemplatePart("french")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addTemplate(new IntentTemplate()
                        .addPart("what is \"")
                        .addPart(new IntentTemplate.TemplatePart("Bonjour")
                                .withMeta("@sys.any")
                                .withAlias("text")
                                .setUserDefined(true))
                        .addPart("\" in ")
                        .addPart(new IntentTemplate.TemplatePart("Dutch")
                                .withAlias("target")
                                .withMeta("@sys.language")
                                .setUserDefined(true))
                        .addPart("?"))
                .addResponse(new IntentResponse()
                        .addAffectedContext(new Context("translate", 2))
                        .withAction(this)
                        .addParameter(new IntentResponse.ResponseParameter("text", "$text.original")
                                .withDefaultValue("#translate.text")
                                .withDataType("@sys.any")
                                .setRequired(true)
                                .addPrompt("What do I need to translate?")
                                .addPrompt("What is there for me to translate?")
                                .addPrompt("Can you tell me what I have to translate?")
                                .addPrompt("Please provide the text you want translated")
                                .addPrompt("What do you want me to translate?")
                                .addPrompt("Can you tell me what is there to translate?"))
                        .addParameter(new IntentResponse.ResponseParameter("source", "$source")
                                .withDefaultValue("#translate.source")
                                .withDataType("@sys.language"))
                        .addParameter(new IntentResponse.ResponseParameter("target", "$target")
                                .withDefaultValue("#translate.target")
                                .withDataType("@sys.language")
                                .setRequired(true)
                                .addPrompt("To what language do you want me to translate it?")
                                .addPrompt("What is the language I need to translate to?")
                                .addPrompt("To what language should I translate?")
                                .addPrompt("What is the language I have to translate to?"))
                        .addMessage(new IntentResponse.TextResponse()
                                .addSpeechText("It is translated like: {{translation}}")
                                .addSpeechText("\"{{text}}\" is translated to \"{{translation}}\" in {{target}}")
                                .addSpeechText("\"{{text}}\" is translated to \"{{translation}}\"")
                                .addSpeechText("\"{{translation}}\"")
                                .addSpeechText("{{translation}}"))
                        .addMessage(new IntentResponse.TextResponse()
                                .addSpeechText("I don't know that language :S")
                                .addSpeechText("I can't find the language you want to translate to")
                                .addSpeechText("I do not know the language you want to translate to")));

        try {
            // Upload the intents
            if (QAPluginAPI.findIntentByName(translate.getName()) == null) {
                if (!QAPluginAPI.uploadIntent(translate)) {
                    warning("Unable to upload intent!");
                }
            }
        } catch (FeatureNotEnabled ex) {
            severe("You do not have a developer access token in your QAPlugin config!");
        }
    }

    public String getResponse(AIQuestionEvent event) {
        Map<String, String> params = event.getParameters();
        if (!params.containsKey("text")) {
            return event.getDefaultResponse();
        }

        if (!params.containsKey("target")) {
            return event.getDefaultResponse();
        }

        Locale sourceLanguage = null;
        if (params.containsKey("source")) {
            sourceLanguage = toCode(params.get("source"));
        }
        Locale targetLanguage = toCode(params.get("target"));
        if (targetLanguage == null) {
            // Error
            return ((IntentResponse.TextResponse) event.getDefaultResponses().get(1)).getSpeechTexts().get(0);
        }

        String text = params.get("text");
        if (text.startsWith("\"") && text.endsWith("\"")) {
            text = text.substring(1, text.length() - 1);
        }
        if (text.startsWith("\'") && text.endsWith("\'")) {
            text = text.substring(1, text.length() - 1);
        }
        String translation = translator.translate(text, sourceLanguage == null ? "" : sourceLanguage.getLanguage(), targetLanguage.getLanguage());
        return ((IntentResponse.TextResponse) event.getDefaultResponses().get(0)).getSpeechTexts().get(0)
                .replace("{translation}", translation)
                .replace("{text}", text)
                .replace("{target}", targetLanguage.getDisplayLanguage());
    }

    /**
     * Convert language name to ISO3
     *
     * @param name name
     * @return
     */
    public Locale toCode(String name) {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (name.equalsIgnoreCase(locale.getDisplayLanguage())) {
                return locale;
            }
            if (name.equalsIgnoreCase(locale.getCountry())) {
                return locale;
            }
            if (name.equalsIgnoreCase(locale.getLanguage())) {
                return locale;
            }
        }
        return null;
    }
}
