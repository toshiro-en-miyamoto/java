package se8exam.g26;

public enum LanguageEnumCodeName {
   JAPANESE("ja", "日本語"),
   ENGLISH ("en", "English"),
   CHINESE ("zh", "华语"),
   KOREAN  ("ko", "한국어"),
   OTHER   ("",   "Other");

   private String alpha2;
   private String localName;
   LanguageEnumCodeName(String alpha2, String localName) {
      this.alpha2 = alpha2;
      this.localName = localName;
   }
   public String getAlpha2() {
      return this.alpha2;
   }
   public String toString() {
      return this.localName;
   }
}