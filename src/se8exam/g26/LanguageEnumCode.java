package se8exam.g26;
import java.util.Optional;
import java.util.stream.Stream;

public enum LanguageEnumCode {
   JAPANESE("ja"),   // Japanse
   ENGLISH("en"),    // English
   CHINESE("zh"),    // Chinese
   KOREAN("ko"),     // Korean
   OTHER("");      // Other

   private String alpha2;
   LanguageEnumCode(String alpha2) {
      this.alpha2 = alpha2;
   }
   public String getAlpha2() {
      return this.alpha2;
   }
   public static Optional<LanguageEnumCode> getConstantOfAlpha2(String alpha2)
   throws java.util.NoSuchElementException {
      Optional<LanguageEnumCode> optionalConst =
         Stream.of(LanguageEnumCode.values())
         .filter(constant -> alpha2.equals(constant.getAlpha2()))
         .findFirst();
      return optionalConst;
   }
}