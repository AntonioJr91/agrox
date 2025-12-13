package afsj.agrox.exceptions;

public class DomainException extends RuntimeException {
   private static final String PREFIX = "[DomainException] ";

   public DomainException(String message) {
      super(PREFIX + message);
   }
}
