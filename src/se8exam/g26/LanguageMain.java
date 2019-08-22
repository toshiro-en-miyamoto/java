package se8exam.g26;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class LanguageMain {
   public static void main(String args[]) {
      languageCode();
      languageEnum();
      languageEnumCode();
      languageEnumCodeName();
   }
   static int getLanguageIntCode() {
      return LanguageIntCode.JAPANESE;
      // return 99999; // any int can return
   }
   static void languageCode() {
      int language = getLanguageIntCode();
      assert language == LanguageIntCode.JAPANESE : language;
   }
   static LanguageEnum getLanguageEnum() {
      return LanguageEnum.JAPANESE;
      // return 99999; // compile error
   }
   static void languageEnum() {
      LanguageEnum language = getLanguageEnum();
      assert language == LanguageEnum.JAPANESE : language;

      String languageName = LanguageEnum.JAPANESE.name();
      assert "JAPANESE".equals(languageName) : languageName;
      LanguageEnum japanese = LanguageEnum.valueOf("JAPANESE");
      assert japanese == LanguageEnum.JAPANESE : japanese;

      for (LanguageEnum enumConst : LanguageEnum.values()) {
         System.out.format("(%s) ", enumConst);
      }
      System.out.println();

      final String targetName = "CHINESE";
      Optional<LanguageEnum> optionalConst =
         Stream.of(LanguageEnum.values())
         .filter(enumConst -> targetName.equals(enumConst.name()))
         .findFirst();
      try {
         LanguageEnum foundConst = optionalConst.get();
         assert foundConst == LanguageEnum.CHINESE : foundConst;
      } catch(NoSuchElementException e) {
         System.err.println("enum constant not found");
      }
   }
   static void languageEnumCode() {
      for (LanguageEnumCode code : LanguageEnumCode.values()) {
         System.out.format("(%s:%s) ", code.name(), code.getAlpha2());
      }
      System.out.println();
   }
   static void languageEnumCodeName() {
      for (LanguageEnumCodeName code : LanguageEnumCodeName.values()) {
         System.out.format("(%s:%s:%s) ", code.name(), code.getAlpha2(), code);
      }
      System.out.println();
   }
}