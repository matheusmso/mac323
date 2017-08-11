// Nome: Matheus de Mello Santos Oliveira
// NUSP: 8642821

import edu.princeton.cs.algs4.*;

public class BulgingSquares {
   public static void main(String[] args) {
      StdDraw.setXscale(-7.5, 7.5);
      StdDraw.setYscale(-7.5, 7.5);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.filledSquare(0.0, 0.0, 7.5);
      int x, y;
      for (x = -7; x <= 7; x++) {
         for (y = -7; y <= 7; y++){
            if ((x + y) % 2 != 0) {
               StdDraw.setPenColor(StdDraw.WHITE);
               StdDraw.filledSquare(x, y, 0.5);
            }
            if ((x * y) > 0 && ((x * x) + (y * y)) < 39) {
               if ((x + y) % 2 != 0) {
                  StdDraw.setPenColor(StdDraw.BLACK);
               }
               else {
                  StdDraw.setPenColor(StdDraw.WHITE);
               }
               StdDraw.filledSquare(x - .3, y + .3, .15);
               StdDraw.filledSquare(x + .3, y - .3, .15);
            }
            else if ((x * y) < 0 && ((x * x) + (y * y)) < 39) {
               if ((x + y) % 2 != 0) {
                  StdDraw.setPenColor(StdDraw.BLACK);
               }
               else {
                  StdDraw.setPenColor(StdDraw.WHITE);
               }
               StdDraw.filledSquare(x - .3, y - .3, .15);
               StdDraw.filledSquare(x + .3, y + .3, .15);
            }
            else if ((x == 0) || (y == 0)) {
               if (x == 0 && y > 0 && y < 7) {
                  if ((x + y) % 2 != 0) {
                     StdDraw.setPenColor(StdDraw.BLACK);
                  }
                  else {
                     StdDraw.setPenColor(StdDraw.WHITE);
                  }
                  StdDraw.filledSquare(x - .3, y - .3, .15);
                  StdDraw.filledSquare(x + .3, y - .3, .15);
               }
               else if (x == 0 && y < 0 && y > -7) {
                  if ((x + y) % 2 != 0) {
                     StdDraw.setPenColor(StdDraw.BLACK);
                  }
                  else {
                     StdDraw.setPenColor(StdDraw.WHITE);
                  }
                  StdDraw.filledSquare(x + .3, y + .3, .15);
                  StdDraw.filledSquare(x - .3, y + .3, .15);
               }
               else if (y == 0 && x > 0 && x < 7) {
                  if ((x + y) % 2 != 0) {
                     StdDraw.setPenColor(StdDraw.BLACK);
                  }
                  else {
                     StdDraw.setPenColor(StdDraw.WHITE);
                  }
                  StdDraw.filledSquare(x - .3, y + .3, .15);
                  StdDraw.filledSquare(x - .3, y - .3, .15);
               }
               else if (y == 0 && x < 0 && x > -7) {
                  if ((x + y) % 2 != 0) {
                     StdDraw.setPenColor(StdDraw.BLACK);
                  }
                  else {
                     StdDraw.setPenColor(StdDraw.WHITE);
                  }
                  StdDraw.filledSquare(x + .3, y + .3, .15);
                  StdDraw.filledSquare(x + .3, y - .3, .15);
               }
            }
         }
      }
   }
}
