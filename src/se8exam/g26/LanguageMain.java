package se8exam.g26;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

public class LanguageMain {
   public static void main(String args[]) {
      languageCode();
      languageEnum();
      languageEnumCode();
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
      String languageName = language.toString();
      assert "JAPANESE".equals(languageName) : languageName;
      LanguageEnum japanese = LanguageEnum.valueOf("JAPANESE");
      assert japanese == LanguageEnum.JAPANESE : japanese;
   }
   static LanguageEnumCode getLanguageEnumCode() {
      return LanguageEnumCode.JAPANESE;
      // return 99999; // compile error
   }
   static String getLanguageEnumCodeAlpha2() {
      return "ja";
   }
   static void languageEnumCode() {
      LanguageEnumCode language = getLanguageEnumCode();
      assert language == LanguageEnumCode.JAPANESE : language;
      String languageName = language.toString();
      assert "JAPANESE".equals(languageName) : languageName;
      LanguageEnumCode japanese = Enum.valueOf(LanguageEnumCode.class, "JAPANESE");
      assert japanese == LanguageEnumCode.JAPANESE : japanese;

      String jaAlpha2 = LanguageEnumCode.JAPANESE.getAlpha2();
      assert "ja".equals(jaAlpha2) : jaAlpha2;
      String enAlpha2 = LanguageEnumCode.ENGLISH.getAlpha2();
      assert "en".equals(enAlpha2) : enAlpha2;

      String alpha2 = getLanguageEnumCodeAlpha2();
      Optional<LanguageEnumCode> constantOfAlpha2 =
         LanguageEnumCode.getConstantOfAlpha2(alpha2);
      try {
         LanguageEnumCode constant = constantOfAlpha2.get();
         assert constant == LanguageEnumCode.JAPANESE : constant;
      } catch(NoSuchElementException e) {
         System.err.println("invalid alph2");
      }
   }
}