package se.collection.postalcode;

public final class Postalcode implements Comparable<Postalcode> {
   private final String code;
   private final String pref;
   private final String city;
   private final String area;

   public String getCode() { return code; }
   public String getPref() { return pref; }
   public String getCity() { return city; }
   public String getArea() { return area; }

   public static Postalcode of(String code, String pref, String city, String area) {
      return new Postalcode(code, pref, city, area);
   }

   private Postalcode(String code, String pref, String city, String area) {
      this.code = code;
      this.pref = pref;
      this.city = city;
      this.area = area;
   }

   private static final String SEPARATOR = " ";
   @Override public String toString() {
      return new StringBuilder()
            .append(code)
            .append(SEPARATOR)
            .append(pref)
            .append(city)
            .append(area)
            .toString();
   }

   @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null) return false;
      if (this.getClass() != o.getClass()) return false;
      Postalcode postalcode = (Postalcode) o;
      return code.equals(postalcode.getCode());
   }

   @Override public int hashCode() {
      return code.hashCode();
   }

   @Override public int compareTo(Postalcode postalcode) {
      return code.compareTo(postalcode.getCode());
   }

}
