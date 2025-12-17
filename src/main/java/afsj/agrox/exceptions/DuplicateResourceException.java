package afsj.agrox.exceptions;

public class DuplicateResourceException extends RuntimeException {
   private static final String DEFAULT_MESSAGE = "This resource already exists!";

   public DuplicateResourceException() {
      super(DEFAULT_MESSAGE);
   }

   public DuplicateResourceException(String message) {
      super(message);
   }
}
