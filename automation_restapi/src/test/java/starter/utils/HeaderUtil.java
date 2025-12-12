package starter.utils;

public class HeaderUtil {

  public static String extractFilenameFromContentDisposition(String contentDisposition) {
    for (String part : contentDisposition.split(";")) {
      part = part.trim();
      if (part.startsWith("filename=")) {
        String filename = part.substring("filename=".length()).trim();
        // Remove quotes if present
        if (filename.startsWith("\"") && filename.endsWith("\"")) {
          filename = filename.substring(1, filename.length() - 1);
        }
        return filename;
      }
    }
    return null;
  }
}
