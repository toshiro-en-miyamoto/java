package se8exam.s04;
import se8exam.s04.sample.Sample;
import se8exam.s04.sample.test.*;
public class Main {
   public static void main(String[] args) {
      new Sample().sample();
      int num = Integer.parseInt(args[0]);
      new Test().test(num);
   }
}
/*
import java.lang.Integer;
import com.*;

import com.sample.*;

import com.sample.Sample;
import com.sample.test.*;

import java.lang.*;
*/