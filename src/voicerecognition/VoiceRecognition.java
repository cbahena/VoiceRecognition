/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package voicerecognition;

import javax.speech.*;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
/**
 *
 * @author carlos
 */
public class VoiceRecognition extends ResultAdapter{

 static Recognizer recognizer;
 String gst;
 static owl.DLQuery queryOntology;
 
 public void readCommand(String args)
 {
     try {
           String say=args;

           SynthesizerModeDesc required = new SynthesizerModeDesc();
           required.setLocale(Locale.ROOT);

           Voice voice=new Voice(null, Voice.AGE_OLDER_ADULT, Voice.AGE_OLDER_ADULT, null);

           required.addVoice(voice);

           Synthesizer synth = Central.createSynthesizer(null);

           synth.allocate();
           synth.resume();

           synth.speakPlainText(say,null);

           synth.waitEngineState(Synthesizer.QUEUE_EMPTY);
           synth.deallocate();

    } 
    catch (IllegalArgumentException | InterruptedException | AudioException | EngineException e) {
       e.printStackTrace();
    }
 }
 @Override
 public void resultAccepted(ResultEvent re)
 {
    try
    {
        Result res = (Result)(re.getSource());
        ResultToken tokens[] = res.getBestTokens();

        String args= new String();
        args="";
        for (int i=0; i < tokens.length; i++)
        {
           gst = tokens[i].getSpokenText();
           args+=gst+" ";
           System.out.print(gst + " ");
           }
           System.out.println();
           if(gst.equals("Adios"))
           {
              recognizer.deallocate();
              args="Cerrando programa!";
              System.out.println(args);
              readCommand(args);
              System.exit(0);
           }
           else
           {
              recognizer.suspend();
              readCommand(args);
              //Ejecuta el comando en la ontologia
              
              recognizer.resume();
           }
    }   catch(AudioException | EngineException ex)
    {
       System.out.println("Error inesperado " + ex);
    }
 }
 
 public static void main(String args[])
 {
    try
    {
        queryOntology = new owl.DLQuery();
        queryOntology.doQuery("Accion or Rock");
        /*recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
        recognizer.allocate();

        FileReader grammar1 = new FileReader("Gramatica.txt");

        RuleGrammar rg = recognizer.loadJSGF(grammar1);
        rg.setEnabled(true);

        recognizer.addResultListener(new VoiceRecognition());

        System.out.println("Diga una comando:");
        recognizer.commitChanges();

        recognizer.requestFocus();
        recognizer.resume();*/
    }catch (Exception e)
    {
        System.out.println("Exception en " + e.toString());
        e.printStackTrace();
        System.exit(0);
    }
 }
}
