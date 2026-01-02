package afsj.agrox.exceptions;

public class DuplicateCpfException extends RuntimeException {
   public DuplicateCpfException(String message) {
      super(message);
   }
   public DuplicateCpfException() {super("CPF already registered.");
   }


}
