import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

/**
 * Created By Arjun Gautam
 * Date :01/10/2021
 * Time :13:32
 * Project Name :VoiceAssistant
 */

public class VoiceAssistant {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("src/main/resources/assistant.dic");
        configuration.setLanguageModelPath("src/main/resources/language.lm");

        try {
            LiveSpeechRecognizer speechRecognizer = new LiveSpeechRecognizer(configuration);
            speechRecognizer.startRecognition(true);

            SpeechResult speechResult = null;

            while ((speechResult = speechRecognizer.getResult()) != null) {
                String voiceCommand = speechResult.getHypothesis();
                System.out.println("Voice Command is :" + voiceCommand);

                if (voiceCommand.equalsIgnoreCase("Open Safari")) {
                    Runtime.getRuntime().exec("/usr/bin/open -a Safari https://youtube.com/arjuncodes");

                } else if (voiceCommand.equalsIgnoreCase("Close Safari")) {
                    Runtime.getRuntime().exec("killall Safari");

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
