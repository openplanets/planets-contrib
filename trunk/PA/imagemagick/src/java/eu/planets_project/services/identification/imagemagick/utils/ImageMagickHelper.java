/**
 * 
 */
package eu.planets_project.services.identification.imagemagick.utils;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

import eu.planets_project.ifr.core.techreg.formats.FormatRegistry;
import eu.planets_project.ifr.core.techreg.formats.FormatRegistryFactory;
import eu.planets_project.services.utils.PlanetsLogger;
import eu.planets_project.services.utils.ProcessRunner;

/**
 * @author melmsp
 *
 */
public class ImageMagickHelper {
	
	private static File im_home = new File(System.getenv("IMAGEMAGICK_HOME"));
	private static FormatRegistry fReg = FormatRegistryFactory.getFormatRegistry();
	private static List<URI> inFormats = null;
	private static List<URI> outFormats = null;
	
	private static PlanetsLogger log = PlanetsLogger.getLogger(ImageMagickHelper.class) ; 
	
	private static void init() {
		log.info("Initializing ImageMagick format tables." + System.getProperty("line.separator") +  "Checking supported formats and installed libraries...will be back soon, please hang on!");
		ProcessRunner imageMagick = new ProcessRunner();
		imageMagick.setStartingDir(im_home);
		imageMagick.setCommand(getListCommand());
		imageMagick.run();
		String output = imageMagick.getProcessOutputAsString();
		output = output.replace("*", "").trim().replaceAll("[ ]+", " ").trim();
		StringTokenizer tokenizer = new StringTokenizer(output, System.getProperty("line.separator"));
		List<String> lines = new ArrayList<String>();
		
		while(tokenizer.hasMoreTokens()) {
			String currentLine = tokenizer.nextToken();
			if(currentLine.matches("[ ]?[A-Za-z0-9]+ [A-Za-z0-9]+ [rw\\-+]{3}.+?")) {
				lines.add(currentLine.trim());
			}
		}
		
		String[] splitted = null;
		TreeSet<URI> inputFormats = new TreeSet<URI>();
		TreeSet<URI> outputFormats = new TreeSet<URI>();
		for (String currentString : lines) {
			splitted = currentString.split(" ", 4);
			if(splitted[2].contains("r")) {
				inputFormats.add(fReg.createExtensionUri(splitted[1]));
			}
			if(splitted[2].contains("w")) {
				outputFormats.add(fReg.createExtensionUri(splitted[1]));
			}
		}
		inFormats = new ArrayList<URI>();
		outFormats = new ArrayList<URI>();
		inFormats.addAll(inputFormats);
		outFormats.addAll(outputFormats);
	}
	
	private static List<String> getListCommand() {
		List<String> commands = new ArrayList<String>();
		commands.add(im_home.getAbsolutePath() + File.separator + "identify");
		commands.add("-list");
		commands.add("format");
		return commands;
	}
	
	public static List<URI> getSupportedInputFormats() {
		if(inFormats == null || outFormats == null) {
			init();
		}
		else {
			log.info("ImageMagick format tables already initialized.");
		}
		return inFormats;
	}
	
	public static List<URI> getSupportedOutputFormats() {
		if(inFormats == null || outFormats == null) {
			init();
		}
		else {
			log.info("ImageMagick format tables already initialized.");
		}
		return outFormats;
	}

}