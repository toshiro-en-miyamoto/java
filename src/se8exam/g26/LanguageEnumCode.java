package se8exam.g26;

public enum LanguageEnumCode {
   JAPANESE("ja"),
   ENGLISH ("en"),
   CHINESE ("zh"),
   KOREAN  ("ko"),
   OTHER   ("");

   private String alpha2;
   LanguageEnumCode(String alpha2) {
      this.alpha2 = alpha2;
   }
   public String getAlpha2() {
      return this.alpha2;
   }
}