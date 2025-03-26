package hw5;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

  // Update this to any other data file for benchmarking experiments.
  public static String DATA_FILENAME = "urls.txt";

  /**
   * Instantiate a Map to be used for JHUgle's search engine.
   *
   * @return an implementation of Map ADT.
   */
  public static Map getMap() {
    // Update this to any other implementation of Map for benchmarking experiments.
    return new JdkHashMap();
  }

  /**
   * Get the data file to be used to build JHUgle's index.
   *
   * @return a File object.
   */
  public static File getDataFile() {
    URL url = Thread.currentThread().getContextClassLoader().getResource("");
    String path = url.getPath().replace("%20", " ")
        .replace("classes", "resources");
    Path dataFile = Paths.get(path, DATA_FILENAME);
    // TODO: On Windows, use the statement below instead of the one above!
    // Path dataFile = Paths.get(path.substring(1), DATA_FILENAME);
    return dataFile.toFile();
  }


}
