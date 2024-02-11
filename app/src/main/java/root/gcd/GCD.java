/**
 * Copyright (c) 2009 ISP RAS.
 * 109004, A. Solzhenitsina, 25, Moscow, Russia.
 * All rights reserved.
 *
 * $Id$
 * Created on Dec 22, 2015
 *
 */

package root.gcd;

/**
 * @author Victor Kuliamin
 *
 */
public class GCD {
  public int gcd(int x, int y) {
    long t, x_l = x, y_l = y;

    if (x < 0)
      x_l = -x;
    if (y < 0)
      y_l = -y;

    while (y_l != 0) {
      if (y_l > x_l) {
        x_l = y_l - x_l;
        y_l = y_l - x_l;
        x_l = x_l + y_l;
      }

      if (y_l == 0)
        return (int) x_l;

      t = y_l;
      y_l = x_l % y_l;
      x_l = t;
    }
    return (int) x_l;
  }

}
