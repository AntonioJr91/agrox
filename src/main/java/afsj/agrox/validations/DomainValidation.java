package afsj.agrox.validations;

import afsj.agrox.exceptions.DomainException;

public final class DomainValidation {

   private DomainValidation() {
   }

   public static void when(boolean hasError, String message) {
      if (hasError) throw new DomainException(message);
   }
}
